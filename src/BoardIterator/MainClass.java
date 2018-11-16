package BoardIterator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MainClass {
    public static void main(String[] args) {
        argumentParsing(args);
    }

    private static void argumentParsing(String[] args) {
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
            System.out.println("First argument must be the board size");
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

        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter("bf_java_results.txt");
            bw = new BufferedWriter(fw);
            bw.write("");
        } catch(IOException e) {
            e.printStackTrace();
        }

        // No option arguments passed -> Just print the solutions
        if(!tFlag && !taFlag && !tfFlag) {
            new BoardUtils(boardSize).printAllSolutions(new BoardIterator(boardSize));
            System.exit(0);
        }

        String outputStr;
        try {
            if (tFlag) {
                outputStr = "Printing ALL solutions: " + getPrintingTime(boardSize) + " s";
                handleOutput(outputStr, bw);
            }

            if (taFlag) {
                outputStr = "Finding ALL solutions: " + getAllSolutionsTime(boardSize) + " s";
                handleOutput(outputStr, bw);
            }

            if (tfFlag) {
                outputStr = "Finding FIRST solution: " + getFirstSolutionTime(boardSize) + " s";
                handleOutput(outputStr, bw);
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleOutput(String outputStr, BufferedWriter bw) throws IOException {
        System.out.println(outputStr);
        bw.append(outputStr);
        bw.newLine();
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
