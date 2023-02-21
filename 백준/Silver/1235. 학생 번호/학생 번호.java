import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();	// 학생번호 맨 뒤부터 쪼개서 넣은 자료구조 HashSet
		String[] input = new String[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String classNumber = st.nextToken();
			input[i] = classNumber;
		}
		
		// 학생 번호 길이 다 같으므로
		int len = input[0].length();
		
		// 맨 뒤글자부터 탐색하기
		for(int i=1; i<=len; i++) {
			// 학생 수만큼 탐색 (각각 학생의 맨 뒤부터 늘려가면서 문자열 자라냄)
			for(int j=0; j<N; j++) {
				// substring()을 이용해서 학생번호 맨 뒤글자부터 늘려가면서 잘라낸것을 집어넣음
				set.add(input[j].substring(len-i));
			}
			//System.out.println(set);
			// 만약 학생수와 문자열 잘라낸것을 담은 set의 길이가 같아지는 경우 탐색 종료
			if(set.size() == N) {
				System.out.println(i);
				return;
			}
			set.clear();
		}
	}

}