import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int sum = 0;
        int cnt = 0;
        int max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            arr[i] = num;

            if (cnt == K) {
                max = Math.max(max, sum);
                sum -= arr[i - K];
                cnt--;
            }

            if (cnt != K) {
                sum += num;
                cnt++;
            }
        }
        System.out.println(Math.max(max, sum));

    }
}