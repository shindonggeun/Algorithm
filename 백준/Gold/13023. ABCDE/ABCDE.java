import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static List<List<Integer>> graph; // 각 친구 (노드)마다 친구 관계 (연결 관계)를 나타내는 그래프
    static boolean[] visited; // 각 친구 (노드)의 방문 여부를 나타내는 배열
    static boolean isFind; // 친구관계 A, B, C, D, E를 찾았는지 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[N + 1];
        isFind = false;

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 친구 관계 연결 (양방향 간선)
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 모든 사람들 자기 자신부터 시작해서 탐색
        for (int i = 1; i <= N; i++) {
            // 이미 해당 친구 관계 A, B, C, D, E 찾은 경우 탐색 종료
            if (isFind) {
                break;
            }
            dfs(i, 1); // 친구 관계 1부터 시작
        }

        System.out.println(isFind ? 1 : 0);
    }

    private static void dfs(int now, int depth) {
        // 현재 친구관계가 5 (A, B, C, D, E 존재)하는 경우 (기저 조건)
        if (depth == 5) {
            isFind = true;
            return;
        }

        visited[now] = true;

        for (int next : graph.get(now)) {
            if (!visited[next]) {
                dfs(next, depth + 1);
            }
        }

        visited[now] = false;
    }
}