package io.github.chatlog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DISCORD_TOKEN")
public class DiscordToken implements Serializable {

    private static final long serialVersionUID = -8972494130465697316L;
    @Id
    private final Long id = 1L;
    @Column(name = "TOKEN")
    private String token;

    public DiscordToken(String token) {
        this.token = token;
    }

}
