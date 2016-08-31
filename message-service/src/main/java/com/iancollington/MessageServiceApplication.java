package com.iancollington;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@SpringBootApplication
public class MessageServiceApplication {

    @Value("${app.global.message}")
    private String globalMessage;

    @Value("${app.local.message}")
    private String localMessage;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Message getMessage() {
        return new Message(localMessage, globalMessage);
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class, args);
    }

    private class Message {

        private String localMessage;

        private String globalMessage;

        public Message(final String localMessage, final String globalMessage) {
            this.localMessage = localMessage;
            this.globalMessage = globalMessage;
        }

        public String getLocalMessage() {
            return localMessage;
        }

        public void setLocalMessage(final String localMessage) {
            this.localMessage = localMessage;
        }

        public String getGlobalMessage() {
            return globalMessage;
        }

        public void setGlobalMessage(final String globalMessage) {
            this.globalMessage = globalMessage;
        }
    }
}
