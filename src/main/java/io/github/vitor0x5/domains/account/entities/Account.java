package io.github.vitor0x5.domains.account.entities;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import io.github.vitor0x5.shared.converters.monetary.MonetaryTypeAttributeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String nickname;
    private String bank;

    @Convert(converter = MonetaryTypeAttributeConverter.class)
    private BigDecimal balance;
}
