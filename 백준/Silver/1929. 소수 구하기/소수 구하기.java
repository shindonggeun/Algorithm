import java.util.*;
import java.io.*;

public class Main {

    static int M;
    static int N;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        sieveOfEratosthenes(N);

        StringBuilder sb = new StringBuilder();
        for (int i = M; i <= N; i++) {
            if (isPrime[i]) {
                sb.append(i).append("\n");
            }
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