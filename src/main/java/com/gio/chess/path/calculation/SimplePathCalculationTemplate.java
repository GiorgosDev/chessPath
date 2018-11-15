package com.gio.chess.path.calculation;

import com.gio.chess.path.model.Path;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.moves.PieceMovesStrategy;

import java.util.HashSet;
import java.util.Set;

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

        Set<Position> intersection = calculateIntersection(leftSet, rightSet);

        //join left and right paths
        Set<Path> leftSetToJoin = filterPathBySetOfEndPositions(leftSet, intersection);
        Set<Path> rightSetToJoin = filterPathBySetOfEndPositions(rightSet, intersection);

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

    private Set<Position> calculateIntersection(Set<Path> leftSet, Set<Path> rightSet){
        //calculate intersecting paths
        Set<Position> intersection = new HashSet<>();
        for (Path path : leftSet) {
            Position position = path.getPosition();
            intersection.add(position);
        }
        Set<Position> rightPositions = new HashSet<>();
        for (Path path : rightSet) {
            Position position = path.getPosition();
            rightPositions.add(position);
        }

        intersection.retainAll(rightPositions);

        return intersection;

    }

    private Set<Path> filterPathBySetOfEndPositions(Set<Path> paths,  Set<Position> intersection){
        Set<Path> set = new HashSet<>();
        for (Path path : paths) {
            if (intersection.contains(path.getPosition())) {
                set.add(path);
            }
        }
        return set;
    }


    private Set<Path> expandPathsOneStep(Set<Path> paths){
        Set<Path> set = new HashSet<>();
        for (Path path : paths) {
            Set<Path> pathSet = pathOneMoreStep(path);
            for (Path path1 : pathSet) {
                set.add(path1);
            }
        }
        return set;
    }

    private Set<Path> pathOneMoreStep(Path path) {
        Position lastPosition = path.getPosition();
        Set<Path> set = new HashSet<>();
        for (Position position : pieceMovesStrategy.getMovesAvailable(lastPosition)) {
            Path copyAndAppend = path.copyAndAppend(position);
            set.add(copyAndAppend);
        }
        return set;
    }

}
