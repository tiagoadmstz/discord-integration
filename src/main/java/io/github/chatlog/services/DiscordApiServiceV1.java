package io.github.chatlog.services;

import io.github.chatlog.models.DiscordDto;
import io.github.chatlog.repositories.DiscordRepository;
import org.javacord.api.DiscordApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class DiscordApiServiceV1 {

    private Logger logger = LoggerFactory.getLogger(DiscordApiServiceV1.class);
    private DiscordApi discordApi;
    private DiscordRepository discordRepository;

    //@Autowired
    public DiscordApiServiceV1(DiscordApi discordApi, DiscordRepository discordRepository) {
        logger.info("Initializing Discord Service");
        this.discordApi = discordApi;
        this.discordRepository = discordRepository;
        discordListenerInit();
    }

    public void discordListenerInit() {
        logger.info("Initializing Discord Listener");
        discordApi.addMessageCreateListener(event -> {
            DiscordDto discordDto = new DiscordDto(event.getMessage());
            printDiscordMessage(discordDto);
            saveMessageLog(discordDto);
            logger.info(discordDto.toString());
        });
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
