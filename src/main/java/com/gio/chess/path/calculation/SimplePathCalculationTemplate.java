package com.gio.chess.path.calculation;

import com.gio.chess.path.model.Path;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.moves.PieceMovesStrategy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SimplePathCalculationTemplate implements PathCalculationTemplate {

    PieceMovesStrategy pieceMovesStrategy;

    public SimplePathCalculationTemplate(PieceMovesStrategy pieceMovesStrategy) {
        this.pieceMovesStrategy = pieceMovesStrategy;
    }

    @Override
    public Set<Path> getPaths(int steps, Position start, Position end) {
        Set<Path> leftSet = new HashSet<>();
        Set<Path> rightSet = new HashSet<>();
        leftSet.add(new Path(start));
        rightSet.add(new Path(end));

        // calculate positions from both directions
        for (int i = 0; i < steps/2; i++) {
            leftSet = expandPathsOneStep(leftSet);
            rightSet = expandPathsOneStep(rightSet);
        }

        //make additional step if needed
        if(steps % 2 != 0){
            leftSet = expandPathsOneStep(leftSet);
        }

        //calculate intersecting paths
        Set<Position> intersection = leftSet.stream()
                .map(Path::getPosition)
                .collect(Collectors.toSet());
        Set<Position> rightPositions = rightSet.stream()
                .map(Path::getPosition)
                .collect(Collectors.toSet());

        intersection.retainAll(rightPositions);

        //join left and right paths
        Set<Path> leftSetToJoin = leftSet.stream()
                .filter(path -> intersection.contains(path.getPosition()))
                .collect(Collectors.toSet());
        Set<Path> rightSetToJoin = rightSet.stream()
                .filter(path -> intersection.contains(path.getPosition()))
                .collect(Collectors.toSet());

        Set<Path> resultSet = new HashSet<>();

        for(Path leftPath : leftSetToJoin){
            for(Path rightPath : rightSetToJoin){
                if(leftPath.getPosition().equals(rightPath.getPosition())){
                    resultSet.add(leftPath.appendAll(rightPath));
                }
            }
        }

        return resultSet;
    }



    public Set<Path> expandPathsOneStep(Set<Path> paths){
        return paths.stream().map(this::pathOneMoreStep).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public Set<Path> pathOneMoreStep(Path path) {
        Position lastPosition = path.getPosition();
        return pieceMovesStrategy.getMovesAvailable(lastPosition).stream().map(path::copyAndAppend).collect(Collectors.toSet());
    }

}