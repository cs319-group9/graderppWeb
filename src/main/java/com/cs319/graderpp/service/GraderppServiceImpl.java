package com.cs319.graderpp.service;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;


@Service(value = "graderppService")
public class GraderppServiceImpl implements GraderppService {

    String helloMessage;

    @PostConstruct
    public void init() {
        this.helloMessage = "Hello from Service";
    }

    public String getHelloMessage() {
        return helloMessage;
    }

    public void setHelloMessage(String helloMessage) {
        this.helloMessage = helloMessage;
    }
}