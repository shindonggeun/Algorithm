import java.util.*;
import java.io.*;

public class Main {

    static final int MAX = 10000; // 문제에서 주어진 n의 최대값
    static int T; // 테스트 케이스 개수
    static boolean[] isPrime; // 소수 여부를 저장하는 배열 (true면 소수)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        isPrime = new boolean[MAX + 1]; // [0] ~ [MAX]
        Arrays.fill(isPrime, true); // 초기 모든 수를 소수라고 세팅

        sieveOfEratosthenes(MAX); // 에라토스테네스의 체로 소수 판별

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        // 각 테스트 케이스마다 골드바흐 파티션을 계산
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine()); // 짝수 n 입력

            int primeA = 0; // 첫 번째 소수
            int primeB = 0; // 두 번째 소수

            // 골드바흐 파티션 찾기 과정: n/2부터 시작하여 대상 소수 쌍 찾기
            // 두 수의 차이가 가장 작은 조합부터 찾게 됨
            for (int j = n / 2; j >= 2; j--) {
                // 해당 두 수들이 모두 소수인 경우
                if (isPrime[j] && isPrime[n - j]) {
                    primeA = j;
                    primeB = n - j;
                    break; // 가장 차이가 적은 조합을 찾았으므로 빠져나옴
                }
            }

            sb.append(primeA).append(" ").append(primeB).append("\n"); // 결과 저장
        }

        System.out.print(sb);
    }

    // 에라토스테네스의 체 알고리즘을 이용하여 소수 판별하는 메서드
    public static void sieveOfEratosthenes(int n) {
        // 0과 1은 소수 아님
        isPrime[0] = false;
        isPrime[1] = false;

        // 2부터 n의 제곱근까지 순회
        for (int i = 2; i * i <= n; i++) {
            // 해당 수가 소수가 아닌 경우 (즉, 이미 소수가 아니라고 표시된 수)
            if (!isPrime[i]) {
                continue; // 다음 수 탐색하도록 넘어감
            }

            // i의 배수들은 소수가 아님
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false; // 소수 아니라고 처리 (false)
            }
        }
    }
}