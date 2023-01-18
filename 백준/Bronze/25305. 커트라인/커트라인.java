import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		int cutline = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<num; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list, Collections.reverseOrder());	// ArrayList 내림차순 정렬
		
		System.out.println(list.get(cutline - 1));
    }
}