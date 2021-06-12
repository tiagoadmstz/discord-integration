package io.github.chatlog.controllers;

import io.github.chatlog.services.DiscordApiServiceV2;
import io.github.chatlog.services.DiscordTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Discord integration endpoints")
@RestController
@RequestMapping("/v1")
public class DiscordApiController {

    @Autowired
    private DiscordTokenService tokenService;
    @Autowired
    private DiscordApiServiceV2 discordApiService;

    @PostMapping("/token")
    @ApiOperation(value = "Integration service activation token", consumes = "String")
    public ResponseEntity tokenUpdate(String token) {
        ResponseEntity responseEntity = tokenService.saveToken(token);
        discordApiService.discordInit();
        return responseEntity;
    }

    @GetMapping("/status")
    @ApiOperation(value = "Returns the current status of the service", response = Boolean.class)
    public ResponseEntity<Boolean> discordApiStatus() {
        return ResponseEntity.ok(discordApiService.getDiscordStatus());
    }

}
