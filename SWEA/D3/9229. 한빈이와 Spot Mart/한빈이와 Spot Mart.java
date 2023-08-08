import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] snackArr = new int[N];	// 과자봉지 무게를 담은 배열
			int maxResult = Integer.MIN_VALUE;	// 과자 두봉지의 최대 무게 합을 저장할 변수
			
			st = new StringTokenizer(br.readLine());
			// 과자봉지들 무게 입력받아서 배열에 저장
			for(int i=0; i<N; i++) {
				snackArr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(snackArr);	// 과자봉지들 무게를 오름차순 정렬
			
			// 투포인터 알고리즘 이용
			int left = 0;	// 왼쪽 포인터
			int right = snackArr.length-1;	// 오른쪽 포인터
			
			while(left < right) {
				int sum = snackArr[left] + snackArr[right];
				// 과자 두봉지의 무게 합이 M 이하인 경우
				if(sum <= M) {
					maxResult = Math.max(maxResult, sum);	// 최대 무게 합 갱신해줌
					left++;	// 왼쪽 포인터 증가
				}
				// 과자 두봉지의 무게 합이 M을 초과하는 경우
				else {
					right--;	// 오른쪽 포인터 감소시켜줌 (배열에서 오른쪽으로 갈수록 무게가 높아지기 때문)
				}
			}
			
			// 위의 투포인터 알고리즘을 이용해서 탐색했는데도 최대 무게가 갱신이 안됐으면 -1 저장해줌
			if(maxResult == Integer.MIN_VALUE) {
				maxResult = -1;
			}
			
			sb.append("#").append(tc).append(" ").append(maxResult).append("\n");
		}
		System.out.print(sb);

	}

}
