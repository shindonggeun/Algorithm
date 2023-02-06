import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();	// 중복값 허용X
		set.add("ChongChong");	// 콩콩이 set에 추가
		
		// 콩콩이랑 추면 콩콩이랑 춘 사람은 무지개 댄스를 전염시킬 수 있음
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String member1 = st.nextToken();
			String member2 = st.nextToken();
			
			// set에 member1의 이름이 있는 경우
			if(set.contains(member1)) {
				set.add(member2);	// 두번째 입력값 추가(두번째 사람)
			}
			// set에 member2의 이름이 있는 경우
			if(set.contains(member2)) {
				set.add(member1);	// 첫번째 입력값 추가(첫번째 사람)
			}
			
		}
		System.out.println(set.size());

	}

}