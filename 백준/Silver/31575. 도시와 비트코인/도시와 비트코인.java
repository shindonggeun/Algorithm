import java.io.*;
import java.util.*;

public class Main {

    // 좌표의 정보를 담고 있는 내부 클래스
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N; // 가로 크기
    static int M; // 세로 크기
    static int[][] map;
    static boolean[][] visited; // 각 좌표마다 방문 여부를 나타내는 배열
    // 2가지 방향 배열 (하, 우)
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean result = bfs(0, 0);

        System.out.println(result == true ? "Yes" : "No");
    }

    private static boolean bfs(int startX, int startY) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;

            // 도착지점에 도달한 경우 거래소 갈 수 있음
            if (nowX == M - 1 && nowY == N - 1) {
                return true;
            }

            for (int i = 0; i < 2; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                    continue;
                }

                if (visited[nextX][nextY] || map[nextX][nextY] == 0) {
                    continue;
                }

                queue.add(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
            }
        }

        // 너비 우선 탐색을 실시했는데도 도착지점에 도달하지 못한 경우 거래소 갈 수 없음
        return false;
    }
}