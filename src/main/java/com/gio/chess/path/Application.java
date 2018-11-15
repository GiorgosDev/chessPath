package com.gio.chess.path;

import com.gio.chess.path.calculation.PathCalculationTemplate;
import com.gio.chess.path.calculation.SimplePathCalculationTemplate;
import com.gio.chess.path.exceptions.IncorrectPositionException;
import com.gio.chess.path.model.Path;
import com.gio.chess.path.model.Position;
import com.gio.chess.path.moves.KingMovesStrategy;
import com.gio.chess.path.moves.KnightMovesStrategy;
import com.gio.chess.path.moves.PieceMovesStrategy;

import java.util.Set;

public class Application {
    public static void main(String args[]){
        System.out.println("Welcome to Chess Path Calculator");
        System.out.println("Please enter start position");

        try(java.util.Scanner scanner = new java.util.Scanner(System.in)) {


            String start = scanner.nextLine();

            System.out.println("Please enter end position");

            String end = scanner.nextLine();

            System.out.println("Please number of steps");

            int steps = Integer.parseInt(scanner.nextLine());

            PieceMovesStrategy pieceMovesStrategy;

            System.out.println("Please enter 1 to get paths for Knight, 2 to get paths for King");

            int selectedPiece = Integer.parseInt(scanner.nextLine());
            if(selectedPiece == 1)
                pieceMovesStrategy  = new KnightMovesStrategy();
            else if (selectedPiece == 2)
                pieceMovesStrategy  = new KingMovesStrategy();
            else {
                System.out.println("Incorrect piece type");
                return;
            }
            PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
            Set<Path> paths = pathCalculationTemplate.getPaths(steps, new Position(start), new Position(end));
            if(paths.isEmpty()){
                System.out.println("No Paths found");
            } else {
                System.out.println("Paths Calculated:");
                paths.forEach(System.out::println);
            }



        } catch (IncorrectPositionException e){
            System.out.println("You've entered invalid position");
        }
        catch(Exception e){
            System.out.println("Unexpected error occurred");
        }
    }
}
