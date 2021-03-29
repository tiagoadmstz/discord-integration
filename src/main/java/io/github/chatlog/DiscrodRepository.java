package io.github.chatlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscrodRepository extends JpaRepository<DiscordDto, Long> {
}
