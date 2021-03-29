package io.github.chatlog;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * author: tiagoadmstz
 * documentation: https://discord.com/developers/docs
 */
@SpringBootApplication
public class ChatLogDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatLogDemoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(value = "discord-api")
    public DiscordApi discordApiConfiguration() {
        DiscordApi discordApi = new DiscordApiBuilder()
                .setToken("ODIzNzA1NDI0NDU1Nzk0NzE4.YFktdA.u5c_UoBV59JIGkiNB5XioWPfIL0")
                .login()
                .join();
        discordApi.addMessageCreateListener(msg -> {
            DiscordDto discordDto = new DiscordDto(msg.getMessage());
            System.out.println(String.format("Send by %s to %s",
                    discordDto.getAuthor(), discordDto.getDestinatary()
            ));
            System.out.println(msg.getMessage().getContent());
        });
        return discordApi;
    }

}
