import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long newScore = Long.parseLong(st.nextToken());
		long P = Long.parseLong(st.nextToken());
		LinkedHashMap<Long, Long> map = new LinkedHashMap<>();
		
		String input = "";
		long rank = 1;
		long minScore = Integer.MAX_VALUE;
		
		// 해당 입력값이 null이 아닌 경우(다음줄에 입력값 나온 경우)
		if((input = br.readLine()) != null) {
			String[] str = input.split(" ");	// 입력값 공백단위로 쪼개준 뒤
			for(int i=0; i<N; i++) {
				long score = Long.parseLong(str[i]);
				// 해당 스코어가 map에 없는 경우
				if(!map.containsKey(score)) {
					map.put(score, rank);
				}
				rank++;	// 등수 증가
				minScore = Math.min(minScore, score);	// 최소점수 최신화
			}
		}
		
		if(N != P) {
			if(newScore >= minScore) {
				if(map.containsKey(newScore)) {
					System.out.println(map.get(newScore));
					return;
				}
				else {
					// 맵에 저장된 점수값들 탐색
					for(long score: map.keySet()) {
						// 해당 점수보다 높은 경우를 찾아내서 등수를 뽑아낸다
						if(newScore > score) {
							System.out.println(map.get(score));
							return;
						}
					}
				}
			}
			else {
				System.out.println(N+1);
				return;
			}
		}
		// 랭킹 리스트에 오를수 있는 개수와 점수 리스트 개수가 같은 경우
		else {
			// 태수의 점수가 점수 리스트에 있는 최소 점수보다 큰 경우
			if(newScore > minScore) {
				// 태수의 점수가 맵(key: 점수, value: 등수)에 키값으로 존재하는 경우
				if(map.containsKey(newScore)) {
					System.out.println(map.get(newScore)); // 해당 점수에 따른 등수 출력
					return;
				}
				// 태수의 점수가 맵에 존재하지 않는 경우
				else {
					// 맵에 저장된 점수값들 탐색
					for(long score: map.keySet()) {
						// 해당 점수보다 높은 경우를 찾아내서 등수를 뽑아낸다
						if(newScore > score) {
							System.out.println(map.get(score));
							return;
						}
					}
				}
			}
			// 태수의 점수가 점수 리스트에 있는 최소 점수보다 같거나 작은경우
			else {
				System.out.println(-1);
				return;
			}
		}
	}

}
