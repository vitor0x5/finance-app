package io.github.vitor0x5.domains.card.dtos;

import io.github.vitor0x5.domains.card.utils.type.CardTypeValidation;
import javax.validation.constraints.NotNull;

public class CreateCardDTO {

    public String nickname;

    @NotNull(message = "The provider can't be null")
    public String provider;

    @CardTypeValidation
    @NotNull(message = "The type can't be null")
    public String type;
}
