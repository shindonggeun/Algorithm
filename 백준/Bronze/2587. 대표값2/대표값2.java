import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Integer> list = new ArrayList<>();
		int result = 0;
		StringBuilder sb = new StringBuilder();
		// 다섯 개의 자연수 입력받기
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(Integer.parseInt(st.nextToken()));
			result+=list.get(i);
		}
		sb.append(result/list.size()).append("\n");
		
		Collections.sort(list);
		sb.append(list.get(list.size()/2)).append("\n");
		
		System.out.println(sb);
    }
}