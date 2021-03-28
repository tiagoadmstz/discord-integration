package io.github.chatlog;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

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
                .setToken("token do bot")
                .login()
                .join();
        discordApi.addMessageCreateListener(msg -> {
            System.out.println(String.format("Send by %s to %s",
                    msg.getMessageAuthor().getDisplayName(),
                    msg.getMessage().getMentionedUsers().get(0).getName()));
            System.out.println(msg.getMessage().getContent());
        });
        return discordApi;
    }

}
