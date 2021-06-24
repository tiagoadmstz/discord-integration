package io.github.chatlog.factories;

import io.github.chatlog.utils.InstanceControl;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class DiscordApiFactory {

    private final String DISCORDAPI = "discordApi";

    public DiscordApi discordApiV1(String token) {
        if (token != null && InstanceControl.getInstance(DISCORDAPI) == null) {
            DiscordApi discordApi = new DiscordApiBuilder()
                    .setToken(token)
                    .login()
                    .join();
            InstanceControl.putInstance(DISCORDAPI, discordApi);
            return discordApi;
        }
        return InstanceControl.getInstance(DISCORDAPI);
    }

}
