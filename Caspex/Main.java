package Caspex;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            String p = scanner.nextLine();

            int k = scanner.nextInt();

            StringBuilder password = new StringBuilder();

            final int LOWER_BOUND = 65;
            final int UPPER_BOUND = 126;
            final int RANGE_SIZE = UPPER_BOUND - LOWER_BOUND + 1;

            for (char c : p.toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    int originalAscii = (int) c;
                    int displacedAscii = originalAscii + k;
                    int offset = displacedAscii - LOWER_BOUND;

                    int wrappedOffset = (offset % RANGE_SIZE + RANGE_SIZE) % RANGE_SIZE;
                    int finalAscii = LOWER_BOUND + wrappedOffset;

                    password.append((char) finalAscii);
                } else {
                    password.append(c);
                }

            System.out.println(password.toString());

            scanner.close();
        }
    }
}
