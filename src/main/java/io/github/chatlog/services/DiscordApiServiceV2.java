package io.github.chatlog.services;

import io.github.chatlog.factories.DiscordApiFactory;
import io.github.chatlog.models.DiscordDto;
import io.github.chatlog.models.DiscordToken;
import io.github.chatlog.repositories.DiscordRepository;
import io.github.chatlog.repositories.DiscordTokenRepository;
import io.github.chatlog.utils.InstanceControl;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscordApiServiceV2 {

    private Logger logger = LoggerFactory.getLogger(DiscordApiServiceV2.class);
    @Autowired
    private DiscordRepository discordRepository;
    @Autowired
    private DiscordTokenRepository tokenRepository;
    @Autowired
    private DiscordTokenService tokenService;
    private final String SERVICE_NAME = "discord-listener";

    public DiscordApiServiceV2() {
        logger.info("Initializing Discord Service");
    }

    public void discordInit(){
        this.discordInit(tokenRepository.findById(1L).orElse(new DiscordToken("")).getToken());
    }

    public void discordInit(String token) {
        InstanceControl.putInstance(SERVICE_NAME, this);
        discordListenerInit(token);
    }

    public boolean getDiscordStatus() {
        String statusString = "";
        DiscordApi discordApi = InstanceControl.getInstance(DiscordApiFactory.DISCORDAPI);
        try {
            statusString = discordApi.getStatus().getStatusString();
        } catch (Exception ex) {
        }
        return "online".equalsIgnoreCase(statusString);
    }

    private void discordListenerInit(String token) {
        try {
            DiscordApiFactory discordApiFactory = new DiscordApiFactory();
            logger.info("Initializing Discord Listener");
            String token1 = tokenRepository.findById(1L).orElse(new DiscordToken("")).getToken();
            DiscordApi discordApi = discordApiFactory.discordApiV1(token1 == null ? token : token1);
            if (discordApi != null) {
                discordApi.addMessageCreateListener(event -> {
                    DiscordDto discordDto = new DiscordDto(event.getMessage());
                    printDiscordMessage(discordDto);
                    saveMessageLog(discordDto);
                    logger.info(discordDto.toString());
                });
            }
            tokenService.saveToken(token);
            logger.info("Discord Listener Initialized");
        } catch (Exception ex) {
            ex.printStackTrace();
            InstanceControl.putInstance("discordApi", null);
            logger.info("Discord listener not initialized");
        }
    }

    private void printDiscordMessage(DiscordDto discordDto) {
        if (!"".equals(discordDto.getDestinatary())) {
            System.out.println(String.format("Send by %s to %s", discordDto.getAuthor(), discordDto.getDestinatary()));
        } else {
            System.out.println(String.format("Send by %s", discordDto.getAuthor()));
        }
        System.out.println(String.format("Message received: %s", discordDto.getMessage()));
    }

    private void saveMessageLog(DiscordDto discordDto) {
        discordRepository.save(discordDto);
    }

}
