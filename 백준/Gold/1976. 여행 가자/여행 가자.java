import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parents = new int[N + 1]; // [1] ~ [N]
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connect = Integer.parseInt(st.nextToken());
                if (connect == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        boolean isPlan = true;
        int rootStart = find(start);

        for (int i = 1; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());
            if (rootStart != find(city)) {
                isPlan = false;
            }
        }

        System.out.println(isPlan ? "YES" : "NO");
    }

    public static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootA] = rootB;
        }
    }
}