import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];	// 입력된 순열 저장할 배열
			Set<Integer> set = new HashSet<>();	// N이하의 값을 저장할 HashSet
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 1 ~ N 까지 HashSet에 저장
			for(int i=1; i<=N; i++) {
				set.add(i);
			}
			
			for(int i=0; i<N; i++) {
				// 입력된 순열이 HashSet에 포함되어있는 경우 해당 값 HashSet에서 제거
				if(set.contains(arr[i])) {
					set.remove(arr[i]);
				}
			}
			
			// HashSet의 크기가 0인 경우(즉 입력된 순열이 1 ~ N 까지의 자연수로 이루어져있는 경우)
			if(set.size() == 0) {
				sb.append("#" + tc + " " + "Yes").append("\n");
			}
			else {
				sb.append("#" + tc + " " + "No").append("\n");
			}
		}
		System.out.print(sb);

	}

}