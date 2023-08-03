import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new LinkedList<>();	// 양방향 특성을 가진 큐 (덱)
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			
			// "push" 라는 명령어가 입력된 경우
			if(inputSplit[0].equals("push")) {
				// 덱에 앞쪽에 숫자 삽입
				deque.addFirst(Integer.parseInt(inputSplit[1]));
			}
			// "pop" 라는 명령어가 입력된 경우
			else if(inputSplit[0].equals("pop")) {
				// 만약 큐(덱)이 비어있다면 -1을 출력
				if(deque.isEmpty()) {
					sb.append(-1).append("\n");
				}
				// 큐(덱)이 비어있지 않은 경우
				else {
					// 덱에 가장 앞쪽에 있는 숫자 빼냄
					// 문제에서는 앞쪽에 있는 숫자를 빼낸다고 했지만 가장 뒤쪽에 있는 숫자 빼내야됨
					sb.append(deque.pollLast()).append("\n");
				}
			}
			// "size" 라는 명령어가 입력된 경우
			else if(inputSplit[0].equals("size")) {
				// 큐(덱)에 들어있는 정수의 개수 출력 (덱의 사이즈 반환)
				sb.append(deque.size()).append("\n");
			}
			// "empty" 라는 명령어가 입력된 경우
			else if(inputSplit[0].equals("empty")) {
				// 큐(덱)이 비어있는 경우
				if(deque.isEmpty()) {
					// 1 출력할 수 있게끔
					sb.append(1).append("\n");
				}
				// 큐(덱)이 비어있지 않은 경우
				else {
					// 0 출력할 수 있게끔
					sb.append(0).append("\n");
				}
			}
			// "front" 라는 명령어가 입력된 경우
			else if(inputSplit[0].equals("front")) {
				// 큐(덱)이 비어있는 경우
				if(deque.isEmpty()) {
					sb.append(-1).append("\n");
				}
				else {
					// 덱에서 가장 앞쪽에 있는 정수 출력 할 수 있게끔
					// 문제에서는 앞쪽이라 표현되어 있지만 가장 뒤쪽에 있는 정수 출력해야 한다
					sb.append(deque.peekLast()).append("\n");
				}
			}
			// "back" 라는 명령어가 입력된 경우
			else if(inputSplit[0].equals("back")) {
				// 큐(덱)이 비어있는 경우
				if(deque.isEmpty()) {
					sb.append(-1).append("\n");
				}
				else {
					// 덱에서 가장 뒤쪽에 있는 정수 출력 할 수 있게끔
					// 문제에서는 가장 뒤쪽에 있는 정수 출력이라고 했지만 가장 앞쪽에 있는 정수 출력해야 한다
					sb.append(deque.peekFirst()).append("\n");
				}
			}
		}
		System.out.print(sb);

	}

}
