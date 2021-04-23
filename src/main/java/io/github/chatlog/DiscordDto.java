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
import java.util.stream.Collectors;

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
    @Column(name = "DESTINATARY", columnDefinition = "text")
    private String destinatary;
    @Column(name = "MESSAGE", columnDefinition = "text")
    private String message;
    @Column(name = "CHANNEL_ID")
    private String channelId;
    @Column(name = "CHANNEL_NAME")
    private String channelName;
    @Column(name = "SERVER_ID")
    private String serverId;
    @Column(name = "SERVER_NAME")
    private String serverName;
    @Transient
    private Message discordMessage;

    public DiscordDto(Message discordMessage) {
        this.discordMessage = discordMessage;
        this.dateLog = new Date();
        this.author = discordMessage.getAuthor().getDisplayName();
        this.destinatary = getMentionedUsers();
        this.message = disgestMentionedUser();
        this.channelId = discordMessage.getChannel().getIdAsString();
        this.channelName = discordMessage.getServerTextChannel().get().getName();
        this.serverId = discordMessage.getServer().get().getIdAsString();
        this.serverName = discordMessage.getServer().get().getName();
    }

    @Transient
    private String getMentionedUsers() {
        if(discordMessage.getMentionedUsers().isEmpty()){
            return "Todos";
        }
        return discordMessage.getMentionedUsers().stream().map(User::getName).collect(Collectors.joining(";"));
    }

    @Transient
    private String disgestMentionedUser(){
        String digestedMessage = discordMessage.getContent();
        for(User user : discordMessage.getMentionedUsers()){
            digestedMessage = digestedMessage.replaceAll("\\<\\@\\!" + user.getIdAsString() + "\\>", user.getName());
        }
        return digestedMessage.replaceAll("<@!.*>", "");
    }

}
