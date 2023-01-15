import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr = new int[10];
		Set<Integer> set = new LinkedHashSet<>();
		
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			
			int result = arr[i] % 42;
			set.add(result);	// LinkedHashSet에 집어넣음으로써 중복 허용 X, 순서 보장
		}
		
		System.out.println(set.size());
    }
}