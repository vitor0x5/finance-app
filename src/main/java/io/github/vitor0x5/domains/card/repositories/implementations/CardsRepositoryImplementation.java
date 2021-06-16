package io.github.vitor0x5.domains.card.repositories.implementations;

import io.github.vitor0x5.domains.card.entities.Card;
import io.github.vitor0x5.domains.card.repositories.CardsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardsRepositoryImplementation  extends CardsRepository, JpaRepository<Card, UUID> {
}
