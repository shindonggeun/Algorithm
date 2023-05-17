import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			List<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int score = Integer.parseInt(st.nextToken());
				list.add(score);
			}
			
			Collections.sort(list, Collections.reverseOrder());	// 점수가 저장된 리스트 내림차순 정렬
			int sum = 0;
			for(int i=0; i<K; i++) {
				sum += list.get(i);
			}
			sb.append("#" + tc + " " + sum).append("\n");
		}
		System.out.print(sb);
	}

}
