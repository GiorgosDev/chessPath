package com.gio.chess.path.calculation;

import com.gio.chess.path.model.Path;
import com.gio.chess.path.model.Position;

import java.util.Set;

public interface PathCalculationTemplate {

    Set<Path> getPaths(int steps, Position start, Position end);

}
