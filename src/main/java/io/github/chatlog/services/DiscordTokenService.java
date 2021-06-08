package io.github.chatlog.services;

import io.github.chatlog.models.DiscordToken;
import io.github.chatlog.repositories.DiscordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiscordTokenService {

    @Autowired
    private DiscordTokenRepository tokenRepository;

    public ResponseEntity saveToken(String token) {
        tokenRepository.save(new DiscordToken(token));
        return ResponseEntity.ok().build();
    }

}
