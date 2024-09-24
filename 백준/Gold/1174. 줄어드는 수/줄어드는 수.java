import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] numArr;
	static Set<Long> set;
	static List<Long> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		
		set = new HashSet<>();
		list = new ArrayList<>();
		
		dfs(0, 0);
		
		Collections.sort(list);
		
		System.out.println(list.size() >= N ? list.get(N-1) : -1);
	}
	
	public static void dfs(int depth, long num) {
		if (!set.contains(num)) {
			set.add(num);
			list.add(num);
		}
		
		if (depth == 10) {
			return;
		}
		
		
		// 현재 자릿수보다 작은 숫자들만을 선택하여 재귀 호출
		for (int i=depth; i<10; i++) {
			dfs(i + 1, num * 10 + numArr[i]);
		}
	}

}
