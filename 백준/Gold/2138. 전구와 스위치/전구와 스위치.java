import java.io.*;

public class Main {
	
	static int N; // 스위치의 개수
	static int[] current; // 현재 전구들의 상태를 담은 배열
	static int[] target; // 만들고자 하는 전구들의 상태

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		current = new int[N]; // [0] ~ [N-1]
		target = new int[N]; // [0] ~ [N-1]
		
		String currentInput = br.readLine();
		String targetInput = br.readLine();
		
		for (int i=0; i<N; i++) {
			current[i] = currentInput.charAt(i) - '0';
			target[i] = targetInput.charAt(i) - '0';
		}
		
		// 첫 번째 스위치를 누르지 않은 경우와 첫 번째 스위치를 누른 경우 각각 두 경우를 이용하여 스위치 누른 개수를 구한 뒤
		// 두 가지 중 스위치 누른 개수의 최소값을 구함
		int result = Math.min(getSwitchCount(false), getSwitchCount(true));
		
		// 결과값이 최대값인 경우 -1 (즉, 만들고자 하는 전구들의 상태를 못만드는 경우) 출력, 아닌 경우 결과값 출력
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	// 스위치를 누른 개수를 구하는 메서드
	// firstSwitch: 첫 번째 스위치를 누른 여부
	public static int getSwitchCount(boolean firstSwitch) {
		int[] bulbArr = current.clone(); // 전구들의 상태를 현재 전구들의 상태 배열에서 복사
		int count = 0; // 스위치를 누른 횟수
		
		// 스위치 처음꺼(첫번째 스위치 = [0])을 누른 경우
		if (firstSwitch) {
			switchPress(bulbArr, 0); // 첫 번째 스위치를 눌러서 전구들의 상태 변경
			count++; // 스위치 누른 횟수 증가
		}
		
		// 두 번째 전구부터 마지막 전구까지 순차적으로 탐색
		for (int i=1; i<N; i++) {
			// 해당 전구의 전 전구 (i-1) 상태가 목표 전구의 상태와 다른 경우 
			if (bulbArr[i-1] != target[i-1]) {
				switchPress(bulbArr, i); // 해당 스위치를 누름
				count++;
			}
		}
		
		// 마지막 전구의 상태가 목표 전구의 상태와 같은 경우
		if (bulbArr[N-1] == target[N-1]) {
			return count; // 스위치 누른 횟수 반환
		}
		
		// 위의 경우가 아닌 경우 (즉, 마지막 전구의 상태가 목표 전구의 상태와 다른 경우) 최대값 반환
		// 즉, 목표 전구의 상태를 만들 수 없음
		return Integer.MAX_VALUE;
	}
	
	// 해당 인덱스의 스위치를 눌러 전구 상태를 변경하는 메서드
	public static void switchPress(int[] bulbArr, int idx) {
		bulbArr[idx] = 1 - bulbArr[idx]; // 해당 전구의 상태를 변경
		
		// 해당 인덱스가 0보다 큰 경우
		// 즉, 왼쪽 전구가 있는 경우
		if (idx > 0) {
			bulbArr[idx-1] = 1 - bulbArr[idx-1]; // 해당 전구의 왼쪽 전구의 상태 변경
		}
		
		// 해당 인덱스가 N-1보다 작은 경우
		// 즉, 오른쪽 전구가 있는 경우
		if (idx < N-1) {
			bulbArr[idx+1] = 1 - bulbArr[idx+1]; // 해당 전구의 오른쪽 전구의 상태 변경
		}
	}

}