package io.github.chatlog;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DiscordApiFactory {

    @Bean
    public DiscordApi discordApi() {
        DiscordApi discordApi = new DiscordApiBuilder()
                .setToken("token")
                .login()
                .join();
        return discordApi;
    }

}
