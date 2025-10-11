import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<List<Integer>> graph;
    static int[] indegree;
    static int[] selfBuild;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        indegree = new int[N + 1];
        selfBuild = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int buildingTime = Integer.parseInt(st.nextToken());
            selfBuild[i] = buildingTime;
            while (true) {
                int preBuildingNumber = Integer.parseInt(st.nextToken());
                if (preBuildingNumber == -1) {
                    break;
                }

                graph.get(preBuildingNumber).add(i);
                indegree[i]++;
            }
        }

        result = new int[N + 1];
        topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i] + selfBuild[i]).append("\n");
        }

        System.out.print(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                indegree[next]--;
                result[next] = Math.max(result[next], result[now] + selfBuild[now]);
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }
}