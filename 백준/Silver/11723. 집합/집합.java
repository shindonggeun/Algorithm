import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), "\n");
			String s = st.nextToken();
			String[] command = s.split(" ");
			
			// "add" 명령어 나오면 set에 X 추가
			if(command[0].equals("add")) {
				set.add(Integer.parseInt(command[1]));
			}
			// "check" 명령어 나오면 set에 X가 ㅆ으면 1을, 없으면 0을 출력
			else if(command[0].equals("check")) {
				if(set.contains(Integer.parseInt(command[1]))) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			// "remove" 명령어 나오면 set에서 X 제거 (없는경우는 연산 무시)
 			else if(command[0].equals("remove")) {
				set.remove(Integer.parseInt(command[1]));
			}
			// "toggle" 명령어 나오면 set에 X가 있으면 X제거, 없으면 X 추가
			else if(command[0].equals("toggle")) {
				if(set.contains(Integer.parseInt(command[1]))) {
					set.remove(Integer.parseInt(command[1]));
				}
				else {
					set.add(Integer.parseInt(command[1]));
				}
			}
			// "empty" 명령어 나오면 set을 공집합으로 바꾸기
			else if(command[0].equals("empty")) {
				set.clear();	// set에 저장된거 전부 삭제
			}
			// "all" 명령어 나오면 set을 {1,2, ..., 20}으로 바꾸기
			else if(command[0].equals("all")) {
				for(int j=1; j<=20; j++) {
					set.add(j);
				}
			}
		}
		
		System.out.print(sb);
	}

}