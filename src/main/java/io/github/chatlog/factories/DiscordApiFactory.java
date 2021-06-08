package io.github.chatlog.factories;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class DiscordApiFactory {

    public DiscordApi discordApiV1(String token) {
        if (token != null) {
            DiscordApi discordApi = new DiscordApiBuilder()
                    .setToken(token)
                    .login()
                    .join();
            return discordApi;
        }
        return null;
    }

}
