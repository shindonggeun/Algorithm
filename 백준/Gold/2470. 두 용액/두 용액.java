import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] nums; // 용액들의 특성값들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums); // 오름차순 정렬

        // 투포인터 알고리즘 이용 (양 쪽으로 좁혀오기 - 각 왼쪽, 오른쪽 인덱스 설정)
        int left = 0;
        int right = N - 1;

        int minAbsSum = Integer.MAX_VALUE; // 두 용약을 더했을 때 최소값
        int resultA = 0; // 두 용액 중 작은 값
        int resultB = 0; // 두 용액 중 큰 값

        // 투 포인터가 만나기 전까지 반복
        while (left < right) {
            int sum = nums[left] + nums[right];
            
            if (Math.abs(sum) < minAbsSum) {
                minAbsSum = Math.abs(sum);
                resultA = nums[left];
                resultB = nums[right];
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(resultA + " " + resultB);
    }
}