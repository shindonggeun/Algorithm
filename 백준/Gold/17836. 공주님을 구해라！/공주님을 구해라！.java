import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static int T;
    static int[][] map;
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE;
    // 4가지 방향 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = INF;
            }
        }

        int rescueTime = bfs(0, 0);

        if (rescueTime == -1 || rescueTime > T) {
            System.out.println("Fail");
        } else {
            System.out.println(rescueTime);
        }
    }

    private static int bfs(int startX, int startY) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        dist[startX][startY] = 0;

        int swordDist = -1;
        int swordX = -1;
        int swordY = -1;
        boolean hasSward = false;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (map[nextX][nextY] == 1) {
                    continue;
                }

                if (dist[nextX][nextY] != INF) {
                    continue;
                }

                dist[nextX][nextY] = dist[nowX][nowY] + 1;
                queue.add(new Point(nextX, nextY));

                if (map[nextX][nextY] == 2) {
                    swordDist = dist[nextX][nextY];
                    swordX = nextX;
                    swordY = nextY;
                    hasSward = true;
                }
            }
        }

        int normalDist = dist[N - 1][M - 1]; // 검 없이 일반적인 도착 거리
        int swardRoute = INF;

        if (hasSward) {
            int manhattanDist = (N - 1 - swordX) + (M - 1 - swordY);
            swardRoute = swordDist + manhattanDist;
        }

        int result = INF;
        if (normalDist != -1) {
            result = Math.min(result, normalDist);
        }

        if (swardRoute != INF) {
            result = Math.min(result, swardRoute);
        }

        return result == INF ? -1 : result;
    }
}