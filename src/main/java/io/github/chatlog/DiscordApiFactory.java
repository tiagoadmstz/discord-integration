package io.github.chatlog;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DiscordApiFactory {

    @Autowired
    private DiscordApiConfiguration discordApiConfiguration;

    @Bean
    public DiscordApi discordApi() {
        DiscordApi discordApi = new DiscordApiBuilder()
                .setToken(discordApiConfiguration.getToken())
                .login()
                .join();
        return discordApi;
    }

}
