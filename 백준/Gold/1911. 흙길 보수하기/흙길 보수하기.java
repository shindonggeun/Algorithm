import java.io.*;
import java.util.*;

public class Main {

    // 물 웅덩이의 시작점과 끝점 정보를 가지고 있는 내부 클래스
    static class Pool {
        int start;
        int end;

        public Pool(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int N; // 물웅덩이 개수
    static int L; // 물웅덩이를 덮을 수 있는 길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        List<Pool> pools = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pools.add(new Pool(start, end));
        }

        Collections.sort(pools, (a, b) -> a.start - b.start);

        int result = 0; // 널빤지가 필요한 최소 개수
        int lastCovered = 0; // 널빤지를 마지막으로 덮은 위치

        // 물 웅덩이 시작점 위치에 따라 차례로 탐색
        for (Pool pool : pools) {
            int start = Math.max(pool.start, lastCovered); // 시작점 계산

            // 이미 덮여있는 구간인 경우 스킵
            if (start >= pool.end) {
                continue;
            }

            int remain = pool.end - start; // 물 웅덩이를 덮어야 할 남은 길이
            int count = (int) Math.ceil((double) remain / L); // 해당 물 웅덩이를 덮기 위해 필요한 널빤지 개수
            result += count; // 널빤지 개수 누적

            lastCovered = start + count * L; // 덮은 마지막 위치 계산
        }

        System.out.println(result);
    }
}