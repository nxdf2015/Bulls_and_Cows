package bullscows;

import java.util.Random;

public class Symbols {
    private int number;
    private Random random;
    public Symbols(int number) {
        this.number = number;
        random = new Random();
    }

    public char next() {
        int n = random.nextInt(number );
        if (n < 10){
            return   (char) ('0' + n );
        } else {
            return (char) ('a' + n - 10);
        }
    }
}
