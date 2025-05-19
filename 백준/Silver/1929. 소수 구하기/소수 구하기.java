import java.util.*;
import java.io.*;

public class Main {

    static int M;
    static int N;
    static boolean[] isPrime; // 각 수가 소수인지 여부를 판별하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        isPrime = new boolean[N + 1]; // [0] ~ [N]
        Arrays.fill(isPrime, true); // 초기 모든 수를 소수(true)로 세팅

        sieveOfEratosthenes(N); // 0부터 N까지의 수에서 소수들은 어떤것들이 있는지 확인하도록 에라토스테네스의 체 메서드 호출

        StringBuilder sb = new StringBuilder();

        // M부터 N까지 소수인 것들 출력하는 과정
        for (int i = M; i <= N; i++) {
            // 해당 수가 소수인 경우
            if (isPrime[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.print(sb);
    }

    // 에라토스테네스의 체 알고리즘을 이용하여 0부터 n까지 소수 판별을 해주는 메서드
    public static void sieveOfEratosthenes(int n) {
        // 0과 1은 소수가 아님 (false)
        isPrime[0] = false;
        isPrime[1] = false;

        // 2부터 n의 제곱근까지 순회
        for (int i = 2; i * i <= n; i++) {
            // 해당 수가 소수가 아닌 경우
            if (!isPrime[i]) {
                continue; // 다음 수로 넘어감
            }

            // i가 소수인 경우, i의 배수들을 소수 아님(false)으로 처리
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false; // 소수 아님
            }
        }
    }
}