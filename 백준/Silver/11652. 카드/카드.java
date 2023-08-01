import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// key값이 Long인 이유는 숫자카드가 -2^62 ~ 2^62 범위로 주어졌기 때문에
		Map<Long, Integer> map = new HashMap<>();	// key: 해당 숫자 카드, value: 해당 숫자카드 개수
		for(int i=0; i<N; i++) {
			long cardNum = Long.parseLong(br.readLine());	// 해당 숫자 카드 입력
			map.put(cardNum, map.getOrDefault(cardNum, 0) + 1);	// 해당 카드 숫자가 나올때마다 +1씩 개수 카운트 해주도록 설정
		}
		
		// Collections.max() 메서드 활용해보기
		int maxCount = Collections.max(map.values());	// map에 저장된 value값들에서 최대값 뽑기
		List<Long> list = new ArrayList<>();	// 해당 숫자카드가 가장 많이 나온 것들 리스트에 저장해주게끔 리스트 선언	
		
		// map에 저장된 키값들 탐색
		for(long cardNum: map.keySet()) {
			// 숫자카드가 가장 많이 나온것인 경우
			if(map.get(cardNum) == maxCount) {
				list.add(cardNum);	// 리스트에 추가
			}
		}
		
		Collections.sort(list);	// 리스트 오름차순 정렬
		System.out.println(list.get(0));	
	}

}
