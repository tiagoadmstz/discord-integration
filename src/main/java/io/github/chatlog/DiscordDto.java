package io.github.chatlog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DISCORD_MESSAGE_LOG")
@SequenceGenerator(name = "DISCORD_MESSAGE_LOG_SEQ", allocationSize = 1)
public class DiscordDto implements Serializable {

    private static final long serialVersionUID = -1338619700298436845L;
    @Id
    @GeneratedValue(generator = "DISCORD_MESSAGE_LOG_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_LOG", columnDefinition = "timestamp")
    private Date dateLog;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "DESTINATARY")
    private String destinatary;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "SERVER")
    private String server;
    @Transient
    private Message discordMessage;

    public DiscordDto(Message discordMessage) {
        this.discordMessage = discordMessage;
        this.dateLog = new Date();
        this.author = discordMessage.getAuthor().getDisplayName();
        this.destinatary = getMentionedUser();
        this.message = discordMessage.getContent().replaceAll("<@!.*>", "");
        this.channel = discordMessage.getChannel().getIdAsString();
        this.server = discordMessage.getServer().get().getName();
    }

    @Transient
    private String getMentionedUser() {
        String destinatary = "";
        List<User> mentionedUsers = discordMessage.getMentionedUsers();
        if (mentionedUsers != null) {
            if (mentionedUsers.size() > 0) {
                destinatary = mentionedUsers.get(0).getName();
            }
        }
        return destinatary;
    }
}
