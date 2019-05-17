package com.software.basement.tron.server.restcontroller;

import com.software.basement.tron.server.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/message")
    public Message getHelloMessage(){
        return new Message("Hello");
    }

}
