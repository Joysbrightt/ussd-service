package com.Joysbrightt.ussdservice.service;

import com.Joysbrightt.ussdservice.dto.UssdSessionDto;
import com.Joysbrightt.ussdservice.repository.UssdSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UssdSessionService {
    @Autowired
    private UssdSessionRepository ussdSessionRepository;

    public UssdSessionDto get(String id){
        return ussdSessionRepository.findById(id).orElse(null);
    }

    public UssdSession update(UssdSessionDto ussdSessionDto){
        if (ussdSessionRepository.existsById(ussdSessionDto.getId())){
            return ussdSessionRepository.save(ussdSessionDto);
        } throw new IllegalArgumentException("A session must have an id to be updated");

        public void delete(String id){
            ussdSessionRepository.deleteById(ussdSessionDto.getId());
        }
    }
}
