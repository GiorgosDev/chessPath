package com.gio.chess.path.model;

import java.util.*;

public class Path {
    private ArrayList<Position> positions = new ArrayList<>();

    public Path(Position position) {
        this.append(position);
    }

    public Path(Path patternPath) {
        this.positions = (ArrayList<Position>) patternPath.positions.clone();
    }

    public void append(Position position){
        positions.add(position);
    }

    public void appendAll(Path path){
        ArrayList<Position> clone = (ArrayList<Position>)path.positions.clone();
        Collections.reverse(clone);
        if(!clone.isEmpty()
                && !positions.isEmpty()
                && clone.get(0).equals(positions.get(positions.size()-1)))
            positions.remove(positions.size()-1);
        positions.addAll(clone);
    }


    public Path copyAndAppend(Position position){
        Path path = new Path(this);
        path.append(position);
        return path;
    }

    public List<Position> getPath() {
        return positions;
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
            if(!this.getPath().get(i).equals(path1.getPath().get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        return Objects.hash(positions);
    }
}
