import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 메모장에 적을 키워드 개수
		int M = Integer.parseInt(st.nextToken());	// 블로그 글 쓴 횟수
		
		Set<String> set = new HashSet<>();			// 중복허용 X
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String memo = st.nextToken();
			set.add(memo);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String[] blog = st.nextToken().split(",");	// 블로그에 쓴 글 "," (쉼표)로 구분
			
			// 블로그에 쓴 글에서 메모장에 있는 키워드 찾기
			for(String b: blog) {
				// 메모장에 있는 키워드인 경우 메모장에서 삭제
				if(set.contains(b)) {
					set.remove(b);
				}
			}
			
			sb.append(set.size()).append("\n");
		}
		
		System.out.print(sb);

	}

}