import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();		// key: 확장자명, value: 확장자명을 쓰는 파일 개수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			
			String[] file = str.split("[.]");	// .이후로 입력값 쪼개기
			map.put(file[1], map.getOrDefault(file[1], 0) + 1);
		}
		
		List<String> file_list = new ArrayList<>(map.keySet());	// key값들(file 확장자명) list화
		Collections.sort(file_list);	// 파일명 오름차순 정렬(사전순)
		StringBuilder sb = new StringBuilder();
		
		for(String file: file_list) {
			sb.append(file + " " + map.get(file)).append("\n");
		}
		System.out.println(sb);
	}

}