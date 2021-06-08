package io.github.chatlog.repositories;

import io.github.chatlog.models.DiscordDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordRepository extends JpaRepository<DiscordDto, Long> {
}
