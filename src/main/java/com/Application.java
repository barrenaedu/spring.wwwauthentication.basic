package com;

import com.domain.Message;
import com.service.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    public Application(MessageManager messageManager) {
        messageManager.createMessage(new Message.MessageBuilder().text("Hello World").build());
        messageManager.createMessage(new Message.MessageBuilder().text("Have a nice day!").build());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
