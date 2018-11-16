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

            System.out.println("Please enter 1 to get paths with exact " + steps
                    + " steps, any char to get min path with max "
                    + steps + " steps");

            int calcChoice = Integer.parseInt(scanner.nextLine());
            if(calcChoice == 1){
                printExactSteps(steps, pieceMovesStrategy, start, end);
            } else {
                printMinSteps(steps, pieceMovesStrategy, start, end);
            }


        } catch (IncorrectPositionException e){
            System.out.println("You've entered invalid position");
        }
        catch(Exception e){
            System.out.println("Unexpected error occurred");
        }
    }

    private static void printExactSteps(int steps, PieceMovesStrategy pieceMovesStrategy, String start, String end) throws IncorrectPositionException {
        PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
        Set<Path> paths = pathCalculationTemplate.getPaths(steps, new Position(start), new Position(end));
        if(paths.isEmpty()){
            System.out.println("No Paths found");
        } else {
            System.out.println("Shortest Path:");
            System.out.println(paths.stream()
                    .sorted((e2, e1) -> e1.size() > e2.size() ? -1 : 1)
                    .findFirst().get().toString());
            System.out.println("\n\nAll Paths Calculated:");
            paths.forEach(System.out::println);
        }
    }

    private static void printMinSteps(int stepLimit, PieceMovesStrategy pieceMovesStrategy, String start, String end) throws IncorrectPositionException {
        for(int steps = 1 ; steps <= stepLimit; steps++){
            PathCalculationTemplate pathCalculationTemplate = new SimplePathCalculationTemplate(pieceMovesStrategy);
            Set<Path> paths = pathCalculationTemplate.getPaths(steps, new Position(start), new Position(end));
            if(!paths.isEmpty()){
                System.out.println("Shortest Path:");
                System.out.println(paths.stream()
                        .sorted((e2, e1) -> e1.size() > e2.size() ? -1 : 1)
                        .findFirst().get().toString());
                return;
            }
        }
        System.out.println("No Paths found");
    }
}
