package com.Joysbrightt.ussdservice.service;

import com.Joysbrightt.ussdservice.data.UssdMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Service
public class UssdMenuService {
    @Autowired
    ResourceLoader resourceLoader;

    private String readFromInputStream(InputStream inputStream) throws IOException{
        StringBuilder resultStringBuilder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)){
                String line;
                while ((line = br.readLine()) != null){
                    resultStringBuilder.append(line).append("\n");
                }
            }
        }
        return resultStringBuilder.toString();
    }
    public Map<String, Menu> loadMenu() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource("classpath: menu.json");
        InputStream input = resource.getInputStream();
        String json = readFromInputStream(input);
        return objectMapper.readValue(json, new TypeReference<Map<String, UssdMenu>>)
    }
}
