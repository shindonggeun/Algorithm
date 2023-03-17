import java.util.*;
import java.io.*;

public class Main {
	
	static class Grade {
		int rank1 = 0;
		int rank2 = 0;
		
		public Grade(int rank1, int rank2) {
			this.rank1 = rank1;
			this.rank2 = rank2;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			ArrayList<Grade> list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int rank1 = Integer.parseInt(st.nextToken());
				int rank2 = Integer.parseInt(st.nextToken());
				
				list.add(new Grade(rank1, rank2));
			}
			Collections.sort(list, new Comparator<Grade>() {

				@Override
				public int compare(Grade g1, Grade g2) {
					// 1차 성적 순위(서류순위)순으로 오름차순 정렬
					return g1.rank1 - g2.rank1;
				}
				
			});
			int count = 1;	// 서류순위 1등은 어차피 합격이므로 count 1부터 시작
			int ans = list.get(0).rank2;	// 서류순위 1등의 면접순위 저장
			for(int i=1; i<list.size(); i++) {
				// 면접순위가 높으면 그 신입사원의 면접순위로 비교하게끔
				if(list.get(i).rank2 < ans) {
					count++;	// 신입사원 선발수 증가
					ans = list.get(i).rank2;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);

	}

}