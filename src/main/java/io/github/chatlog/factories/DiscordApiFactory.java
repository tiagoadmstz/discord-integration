package io.github.chatlog.factories;

import io.github.chatlog.utils.InstanceControl;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class DiscordApiFactory {

    public static final String DISCORDAPI = "discordApi";

    public DiscordApi discordApiV1(String token) {
        if (token != null && !getIntanceStatus()) {
            DiscordApi discordApi = new DiscordApiBuilder()
                    .setToken(token)
                    .login()
                    .join();
            InstanceControl.putInstance(DISCORDAPI, discordApi);
            return discordApi;
        }
        return InstanceControl.getInstance(DISCORDAPI);
    }

    private boolean getIntanceStatus() {
        try {
            return "online".equalsIgnoreCase(InstanceControl.<DiscordApi>getInstance(DISCORDAPI).getStatus().getStatusString());
        } catch (Exception ex) {
            return false;
        }
    }

}
