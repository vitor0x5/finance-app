package io.github.vitor0x5.domains.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.vitor0x5.domains.account.entities.Account;
import io.github.vitor0x5.domains.card.entities.Card;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.shared.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(targetEntity = Card.class, mappedBy = "user")
    private List<Card> cards = new ArrayList<>();

    @OneToMany(targetEntity = Account.class, mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();
}

