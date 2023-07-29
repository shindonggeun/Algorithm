import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();	// 문자열 담을 자료구조 HashSet, 중복허용 X
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			set.add(input);
		}
		
		int count = 0;	// M개의 문자열 중에 총 몇개가 집합 S에 포함되어 있는지 세는 변수
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			// HashSet에 해당 입력한 문자열이 포함되어 있는 경우 (즉, 집합 S에 포함되어 있는 경우)
			if(set.contains(input)) {
				count++;	// 카운트 증가
			}
		}
		
		System.out.println(count);
	}

}
