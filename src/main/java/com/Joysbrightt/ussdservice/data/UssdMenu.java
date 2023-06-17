package com.Joysbrightt.ussdservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UssdMenu {

    @JsonProperty("id")
    private String id;

    @JsonProperty("menu_level")
    private String menuLevel;

    @JsonProperty("id")
    private String text;

    @JsonProperty("menu_options")
    private List<MenuOption> menuOptions;

    @JsonProperty("max_selections")
    private Integer maxSelections;
}
