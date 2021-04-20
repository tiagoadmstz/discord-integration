package io.github.chatlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author: tiagoadmstz
 * documentation: https://discord.com/developers/docs
 */
@SpringBootApplication
public class ChatLogDemoApplication {

    @Autowired
    private DiscordApiService discordApiService;

    public static void main(String[] args) {
        SpringApplication.run(ChatLogDemoApplication.class, args);
    }

}
