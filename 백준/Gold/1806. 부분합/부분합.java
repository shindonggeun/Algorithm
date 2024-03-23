import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int S;
	static int[] numArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		numArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터 알고리즘 이용
		int leftIdx = 0;	// 왼쪽 인덱스
		int rightIdx = 0;	// 오른쪽 인덱스
		// 연속된 수들의 부분합 중 그 합이 S 이상이 될 때의 가장 짧은 길이 (결과값)
		int minLength = Integer.MAX_VALUE;	
		int sum = 0;	// 연속된 수들의 부분합 (누적합)
		
		while (true) {
            if (sum >= S) { // 현재 부분합이 S 이상인 경우
            	sum -= numArr[leftIdx];
                leftIdx++;
                minLength = Math.min(minLength, rightIdx - leftIdx + 1);	// 가장 짧은 길이 갱신
            } 
            else if (rightIdx == N) { // 현재 부분합이 S 미만이면서, 오른쪽 포인터가 배열의 끝에 도달한 경우
                break;
            } 
            else { // 현재 부분합이 S 미만이고, 오른쪽 포인터가 배열 끝에 도달하지 않은 경우
                sum += numArr[rightIdx];
                rightIdx++;
            }
        }

        if (minLength == Integer.MAX_VALUE) { // 조건을 만족하는 부분 수열이 없는 경우
            System.out.println(0);
        } else {
            System.out.println(minLength);
        }
	}

}
