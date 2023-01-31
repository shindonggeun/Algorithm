import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 저장된 사이트의 수
		int M = Integer.parseInt(st.nextToken());	// 비밀번호 찾으려는 사이트 수
		
		Map<String, String> map = new HashMap<>();	// key: 사이트 주소, value: 비밀번호
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(),"\n");	// 입력값 공백단위로 끊는게 아닌 한줄단위로 끊음(한줄 다 읽음)
			String str = st.nextToken();					
			String[] site = str.split(" ");					// 입력값 공백단위로 쪼개기
			
			map.put(site[0], site[1]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			
			sb.append(map.get(str)).append("\n");
		}
		System.out.println(sb);
	}

}