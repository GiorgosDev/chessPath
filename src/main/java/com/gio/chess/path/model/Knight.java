package com.gio.chess.path.model;

import com.gio.chess.path.model.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight {

    private Position position;

    public Knight(Position position) {
        this.position = position;
    }

    public Set<Position> getMovesAvailable() {
        return getMovesAvailable(this.position);
    }

    public static Set<Position> getMovesAvailable(Position position) {
        Set<Position> moves = new HashSet<>();
        int leftSign = 1;
        int rightSign = 1;
        int leftShift = 1;
        int rightShift = 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                addPositionIfInRange(new Position(position.getX() + leftShift * leftSign,
                        position.getY() + rightShift * rightSign), moves);
                addPositionIfInRange(new Position(position.getX() + rightShift * rightSign,
                        position.getY() + leftShift * leftSign), moves);
                leftSign *= -1;
            }
            rightSign *= -1;
        }
        return moves;
    }

    private static void addPositionIfInRange(Position position, Set<Position> moves) {
        if (position.getX() >= 0
                && position.getX() <= 7
                && position.getY() >= 0
                && position.getY() <= 7)
            moves.add(position);
    }

    public Set<Path> getPaths(int steps, Position end) {
        Set<Path> leftSet = new HashSet<>();
        Set<Path> rightSet = new HashSet<>();
        leftSet.add(new Path(this.position));
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

        for(Path leftPath : leftSetToJoin){
            for(Path rightPath : rightSetToJoin){
                if(leftPath.getPosition().equals(rightPath.getPosition())){
                    leftPath.appendAll(rightPath);
                }
            }
        }

        return leftSetToJoin;
    }

    public static Set<Path> expandPathsOneStep(Set<Path> paths){
        return paths.stream().map(Knight::pathOneMoreStep).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public static Set<Path> pathOneMoreStep(Path path) {
        Position position = path.getPosition();
        return getMovesAvailable(position).stream().map(path::copyAndAppend).collect(Collectors.toSet());
    }
}
