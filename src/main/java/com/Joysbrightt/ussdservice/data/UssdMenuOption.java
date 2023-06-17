package com.Joysbrightt.ussdservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UssdMenuOption {
    private String type;

    private String response;

    @JsonProperty("next_menu_level")
    private String nextMenuLevel;

    private MenuOptionAction action;

}
