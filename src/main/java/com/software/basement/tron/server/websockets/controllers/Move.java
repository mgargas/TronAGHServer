package com.software.basement.tron.server.websockets.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {

    private Integer id;
    private Integer turn;
}
