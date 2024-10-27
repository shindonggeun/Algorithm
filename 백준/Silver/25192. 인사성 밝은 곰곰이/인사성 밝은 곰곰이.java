import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 채팅방 기록 수
	static Set<String> userSet; // 채팅방 유저의 이름들을 저장할 해시셋

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		userSet = new HashSet<>(); // 해시셋 생성
		
		int count = 0; // 곰곰티콘을 사용한 횟수
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			
			// 해당 입력값이 "ENTER"인 경우
			if (input.equals("ENTER")) {
				// 새로운 사람이 채팅방에 입장한 이후 처음 채팅을 입력하는 사람은 반드시 곰곰티콘으로 인사하므로 해시셋 초기화 해줌
				userSet.clear(); // 채팅방 유저의 이름들을 저장한 해시셋 초기화
				continue; // 다음 입력값으로 넘어감
			}
			
			// 해당 입력값이 채팅방 유저의 이름에 없는 경우 (즉, 처음 입장인 경우)
			if (!userSet.contains(input)) {
				userSet.add(input); // 해당 채팅방 유저의 이름들을 저장한 해시셋에 해당 이름 저장
				count++; // 곰곰티콘 사용한 횟수 증가
			}
		}
		
		System.out.println(count);
	}

}