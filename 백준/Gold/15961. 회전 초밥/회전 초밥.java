import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 접시의 수
		int d = Integer.parseInt(st.nextToken());	// 초밥의 가짓 수
		int k = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken());	// 쿠폰 번호
		
		int[] sushiArr = new int[N+k-1];	// 벨트 위의 초밥 번호들을 저장한 배열 (원형 큐처럼 만들어 주기 위해 k-1 더해줌)
		int[] selected = new int[d+1];	// 초밥번호들에 해당하는 것들 먹었는지 판단해주는 배열 (0번방 사용 x)
		// 초밥 번호들 입력받기
		for(int i=0; i<N; i++) {
			sushiArr[i] = Integer.parseInt(br.readLine());
		}
		
		int start = N;	// N번부터 시작하기 위해 시작 인덱스 설정
		// 회전초밥처럼 원형을 만들어주기 위해 뒤에 연속해서 먹는 초밥 접시 수 만큼 초기 초밥 번호를 저장해줌
		for(int i=0; i<k-1; i++) {
			sushiArr[start] = sushiArr[i];
			start++;
		}
		
		// 쿠폰 사용해서 초밥 먹었다고 가정
		int maxEatCount = 1;	// 최대로 먹을 수 있는 초밥의 가짓수
		selected[c] = 1;	// 쿠폰번호에 해당하는 초밥번호 먹었다고 처리
		
		
		for(int i=0; i<k; i++) {
			// 해당 초밥 번호에 해당하는거 먹지 않은(0) 경우
			if(selected[sushiArr[i]] == 0) {
				maxEatCount++;	// 최대로 먹을 수 있는 초밥의 가짓수 증가
			}
			
			selected[sushiArr[i]] += 1;	// 해당 초밥 번호 먹었음
		}
		
		// 슬라이딩 윈도우 알고리즘 이용
		int startIdx = 0;	// 시작 인덱스
		int endIdx = k;	// 끝 인덱스
		
		int resultEatCount = maxEatCount;	// 슬라이딩 윈도우를 이용해 초밥 먹은 개수 비교하기 위해 변수 설정
		
		// 연속해서 먹는 초밥 접시 수만큼(윈도우 크기) 범위 지정해서 그 범위를 한칸 씩 이동시킴
		// 회전초밥 길이까지 비교
		for(int i=endIdx; i<sushiArr.length; i++) {
			selected[sushiArr[startIdx]] -= 1;	// 시작 인덱스에 해당하는 초밥 번호 먹은거 빼줌
			
			// 시작 인덱스에 해당하는 초밥 번호 먹지 않은(0) 경우
			if(selected[sushiArr[startIdx]] == 0) {
				resultEatCount-=1;	// 초밥 먹은 개수 빼줌
			}
			
			// 윈도우 끝부분 인덱스에 해당하는 초밥 번호 먹지 않은(0) 경우
			if(selected[sushiArr[i]] == 0) {
				resultEatCount += 1;	// 초밥 먹은 개수 늘려줌
			}
			
			selected[sushiArr[i]] += 1;	// 슬라이딩 윈도우 끝부분 인덱스에 해당하는 초밥 먹었다고 처리(먹은 개수 증가)
			maxEatCount = Math.max(maxEatCount, resultEatCount);	// 최대로 먹은 초밥개수 갱신
			startIdx++;	// 시작인덱스 증가
		}
		System.out.println(maxEatCount);
	}

}
