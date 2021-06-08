package io.github.chatlog;

import io.github.chatlog.services.DiscordApiServiceV2;
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
    private DiscordApiServiceV2 discordApiService;

    public static void main(String[] args) {
        SpringApplication.run(ChatLogDemoApplication.class, args);
    }

}
