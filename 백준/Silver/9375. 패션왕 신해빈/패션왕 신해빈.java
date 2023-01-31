import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<testCase; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			Map<String, Integer> map = new HashMap<>();	// key: 의상 타입, value: 의상 타입 같은 수 
			int result = 1;		// 의상 입을 수 있는 경우의 수 계산시 필요한 변수
			
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine(), "\n");
				String str = st.nextToken();
				String[] clothes = str.split(" ");
				map.put(clothes[1], map.getOrDefault(clothes[1], 1) + 1);
			}
			
			for (String key : map.keySet()) {
	            result *= map.get(key);
	        }
			sb.append((result-1)).append("\n");		// 알몸 일때의 경우의 수 1개 빼야함
		}
		
		System.out.println(sb);
    }
	
}