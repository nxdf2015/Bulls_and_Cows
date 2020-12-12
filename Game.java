package bullscows;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private String secret;
    private int size;

    private static final int SEED = 1024;


    public Game(int size) {
        this.size = size;
    }
    public int[] grade(String user) {
        int bull = 0;
        int cow = 0;
        for(int i = 0; i < secret.length(); i++){
            char c = user.charAt(i);
            int id = secret.indexOf(c);
            if (id == -1) {
                continue;
            }else {
                if( id == i){
                    bull++;
                }
                cow++;
            }
        }
       return new int[] {bull, cow};
    }

    public void generateSecret(){
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();
        String secret = "";
        while(set.size() < size){
            int value;
            do {
                value = random.nextInt(10);
            } while( set.contains(value));
            set.add(value);
            secret += value;
        }

        this.secret = secret;
    }
}
