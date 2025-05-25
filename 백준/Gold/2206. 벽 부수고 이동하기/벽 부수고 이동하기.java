import java.util.*;
import java.io.*;

public class Main {

    static class Position {
        int x;
        int y;
        int move;
        boolean isBreak;

        public Position(int x, int y, int move, boolean isBreak) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.isBreak = isBreak;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static boolean[][][] visited;
    // 4가지 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // [0][0] ~ [N-1][M-1]
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int result = bfs(0, 0);

        System.out.println(result);
    }

    public static int bfs(int startX, int startY) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 1, false));
        visited[0][startX][startY] = true;

        while (!queue.isEmpty()) {
            Position now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowMove = now.move;
            boolean nowIsBreak = now.isBreak;
            int breakIdx = nowIsBreak ? 1 : 0;

            if (nowX == N - 1 && nowY == M - 1) {
                return nowMove;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (visited[breakIdx][nextX][nextY]) {
                    continue;
                }

                if (map[nextX][nextY] == 1) {
                    if (!nowIsBreak) {
                        visited[breakIdx][nextX][nextY] = true;
                        queue.add(new Position(nextX, nextY, nowMove + 1, true));
                    }
                } else {
                    visited[breakIdx][nextX][nextY] = true;
                    queue.add(new Position(nextX, nextY, nowMove + 1, nowIsBreak));
                }
            }

        }

        return -1;
    }


}