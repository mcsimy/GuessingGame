import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public int rangeLower = 0;
    public int rangeUpper = 50;

    private boolean newNumber = true;
    private boolean correctAnswer;
    private int secretNumber;

    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }

    public void start(){

        while (newNumber){
            secretNumber = generateNewNumber(rangeLower, rangeUpper);
            correctAnswer = false;
            showUI();

            while (!correctAnswer){
                isNumberSameAsSecret();
            }
            askToPlayAgain();
        }
    }

    private void showUI(){
        System.out.println("I imagined a number between " + rangeLower + " and " + rangeUpper
                + ". Try to guess it! ('q' to quit)");
    }

    private int generateNewNumber(int rangeLower, int rangeUpper){

        if (rangeLower >= rangeUpper) {
            throw new IllegalArgumentException("rangeUpper must be greater than rangeLower");
        }

        Random random = new Random();
        return random.nextInt((rangeUpper - rangeLower) +1) + rangeLower;
    }

    private String askForNumber(){
        String inputData;

        Scanner scanner = new Scanner(System.in);

        inputData = scanner.nextLine();

        if (inputData.equals("q")) System.exit(0);

        return inputData;

    }

    private boolean isNumberSameAsSecret(){
        boolean validNumber = true;
        int playerNumber = -1;

        do {
            String inputString = askForNumber();
            try {
                playerNumber = Integer.parseInt(inputString);
                validNumber = true;
            } catch (NumberFormatException ex) {
                validNumber = false;
                System.out.println("This is not a valid number. Play fairly.");
            }
        } while (!validNumber);
        if ((secretNumber - playerNumber) > 0){
            System.out.println("My number is bigger..");
        }

        else if ((secretNumber - playerNumber) < 0){
            System.out.println("My number is smaller..");
        }

        else if ((secretNumber - playerNumber) == 0) {
            correctAnswer = true;
            System.out.println("Bullseye!");
        }

        else System.out.println("Something went wrong, we cannot compare numbers...");

        return correctAnswer;
    }

    private void askToPlayAgain(){

        System.out.println("Let's play again!");
    }
}
