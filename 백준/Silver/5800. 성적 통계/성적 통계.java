import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int start = 1;
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			List<Integer> list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				int score = Integer.parseInt(st.nextToken());
				list.add(score);
			}
			
			Collections.sort(list, Collections.reverseOrder());	// 내림차순 정렬
			
			int max = list.get(0);		// 성적 리스트에서 최고 성적
			int min = list.get(N-1);	// 성적 리스트에서 최소 성적
			int prev = max;		
			int nearGap = 0;	// 인접한 점수 차이 저장할 변수
			for(int i=1; i<N; i++) {
				// 인접한 점수 차이가 가장 큰 값 갱신해주는 과정
				if(nearGap < prev - list.get(i)) {
					nearGap = prev - list.get(i);
				}
				prev = list.get(i);
			}
			
			sb.append("Class " + start).append("\n");
			sb.append("Max " + max + ", Min " + min + ", Largest gap " + nearGap).append("\n");
			start++;
		}
		System.out.print(sb);

	}

}