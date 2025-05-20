import java.util.*;
import java.io.*;

public class Main {

    static int N; // 현재 휴게소의 개수
    static int M; // 추가로 지을 휴게소의 개수
    static int L; // 고속도로 길이
    static List<Integer> restStops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        restStops = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int position = Integer.parseInt(st.nextToken());
            restStops.add(position);
        }

        restStops.add(0);
        restStops.add(L);

        Collections.sort(restStops);

        int result = binarySearch();

        System.out.println(result);
    }

    public static int binarySearch() {
        int left = 1;
        int right = L;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static boolean isPossible(int maxDist) {
        int required = 0;

        for (int i = 1; i < restStops.size(); i++) {
            int gap = restStops.get(i) - restStops.get(i - 1);

            if (gap > maxDist) {
                required += (gap - 1) / maxDist;
            }
        }

        return required <= M;
    }
}