package com.software.basement.tron.server.game;

import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
public class Bonus {
    private BonusType type;
    private Point position;
}
