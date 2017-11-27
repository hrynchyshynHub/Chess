package com.chess.util;

import com.chess.model.Cell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ivan.hrynchyshyn on 17.11.2017.
 */
@AllArgsConstructor
@Getter
@Setter
public class Move {
    private Cell source;
    private Cell destination;
}
