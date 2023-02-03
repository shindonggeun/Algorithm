import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		List<Integer> timeList = new ArrayList<>();	// 각 사람이 돈을 인출하는데 필요한 시간
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			timeList.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(timeList);	// 각 사람이 돈을 인출하는데 필요한 시간 오름차순 정렬
		
		List<Integer> totalList = new ArrayList<>();	// 순서대로 줄 서서 걸린 시간들 list
		int sum = 0;
		
		for(int time: timeList) {
			sum+=time;
			totalList.add(sum);
		}
		
		int minTotal = 0;	// 돈을 인출하는데 필요한 시간의 합의 최솟값
		
		for(int total: totalList) {
			minTotal+=total;
		}
		System.out.println(minTotal);

	}

}