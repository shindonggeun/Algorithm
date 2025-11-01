import java.io.*;

public class Main {

    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sb = new StringBuilder();

        // 소수 2, 3, 5, 7 시작
        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);

        System.out.print(sb);
    }

    private static void dfs(int num, int jarisu) {
        // 해당 자리수가 N자리수인 경우 (기저 조건)
        if (jarisu == N) {
            // 해당 수가 소수인 경우 출력
            if (isPrime(num)) {
                sb.append(num).append("\n");
            }
            return;
        }

        // 각 자리수마다 1부터 9까지 올 수 있음
        for (int i = 1; i < 10; i++) {
            // 짝수인 경우 더 이상 탐색 할 필요 없으므로 스킵
            if (i % 2 == 0) {
                continue;
            }

            int next = num * 10 + i;

            // 소수인 경우 재귀 함수로 자리수 늘림
            if (isPrime(next)) {
                dfs(next, jarisu + 1);
            }
        }
    }

    private static boolean isPrime(int num) {
        // 숫자 2 미만은 소수 아님
        if (num < 2) {
            return false;
        }

        // 소우인지 아닌지 판별하는 과정
        for (int i = 2; i * i <= num; i++) {
            // num이 i의 배수인 경우 소수 아님
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}