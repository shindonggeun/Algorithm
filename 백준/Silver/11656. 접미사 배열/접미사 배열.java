import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		List<String> list = new ArrayList<>();
		
		// list에 접미사 만들것들 집어넣기
		for(int i=0; i<str.length(); i++) {
			String subStr = str.substring(i, str.length());
			list.add(subStr);
		}
		
		Collections.sort(list);	// list에 저장된 문자열들 사전순으로 정렬(오름차순)
		
		StringBuilder sb = new StringBuilder();
		for(String s: list) {
			sb.append(s).append("\n");
		}
		System.out.print(sb);

	}

}