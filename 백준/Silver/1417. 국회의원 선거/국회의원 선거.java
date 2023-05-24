import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int dasomObtain = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 높은값이 우선순위 높게끔
		
		for(int i=1; i<N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int humanCount = 0;	// 매수해야하는 사람의 수
		while(!pq.isEmpty() && (pq.peek() >= dasomObtain)) {
			int obtainNum = pq.poll();
			humanCount++;	// 매수한 사람 증가
			dasomObtain++;	// 매수함으로써 다솜이가 얻은 표 증가
			pq.add(obtainNum-1);
		}
		System.out.println(humanCount);
		

	}

}
