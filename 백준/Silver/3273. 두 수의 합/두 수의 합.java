import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] numArr = new int[n];
		Set<Integer> set = new HashSet<>();	// 중복제외, 탐색 빠름
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
			set.add(numArr[i]);
		}
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());	// 두 수 더해서 만족하는 수 x
		
		//Arrays.sort(numArr);	// int형 배열 오름차순 정렬
		int count = 0;
		
		for(int i=0; i<n; i++) {
			// 두 수를 더해서 나온 x에서 배열의 수 뺄때 해당하는 수가 set에 있으면 count 증가
			// ex) x = 13 -> 13 - 3 = 10 (10은 set에 있음) -> count = 1
			// ex) x = 13 -> 13 - 10 = 3 (3은 set에 있음) -> count = 2
			if(set.contains(x - numArr[i])) {
				count++;
			}
		}
		// 위의 예에서 3과 10을 더한게 13이므로 
		// 3과 10이 한세트가 됨 (3, 10) = (10, 3) 같은 조합
		System.out.println(count / 2);	

	}

}