package io.github.chatlog.repositories;

import io.github.chatlog.models.DiscordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordTokenRepository extends JpaRepository<DiscordToken, Long> {
}
