import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 사이트 주소의 수
	static int M; // 비밀번호를 찾으려는 사이트 주소의 수
	static Map<String, String> sitePasswordMap; // key: 사이트 주소, value: 해당 사이트의 비밀번호

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sitePasswordMap = new HashMap<>(); // 해시맵 생성
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" "); // 해당 입력값 공백단위로 끊음
			
			sitePasswordMap.put(inputSplit[0], inputSplit[1]);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			String site = br.readLine();
			
			// 해당 입력한 사이트의 패스워드를 출력하게끔
			sb.append(sitePasswordMap.get(site)).append("\n");
		}
		
		System.out.print(sb);

	}

}