import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();	// 출입기록을 담은 HashSet 이용
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			String enterOrOut = st.nextToken();
			
			// 출근인 경우
			if(enterOrOut.equals("enter")) {
				set.add(name);
			}
			// 퇴근인 경우
			else if(enterOrOut.equals("leave")) {
				// 출입기록(즉, 출근 기록이 있는 경우)
				if(set.contains(name)) {
					set.remove(name);	// 퇴근처리(출근 기록에서 삭지)
				}
			}
		}
		
		List<String> list = new ArrayList<>(set);	// HashSet을 ArrayList화
		Collections.sort(list, Collections.reverseOrder());		// 사전순의 역순으로 정렬
		StringBuilder sb = new StringBuilder();
		
		for(String str: list) {
			sb.append(str).append("\n");
		}
		System.out.print(sb);
		

	}

}
