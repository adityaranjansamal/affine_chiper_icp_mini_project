import java.util.*;

public class AffineChiper {
    static int A;
    static int B;

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String message = sc1.nextLine();
        System.out.println("Enter the multiplicative key (key1)");
        int mod = 26, count = 0;
        while (true) {
            A = sc2.nextInt();
            for (int i = 2; i <= A; i++) {
                if (A % i == 0 && mod % i == 0) {
                    count++;
                    break;
                }
            }
            if (count == 1) {
                System.out.println("Multiplicative key and modulo must be co-primes. Enter multiplicative key again");
                count = 0;
                continue;
            } else
                break;
        }
        System.out.println("Enter the additive key (key2)");
        B = sc2.nextInt();
        message = message.toLowerCase();
        String encryptedMessage = encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static String encrypt(String plaintext) {
        char[] chars = plaintext.toCharArray();
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : chars) {
            int x = (A * (c - 'a') + B) % 26;
            encryptedMessage.append((char) (x + 'a'));
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String chipertext) {
        char[] chars = chipertext.toCharArray();
        StringBuilder decryptedMessage = new StringBuilder();
        int aInverse = findAInverse();
        for (char c : chars) {
            int x = (aInverse * (c - 'a' - B + 26)) % 26;
            decryptedMessage.append((char) (x + 'a'));
        }
        return decryptedMessage.toString();
    }

    public static int findAInverse() {
        for (int i = 0; i < 26; i++) {
            if ((A * i) % 26 == 1) {
                return i;
            }
        }
        return 0;
    }
}
