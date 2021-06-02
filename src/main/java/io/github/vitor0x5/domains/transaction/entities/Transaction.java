package io.github.vitor0x5.domains.transaction.entities;

import io.github.vitor0x5.domains.transaction.TransactionTypes;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import io.github.vitor0x5.shared.converters.enums.TransactionTypeEnumConverter;
import io.github.vitor0x5.shared.converters.monetary.MonetaryTypeAttributeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    @Type(type = "pg-uuid")
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String source;
    private String description;

    @Convert(converter = TransactionTypeEnumConverter.class)
    private TransactionTypes type;

    @Convert(converter = MonetaryTypeAttributeConverter.class)
    private BigDecimal value;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;
}
