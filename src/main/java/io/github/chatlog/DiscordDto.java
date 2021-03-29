package io.github.chatlog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class DiscordDto implements Serializable {

    private static final long serialVersionUID = -1338619700298436845L;
    @Id
    private Long id;
    private Message discordMessage;

    public DiscordDto(Message discordMessage) {
        this.discordMessage = discordMessage;
    }

    public String getMessage() {
        return discordMessage.getContent();
    }

    public String getAuthor(){
        return discordMessage.getAuthor().getDisplayName();
    }

    public String getDestinatary() {
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
