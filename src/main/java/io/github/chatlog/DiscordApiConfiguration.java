package io.github.chatlog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
@Primary
@ToString
@Configuration
@ConfigurationProperties
@Qualifier(value = "discordapiconfiguration")
public class DiscordApiConfiguration {

    @Value("${discord-api.token}")
    private String token;

}
