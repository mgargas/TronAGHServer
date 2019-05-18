package com.software.basement.tron.server.restcontroller;

import com.software.basement.tron.server.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class HelloController {


    @GetMapping
    public Message getHelloMessage(){
        return new Message("Hello");
    }

}
