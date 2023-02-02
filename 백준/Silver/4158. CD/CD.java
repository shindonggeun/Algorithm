import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 상근이가 가지고 있는 CD 수
			int M = Integer.parseInt(st.nextToken());	// 선영이가 가지고 있는 CD 수
			
			// 입력값 둘다 0을 받는 경우 무한반복 빠져나오기
			if(N == 0 && M == 0) {
				break;
			}
			
			Set<Integer> set = new HashSet<>();	// 상근이가 가진 CD 목록
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				set.add(Integer.parseInt(st.nextToken()));
			}
			
			int count = 0;	// 상근이랑 선영이가 가진 CD 목록 중 CD가 같은것들 개수
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int cd_num = Integer.parseInt(st.nextToken());
				// 상근이가 가지고 있는 CD목록에 선영이랑 같은 CD가 있는 경우
				if(set.contains(cd_num)) {
					count++;
				}
			}
			sb.append(count).append("\n");
			
			// ArrayList를 사용해서 retainAll() 메서드를 사용하는 방법은 시간초과남
			
			/*List<Integer> nlist = new ArrayList<>();	// 상근이가 가지고 있는 CD 목록
			List<Integer> mlist = new ArrayList<>();	// 선영이가 가지고 있는 CD 목록
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				nlist.add(Integer.parseInt(st.nextToken()));
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				mlist.add(Integer.parseInt(st.nextToken()));
			}
			
			// nlist에서 mlist에 있는 공통원소들을 빼줌
			// 즉 nlist에 nlist와 mlist 교집합한거 다시 저장
			nlist.retainAll(mlist);	
			System.out.println(nlist.size());	// 두사람이 동시에 가지고 있는 CD 개수 저장
			*/
		}
		System.out.print(sb);

	}

}
