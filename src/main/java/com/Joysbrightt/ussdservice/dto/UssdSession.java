package com.Joysbrightt.ussdservice.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@RedisHash(value="session", timeToLive=180)
public class UssdSessionDto implements Serializable {

 long serialVersionUID = 1L;




 

    @Id
    private String id;
    private String sessionId;
    private String serviceCode;
    private String phoneNumber;
    private String text;
    private String previousMenuLevel;
    private String currentMenuLevel;


}
