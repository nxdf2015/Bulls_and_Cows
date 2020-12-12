package bullscows;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");

        int size;
        do {
          size = scanner.nextInt();
          if (size > 9){
              System.out.println("Error: can't generate a secret " +
                      "number with a length of 11 because there aren't enough unique digits.");
          }
        }while (size > 9);
        System.out.println("Okay, let's start a game!");

        Game game = new Game(size);

        game.generateSecret();

        boolean play = true;
        int turn = 1;
        String user = "";
        while(play) {
            System.out.printf("turn %d :\n",turn++);
            do {
                user = scanner.next();
            } while ( user.length() < size);

            int[] grades = game.grade(user);
            int bull = grades[0];
            int cow = grades[1];

            play = bull != size;

            StringBuilder builder = new StringBuilder();
            builder.append("Grade: ");
            
            if (bull == 0 && cow == 0){
                builder.append("None");
            }
            if(bull != 0){
                builder.append(  bull + " bull" + (bull > 1 ? "s" : ""));
                if (cow != 0) {
                    builder.append(" and");
                }
            }
            if(cow != 0){
                builder.append(  cow + " cow" + (cow > 1 ? "s" : ""));
            }
            System.out.println(builder.toString());
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
