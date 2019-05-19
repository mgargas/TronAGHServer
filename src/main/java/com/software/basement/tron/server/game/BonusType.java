package com.software.basement.tron.server.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BonusType {
    IMMORTALITY(100, "immortality"), SPEED_UP(200, "speedUp");

    private final int id;
    private final String name;
}
