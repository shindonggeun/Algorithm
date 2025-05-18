import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int M;
    static int LOG;
    static List<List<Integer>> graph;
    static int[][] parents;
    static int[] depth;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        LOG = (int) Math.ceil(Math.log(N) / Math.log(2));

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int fromVertex = Integer.parseInt(st.nextToken());
            int toVertex = Integer.parseInt(st.nextToken());

            graph.get(fromVertex).add(toVertex);
            graph.get(toVertex).add(fromVertex);
        }

        // 2) 깊이(depth)와 1차 부모(parent[0][v]) 계산
        parents = new int[LOG + 1][N + 1];
        depth = new int[N + 1];
        dfs(1, 0);  // 루트는 1, 부모는 0

        // 3) Binary Lifting 테이블 구성
        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parents[k][v] = parents[k - 1][parents[k - 1][v]];
            }
        }


        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(lca(u, v)).append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(int node, int par) {
        parents[0][node] = par;
        depth[node] = depth[par] + 1;
        for (int next : graph.get(node)) {
            if (next == par) {
                continue;
            }
            dfs(next, node);
        }
    }

    public static int lca(int u, int v) {
        // 1) 깊이 맞추기
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        for (int k = LOG; k >= 0; k--) {
            if (depth[u] - (1 << k) >= depth[v]) {
                u = parents[k][u];
            }
        }
        if (u == v) return u;

        // 2) 공통 조상 직전까지 동시에 올리기
        for (int k = LOG; k >= 0; k--) {
            if (parents[k][u] != parents[k][v]) {
                u = parents[k][u];
                v = parents[k][v];
            }
        }
        // 3) 한 칸만 더 올리면 부모가 LCA
        return parents[0][u];
    }
}