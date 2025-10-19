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

        // 1. 맵과 거리 배열 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = INF; // 아직 방문하지 않은 상태로 초기화
            }
        }

        // 2. 너비 우선 탐색 수행
        int rescueTime = bfs(0, 0);

        // 3. 결과 출력
        if (rescueTime == -1 || rescueTime > T) {
            System.out.println("Fail");
        } else {
            System.out.println(rescueTime);
        }
    }

    // 용사가 공주님을 구출 할 수 있는지 확인하는 너비 우선 탐색 메서드
    private static int bfs(int startX, int startY) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        dist[startX][startY] = 0; // 시작점 거리 0

        int swordDist = -1; // 검까지의 거리
        // 검 좌표
        int swordX = -1;
        int swordY = -1;
        boolean hasSward = false; // 검을 구했는지 여부

        // 너비 우선 탐색 시작 (가중치 1)
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                // 맵 범위 벗어난 경우 무시
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                // 벽(1)은 통과 불가능
                if (map[nextX][nextY] == 1) {
                    continue;
                }

                // 이미 방문한 곳인 경우 스킵
                if (dist[nextX][nextY] != INF) {
                    continue;
                }

                // 거리 갱신 및 큐에 추가
                dist[nextX][nextY] = dist[nowX][nowY] + 1;
                queue.add(new Point(nextX, nextY));

                // 검(2)을 발견한 경우 좌표와 거리 저장
                if (map[nextX][nextY] == 2) {
                    swordDist = dist[nextX][nextY];
                    swordX = nextX;
                    swordY = nextY;
                    hasSward = true;
                }
            }
        }

        // 검 없이 공주에게 바로 도달한 거리
        int normalDist = dist[N - 1][M - 1];

        // 검을 얻은 후 벽을 무시하고 공주에게 가는 경로 계산
        int swardRoute = INF;
        if (hasSward) {
            // 맨해튼 거리 |N - 1 - swordX| + |M - 1 - swordY|
            int manhattanDist = Math.abs(N - 1 - swordX) + Math.abs(M - 1 - swordY);

            // 시작 -> 검 + 검 -> 공주
            swardRoute = swordDist + manhattanDist;
        }

        // 두 경로 중 더 짧은 구출 시간 선택
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