package com.Joysbrightt.ussdservice.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UssdMenuOptionAction {
    PROCESS_ACC_BALANCE("PROCESS_ACC_BALANCE"),
    PROCESS_ACC_PHONE_NUMBER("PROCESS_ACC_PHONE_NUMBER"),
    PROCESS_ACC_NUMBER("PROCESS_ACC_NUMBER");

    private String action;
    UssdMenuOptionAction(String action){
        this.action = action;
    }

    @JsonValue
    private String getAction(){
        return action;
    }
}
