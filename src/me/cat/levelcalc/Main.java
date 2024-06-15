package me.cat.levelcalc;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    private static final int EXP_CONST = 10_672_500;

    private static int currentExpEarned;
    private static int expLeft;
    private static int expToSubtract;
    private static int avgExpPerGame;
    private static double daysSpan;

    private static double avgGameTime;

    public static void main(String[] args) {
        try {
            getInfo();
        } catch (Exception ex) {
            System.err.println("| Error! I was expecting a number, or something else");
            return;
        }
        printResults();
    }

    private static void getInfo() {
        Scanner scanner = new Scanner(System.in);
        newLine();

        out("| Enter your current ExpEarned:");
        currentExpEarned = scanner.nextInt();
        expLeft = EXP_CONST - currentExpEarned;
        newLine();

        out("| Enter any exp you wish to subtract:");
        expToSubtract = scanner.nextInt();
        newLine();

        out("| Enter avg. exp earned per game\n" + "  from the game you play:");
        avgExpPerGame = scanner.nextInt();
        newLine();

        out("| Enter the time span for achieving this (days):");
        daysSpan = scanner.nextDouble();
        newLine();

        out("| Enter avg. time it takes to complete a game (minutes):");
        avgGameTime = scanner.nextDouble();
        newLine();

        scanner.close();
    }

    private static void printResults() {
        out("------------------------------------");
        out("Games left to level 100       : " + format(calculateGamesLeft()));
        out("Games required per day        : " + format(calculateGamesPerDay()));
        out("Time it'll take daily (hours) : " + format(calculateDailyTimeRequirement()) + 'h');
        out("Percentage to level 100       : " + format(calculatePercentage()) + '%');
        out("Exp left                      : " + format(expLeft));
        out("------------------------------------");
    }

    public static double calculateGamesLeft() {
        return ((double) expLeft - expToSubtract) / avgExpPerGame;
    }

    public static double calculateGamesPerDay() {
        return (((double) expLeft - expToSubtract) / avgExpPerGame) / daysSpan;
    }

    public static double calculateDailyTimeRequirement() {
        return (calculateGamesPerDay() * avgGameTime) / 60.0d;
    }

    public static double calculatePercentage() {
        return ((double) currentExpEarned / EXP_CONST) * 100.0d;
    }

    private static void out(String s) {
        System.out.println(s);
    }

    private static void newLine() {
        out(System.lineSeparator());
    }

    private static String format(double number) {
        String formatted = new DecimalFormat("#.#")
                .format(number);
        return NumberFormat.getInstance().format(Double.parseDouble(formatted));
    }
}