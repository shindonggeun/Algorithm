import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();	// 중복허용 X, 검색결과 빠름, 채팅창에 곰곰이 이모티콘 사용한 아이디 목록
		
		int count = 0;	// 채팅중에 곰곰이 이모티콘 사용한 횟수 나타내는 변수
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			
			// 입력값 ENTER가 입력되면 
			// ENTER 이후에 다시 초기화
			if(input.equals("ENTER")) {
				set = new HashSet<>();	// 곰곰이 이모티콘 사용하는 HashSet 자료구조 다시 생성
				continue;
			}
			
			// 곰곰이 이모티콘을 사용한 적이 없는 경우(ENTER 이후에 채팅 한번도 안친 아이디)
			if(set.contains(input) == false) {
				set.add(input);		// hashset에 추가
				count++;	// 이모티콘 사용한 횟수 증가
			}
		}
		System.out.println(count);
		

	}

}
