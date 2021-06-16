package io.github.vitor0x5.domains.card;

import java.util.ArrayList;
import java.util.List;

public enum CardTypes {
    CREDIT("c"),
    BENEFITS("b"),
    OTHER("o");

    private final String type;

    CardTypes(String type) {
        this.type = type;
    }

    public static CardTypes fromString(String type) {
        for (CardTypes t : CardTypes.values()) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }

    public static List<String> toStringList() {
        List<String> typesStringList = new ArrayList<>();
        for (CardTypes type: CardTypes.values()) {
            typesStringList.add(type.toString());
        }
        return typesStringList;
    }

    public String getType() {
        return this.type;
    }
}
