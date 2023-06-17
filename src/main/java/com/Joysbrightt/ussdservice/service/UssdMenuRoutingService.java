package com.Joysbrightt.ussdservice.service;

import com.Joysbrightt.ussdservice.data.UssdMenu;
import com.Joysbrightt.ussdservice.data.UssdMenuOption;
import com.Joysbrightt.ussdservice.dto.UssdSessionDto;
import com.Joysbrightt.ussdservice.enums.UssdMenuOptionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UssdMenuRoutingService {

    @Autowired
    private UssdMenuService menuService;

    @Autowired UssdSessionService sessionService;

    public String menuLevelRouter(String sessionId, String serviceCode, String phoneNumber, String text) throws IOException{
            Map<String, UssdMenu> menus = menuService.loadMenu();
        UssdSessionDto sessionDto = checkAndSetSession(sessionId, serviceCode, phoneNumber, text);

        if (text.length() > 0){
            return getNextUssdMenuItem(sessionDto, menus);
        } else {
            return menus.get(sessionDto.getCurrentMenuLevel()).getText();
        }

    }

    public String getNextUssdMenuItem(UssdSessionDto sessionDto, Map<String, UssdMenu> menus) throws IOException{
        String [] levels = sessionDto.getText().split("\\*");
        String lastValue = levels[levels.length - 1];
        UssdMenu menuLevel = menus.get(sessionDto.getCurrentMenuLevel());

        if (Integer.parseInt(lastValue) <= menuLevel.getMaxSelections()){
            UssdMenuOption menuOption = menuLevel.getMenuOptions().get(Integer.parseInt(lastValue) - 1);
            return processMenuOption(sessionDto, menuOption);
        }
        return "CON";
    }

    public String getMenu(String menuLevel) throws IOException{
        Map<String, UssdMenu> menus = menuService.loadMenu();
        return menus.get(menuLevel).getText();
    }

    public String processMenuOption (UssdSessionDto sessionDto, UssdMenuOption menuOption) throws IOException{

        if (menuOption.getType().equals("response")){
            return processMenuOption(menuOption);
        } else if (menuOption.getType().equals("level")){
            updateUssdSessionMenuLevel(sessionDto, menuOption.getNextMenuLevel());
            return getMenu(menuOption.getNextMenuLevel());
        } else {
            return "CON";
        }

    }
    public String processMenuOptionResponse(UssdMenuOption menuOption){
        String response = menuOption.getResponse();
        Map<String, String> variableMap = new HashMap<>();

        if (menuOption.getAction() == UssdMenuOptionAction.PROCESS_ACC_BALANCE){
            variableMap.put("account_balance", "10000");
            response = replace(variableMap, response);
        } else if (menuOption.getAction() == UssdMenuOptionAction.PROCESS_ACC_NUMBER){
            variableMap.put("acccount_number", "12345567712");
            response = replaceVariable(variableMap, response);
        } else if (menuOption.getAction() == UssdMenuOptionAction.PROCESS_ACC_PHONE_NUMBER) {
            variableMap.put("phone_number", "12345678912");
            response = variableMap.replace(variableMap, response);
        }
        return response;
    }

    public String replaceVariable(Map<String, String > variablesMap, String response){
        StringSubstitutor  sub = new StringSubstitutor(variablesMap);
        return sub.replace(response);
    }

    public UssdSessionDto updateUssdSessionMenuLevel(UssdSessionDto sessionDto, String menulevel){
        sessionDto.setPreviousMenuLevel(sessionDto.getCurrentMenuLevel());
        sessionDto.setCurrentMenuLevel(menulevel);
        return sessionService.update(sessionDto);
    }

    public UssdSessionDto checkAndSetSession(String sessionId,String serviceCode, String phoneNumber, String text){

        UssdSessionDto sessionDto = sessionService.get(sessionId);

        if (sessionDto != null){
            sessionDto.setText(text);
            return sessionService.update(sessionDto);
        }
        sessionDto = new UssdSessionDto();
        sessionDto.setCurrentMenuLevel("1");
        sessionDto.setPreviousMenuLevel("1");
        sessionDto.setId(sessionId);
        sessionDto.setPhoneNumber(phoneNumber);
        sessionDto.setServiceCode(serviceCode);
        sessionDto.setText(text);
        return sessionService.createUssdSession(sessionDto);
    }
}
