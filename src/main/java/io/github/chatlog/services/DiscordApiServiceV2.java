package io.github.chatlog.services;

import io.github.chatlog.factories.DiscordApiFactory;
import io.github.chatlog.models.DiscordDto;
import io.github.chatlog.models.DiscordToken;
import io.github.chatlog.repositories.DiscordRepository;
import io.github.chatlog.repositories.DiscordTokenRepository;
import io.github.chatlog.utils.InstanceControl;
import org.javacord.api.DiscordApi;
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
    private final String THREAD_NAME = "discord-listener";

    public DiscordApiServiceV2() {
        logger.info("Initializing Discord Service");
    }

    public void discordInit() {
        if(InstanceControl.getInstance(THREAD_NAME) == null){
            InstanceControl.putInstance(THREAD_NAME, new Thread(this::discordListenerInit, THREAD_NAME));
            InstanceControl.<Thread>getInstance(THREAD_NAME).start();
        }
    }

    public boolean getDiscordStatus() {
        try {
            return InstanceControl.getInstance("discordApi") != null;
        } catch (Exception ex) {
            return false;
        }
    }

    private void discordListenerInit() {
        try {
            DiscordApiFactory discordApiFactory = new DiscordApiFactory();
            logger.info("Initializing Discord Listener");
            DiscordApi discordApi = discordApiFactory.discordApiV1(tokenRepository.findById(1L).orElse(new DiscordToken("")).getToken());
            if (discordApi != null) {
                discordApi.addMessageCreateListener(event -> {
                    DiscordDto discordDto = new DiscordDto(event.getMessage());
                    printDiscordMessage(discordDto);
                    saveMessageLog(discordDto);
                    logger.info(discordDto.toString());
                });
            }
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
