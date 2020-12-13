package bullscows;


import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        boolean error = false;
        Scanner scanner = new Scanner(System.in);
        int size = 0;

        System.out.println("Input the length of the secret code:");
        String response = scanner.next();
        try {
            size = Integer.parseInt(response);
            if (size <= 0){
                System.out.println("error");
                error = true;
            }else if (size > 36) {
                System.out.println("Error: can't generate a secret " +
                        "number with a length of " + size + " because there aren't enough unique digits.");
                error = true;
            }
        }catch (NumberFormatException e) {
            System.out.println("Error: \"" + response + "\" isn't a valid number.");
            error = true;
        }


        int number = 0;
        if(!error) {

            System.out.println("Input the number of possible symbols in the code:");
            response = scanner.next();
            try {
                number = Integer.parseInt(response);
                if (number <= 0) {
                    System.out.println("error");
                    error = true;
                } else if (number > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    error = true;
                } else if (number < size) {
                    System.out.println("Error: it's not possible to generate a " +
                            "code with a length of " + size + " with " + number + " unique symbols.");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Error: \"" + response + "\" isn't a valid number.");
                error = true;
            }
        }

        if(!error) {
            Game game = new Game(size);

            game.generateSecret(number);
            String letters = "";
            if (number < 11) {
                letters = "(0-" + (number - 1) + ")";
            } else {
                char c = (char) ('a' + number - 11);
                letters = "(0-9,a-" + c + ")";
            }
            System.out.println("The secret is prepared: " + "*".repeat(size) + " " + letters + ".");
            System.out.println("Okay, let's start a game!");


            boolean play = true;
            int turn = 1;
            String user = "";
            while (play) {
                System.out.printf("turn %d :\n", turn++);
                do {
                    user = scanner.next();
                } while (user.length() < size);

                int[] grades = game.grade(user);
                int bull = grades[0];
                int cow = grades[1];

                play = bull != size;

                StringBuilder builder = new StringBuilder();
                builder.append("Grade: ");

                if (bull == 0 && cow == 0) {
                    builder.append("None");
                }
                if (bull != 0) {
                    builder.append(bull + " bull" + (bull > 1 ? "s" : ""));
                    if (cow != 0) {
                        builder.append(" and ");
                    }
                }
                if (cow != 0) {
                    builder.append(cow + " cow" + (cow > 1 ? "s" : ""));
                }
                System.out.println(builder.toString());
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }
}
