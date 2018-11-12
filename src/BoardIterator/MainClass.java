package BoardIterator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        argumentParsing(args);
    }

    public static void argumentParsing(String[] args) {
        if(args.length < 1) {
            System.out.println("Missing mandatory argument");
            System.exit(0);
        }

        boolean tFlag = false;
        boolean taFlag = false;
        boolean tfFlag = false;
        int boardSize = 0;

        try {
            boardSize = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("First argument must be board size");
            System.exit(1);
        }

        for(String arg : args) {
            if(arg.equals("-t")) {
                tFlag = true;
            } else if(arg.equals("-ta")) {
                taFlag = true;
            } else if(arg.equals("-tf")) {
                tfFlag = true;
            }
        }

        if(!tFlag && !taFlag && !tfFlag) {
            new BoardUtils(boardSize).printAllSolutions(new BoardIterator(boardSize));
            System.exit(0);
        }

        if(tFlag) {
            System.out.println("Printing ALL solutions: " + getPrintingTime(boardSize) + " s");
        }

        if(taFlag) {
            System.out.println("Finding ALL solutions: " + getAllSolutionsTime(boardSize) + " s");
        }

        if(tfFlag) {
            System.out.println("Finding FIRST solution: " + getFirstSolutionTime(boardSize) + " s");
        }
    }
    
    public static double getPrintingTime(int boardSize) {
        BoardIterator boardIterator = new BoardIterator(boardSize);
        BoardUtils boardUtils = new BoardUtils(boardSize);

        Instant start = Instant.now();
        boardUtils.printAllSolutions(boardIterator);
        return (double)start.until(Instant.now(), ChronoUnit.MILLIS)/1000;
    }

    public static double getFirstSolutionTime(int boardSize) {
        BoardIterator boardIterator = new BoardIterator(boardSize);
        BoardUtils boardUtils = new BoardUtils(boardSize);

        Instant start = Instant.now();
        boardUtils.findFirstSolution(boardIterator);
        return (double)start.until(Instant.now(), ChronoUnit.MILLIS)/1000;
    }

    public static double getAllSolutionsTime(int boardSize) {
        BoardIterator boardIterator = new BoardIterator(boardSize);
        BoardUtils boardUtils = new BoardUtils(boardSize);

        Instant start = Instant.now();
        boardUtils.findAllSolutions(boardIterator);
        return (double)start.until(Instant.now(), ChronoUnit.MILLIS)/1000;
    }
}
