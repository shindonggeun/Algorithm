import java.io.*;
import java.util.*;

public class Main {

    // 친구 자기 자신의 번호와 깊이 정보를 담고 있는 내부 클래스
    static class Friend {
        int num;
        int depth;

        public Friend(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    static int n; // 동기 수
    static int m; // 친구 관계 리스트 수
    static List<List<Integer>> graph; // 각 친구마다 친구 관게를 연결하고 있는 그래프
    static boolean[] visited; // 각 노드 (친구) 방문 여부를 나타내는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 친구 관계 연결 (양방향 간선)
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int result = bfs(1); // 상근이(1)의 친구 관계 탐색 시작

        System.out.println(result);
    }

    private static int bfs(int start) {
        Queue<Friend> queue = new LinkedList<>();
        queue.add(new Friend(start, 0)); // 상근이의 정보 큐에 저장
        visited[start] = true; // 상근이 자기 자신 스타트 방문 처리
        int count = 0; // 초대할 동기 수

        while (!queue.isEmpty()) {
            Friend now = queue.poll();
            int friendNum = now.num;
            int friendDepth = now.depth;

            // 친구의 친구까지만 탐색하게끔 조건 설정
            if (friendDepth >= 2) {
                continue;
            }

            // 해당 친구랑 연관되어 있는 친구들 탐색
            for (int next : graph.get(friendNum)) {
                if (!visited[next]) {
                    visited[next] = true;
                    count++;
                    queue.add(new Friend(next, friendDepth + 1)); // 탐색한 친구의 정보 및 친구 관계 수 + 1 해서 저장
                }
            }
        }

        return count;
    }
}