package com.gio.chess.path.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class Path {
    private ArrayList<Position> positions = new ArrayList<>();

    public Path(Position position) {
        this.positions.add(position);
    }

    public Path(Path patternPath) {
        this.positions = (ArrayList<Position>) patternPath.positions.clone();
    }



    public Path appendAll(Path path){
        ArrayList<Position> clone = (ArrayList<Position>)path.positions.clone();
        Path clonedPath = new Path(this);
        Collections.reverse(clone);
        if(!clone.isEmpty()
                && !clonedPath.positions.isEmpty()
                && clone.get(0).equals(clonedPath.positions.get(clonedPath.positions.size()-1)))
            clonedPath.positions.remove(clonedPath.positions.size()-1);
        clonedPath.positions.addAll(clone);
        return clonedPath;
    }


    public Path copyAndAppend(Position position){
        Path path = new Path(this);
        path.positions.add(position);
        return path;
    }



    public int size(){
        return positions.size();
    }

    public Position getPosition() {
        return positions.isEmpty() ? null : positions.get(positions.size()-1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path1 = (Path) o;
        //checking size of pathes to match
        if(this.size() != path1.size())
            return false;
        //checking every element
        for(int i = 0 ; i < positions.size() ; i++){
            if(!this.positions.get(i).equals(path1.positions.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(Position position : positions){
            hash+= Objects.hash(position);
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Path{" + positions.stream().map(Position::toString).collect(Collectors.joining(", ")) +'}';
    }
}
