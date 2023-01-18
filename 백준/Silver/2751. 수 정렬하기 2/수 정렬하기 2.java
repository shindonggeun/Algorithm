import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<num; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list);	// 라이브러리 이용해서 ArrayList 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); i++) {
            sb.append(list.get(i)).append("\n");
		}
        System.out.println(sb);
    }
}