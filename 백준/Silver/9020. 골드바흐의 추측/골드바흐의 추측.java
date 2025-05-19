import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = 10000;
    static int T;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        isPrime = new boolean[MAX + 1];
        Arrays.fill(isPrime, true);

        sieveOfEratosthenes(MAX);

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            int primeA = 0;
            int primeB = 0;

            for (int j = n / 2; j >= 2; j--) {
                if (isPrime[j] && isPrime[n - j]) {
                    primeA = j;
                    primeB = n - j;
                    break;
                }
            }

            sb.append(primeA).append(" ").append(primeB).append("\n");
        }

        System.out.print(sb);
    }

    public static void sieveOfEratosthenes(int n) {
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) {
                continue;
            }

            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }
}