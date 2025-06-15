import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int C;
    static int[] housePositions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        housePositions = new int[N];

        for (int i = 0; i < N; i++) {
            housePositions[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(housePositions);

        int result = binarySearch();
        System.out.println(result);
    }

    // 이분 탐색 알고리즘을 통해 가장 인접한 두 공유기 사이의 최대 거리를 구하는 메서드
    public static int binarySearch() {
        int start = 1; // 시작점 (가능한 최소 거리)
        int end = housePositions[N - 1] - housePositions[0]; // 끝점 (가능한 최대 거리: 마지막 집의 좌표 - 첫 시작 집의 좌표)
        int result = 0; // 결과값 (가장 인접한 두 공유기 사이의 최대 거리)

        while (start <= end) {
            int mid = (start + end) / 2;

            // 가능한 거리로 공유기를 설치할 수 있는지 확인
            // 해당 거리로 공유기 C대 이상 설치 가능한 경우
            if (checkWifiInstall(mid) >= C) {
                start = mid + 1;
                result = mid;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }

    // 해당 거리를 통해 공유기를 설치할 수 있는지 확인하는 메서드
    public static int checkWifiInstall(int distance) {
        int count = 1; // 공유기 설치 가능한 개수 (첫 번째 집에 공유기 설치하므로 일단 개수 1)
        int lastHouse = housePositions[0]; // 마지막으로 공유기를 설치한 집의 위치 (첫 번째 집의 위치로 초기화)

        // 두 번째 집의 위치부터 탐색 시작
        for (int i = 1; i < N; i++) {
            // 해당 집과 마지막으로 설치한 집 사이의 거리가 주어진 거리보다 크거나 같은 경우
            if (distance <= housePositions[i] - lastHouse) {
                count++; // 공유기 설치 가능한 개수 증가
                lastHouse = housePositions[i]; // 마지막으로 공유기를 설치한 집의 위치 갱신
            }
        }

        return count;
    }
}