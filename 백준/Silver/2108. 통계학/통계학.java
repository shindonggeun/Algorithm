import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();		// key: 숫자, value: 빈도수
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			list.add(num);
			map.put(num, map.getOrDefault(num, 0) + 1);	
			sum+=num;
		}
		
		Collections.sort(list);		// 오름차순 정렬
		double mean = (double) sum / list.size();		// 평균값 계산
		int realMean = (int) Math.round(mean);			// 평균값 반올림해줌(이유: -0.3333 -> -0이 아닌 0으로 표현해야하기때문)
		int median = list.get(list.size() / 2);		// 중앙값 계산
		int range = Math.abs(list.get(0) - list.get(list.size() - 1));	// 범위 계산
		
		int numbers = 0;
		for(int key : map.values()) {
			numbers = Math.max(numbers, key);
		}

		list.clear();	// 위에서 사용했던 list를 다시 사용하기 위해 clear 처리
		
		// 최빈수를 구하기 최빈수와 같은 빈도의 수를 list에 삽입
		for(int key : map.keySet()) {
			if(map.get(key) == numbers) {
				list.add(key);
			}
		}

		
		Collections.sort(list);	// 최빈수가 2개 이상일 경우 2번째로 큰 값을 뽑아내야 하기 때문에 list를 정렬
		int frequency = 0;		// 빈도수
		
		// 최빈수와 빈도수가 같은 수가 2개 이상인경우 index 1의 값을 최빈수로 설정
		// 1개일 경우는 그대로 index 0을 최빈수로 설정
		if(list.size() >= 2) {
			frequency = list.get(1);
		}
		else {
			frequency = list.get(0);
		}
		
		// 평균 -> 소수점 첫째자리에서 반올림 (Math.round() 함수 이용)
		// 중앙값 -> N개의 수들을 리스트화 할때(오름차순정렬) 중앙에 위치한 값
		// 최빈값 -> 최빈수가 2개 이상일 경우 2번째로 큰 값
		// 범위
		sb.append(realMean).append("\n")
		  .append(median).append("\n")
		  .append(frequency).append("\n")
		  .append(range).append("\n");
		
		System.out.print(sb);

	}

}