package com.software.basement.tron.server.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum BonusType {
    IMMORTALITY(100, "immortality"), SPEED_UP(200, "speedUp");

    private final int id;
    private final String name;

    public static BonusType fromId(int id) {
        return Arrays.stream(values())
                .filter(bonusType -> bonusType.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
