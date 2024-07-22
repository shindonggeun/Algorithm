import java.util.*;
import java.io.*;

public class Main {

	static int N; // 1부터 N까지의 수
	static Stack<Integer> stack; // 스택 수열을 만들기 전 이용할 스택
	static List<Integer> sequenceList; // 스택 수열을 저장할 리스트
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		stack = new Stack<>(); // 스택 초기화
		sequenceList = new ArrayList<>(); // 리스트 초기화
		sb = new StringBuilder();

		// 스택 수열을 만들기 위한 메서드 호출
		generateStackSequence(1, 1);

		System.out.print(sb.toString());
	}

	// 스택 수열을 만드는 메서드 (백트래킹)
	// pushNum: 현재 스택에 넣을 숫자
	// popNum: 현재 스택에 빼낼 숫자
	public static void generateStackSequence(int pushNum, int popNum) {
		// 현재 스택에 빼낼 숫자가 N보다 큰 경우 (즉, 모든 숫자가 pop된 경우) (기저 조건)
		if (popNum > N) {
			// 생성된 스택 수열 출력
			for (int num : sequenceList) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return; // 메서드 종료
		}

		// pop을 먼저 한 뒤 push를 하는 이유는 스택 수열을 출력할 때 사전순으로 출력하기 위해서임
		
		// 스택이 비어있지 않은 경우 -> pop
		if (!stack.isEmpty()) {
			int top = stack.pop(); // 현재 스택에서 최상단 빼냄
			sequenceList.add(top); // 스택 수열에 해당 숫자 넣음
			generateStackSequence(pushNum, popNum + 1); // 현재 스택에 빼낸 숫자 + 1 (다음 숫자)한 상태로 재귀 호출
			stack.push(top); // 아까 빼낸 숫자 다시 스택에 집어넣어서 원복 (백트래킹)
			sequenceList.remove(sequenceList.size() - 1); // 스택 수열 리스트에서 맨 마지막 요소 제거하여 원복 (백트래킹)
		}

		// 현재 스택에 넣을 숫자가 N보다 작거나 같은 경우 -> push
		if (pushNum <= N) {
			stack.push(pushNum); // 해당 숫자 스택에 넣음
			generateStackSequence(pushNum + 1, popNum); // 현재 스택에 넣을 숫자 + 1 (다음 숫자)한 상태로 재귀 호출
			stack.pop(); // 해당 숫자 스택에 빼내서 원복 (백트래킹)
		}
	}
}