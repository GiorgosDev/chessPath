package com.gio.chess.path.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Path {
    List<Position> path = new ArrayList<>();

    public void append(Position position){
        path.add(position);
    }

    public List<Position> getPath() {
        return path;
    }

    public int size(){
        return path.size();
    }

    public Position getPosition() {
        return path.size()==0 ? null : path.get(path.size()-1);
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
        for(int i = 0 ; i < path.size() ; i++){
            if(!this.getPath().get(i).equals(path1.getPath().get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        return Objects.hash(path);
    }
}
