import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String s = st.nextToken();
		// LinkedHashSet 이므로 중복허용 X, 순서 보장
		Set<String> set = new LinkedHashSet<>();	// 문자열에서 각 부분집합 만든것들 중복없이 넣어야하므로 HashSet 이용
		
		for(int i=0; i<s.length(); i++) {
			String answer = "";
			for(int j=i; j<s.length(); j++) {
				answer+=s.substring(j, j+1);
				//System.out.println(answer);
				set.add(answer);
			}
		}
		System.out.println(set.size());
		
		
	}

}
