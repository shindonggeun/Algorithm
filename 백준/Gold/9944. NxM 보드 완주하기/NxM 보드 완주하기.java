import java.util.*;
import java.io.*;

public class Main {

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int minTurnCount;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str;
        StringBuilder sb = new StringBuilder();
        int testCase = 1;

        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            int emptyCount = 0;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '.') emptyCount++;
                }
            }

            minTurnCount = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '.') {
                        visited = new boolean[N][M];
                        visited[i][j] = true;
                        dfs(i, j, 1, 0, emptyCount);
                    }
                }
            }

            sb.append("Case ").append(testCase++).append(": ");
            sb.append(minTurnCount == Integer.MAX_VALUE ? -1 : minTurnCount).append("\n");
        }

        System.out.print(sb);
    }

    public static void dfs(int x, int y, int depth, int turnCount, int emptyCount) {
        if (depth == emptyCount) {
            minTurnCount = Math.min(minTurnCount, turnCount);
            return;
        }

        if (turnCount >= minTurnCount) return;

        for (int d = 0; d < 4; d++) {
            int nextX = x;
            int nextY = y;
            List<Position> path = new ArrayList<>();

            // 한 방향으로 이동하면서 빈 칸 방문
            while (true) {
                nextX += dx[d];
                nextY += dy[d];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) break;
                if (map[nextX][nextY] == '*' || visited[nextX][nextY]) break;

                visited[nextX][nextY] = true;
                path.add(new Position(nextX, nextY));
            }

            if (!path.isEmpty()) {
                // 마지막 유효 좌표를 전달
                Position last = path.get(path.size() - 1);
                dfs(last.x, last.y, depth + path.size(), turnCount + 1, emptyCount);

                // 백트래킹
                for (Position pos : path) {
                    visited[pos.x][pos.y] = false;
                }
            }
        }
    }
}