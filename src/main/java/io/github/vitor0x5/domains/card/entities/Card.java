package io.github.vitor0x5.domains.card.entities;

import io.github.vitor0x5.domains.card.CardTypes;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import io.github.vitor0x5.shared.converters.enums.CardTypeEnumConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cards")
public class Card  extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String nickname;
    private String provider;

    @Convert(converter = CardTypeEnumConverter.class)
    private CardTypes type;
}
