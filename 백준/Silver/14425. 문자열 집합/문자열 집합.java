import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		int count = 0;	
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			set.add(st.nextToken());	// 입력값 set에 추가 (중복값 허용 X)
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			// 입력값이 HashSet에 있는 경우
			if(set.contains(st.nextToken())) {
				count++;
			}
		}
		
		System.out.println(count);

	}

}