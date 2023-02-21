import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());	// 테스트케이스 개수
		Map<String, String> map = new HashMap<>();	// key: 동물 이름, value: 동물 울음소리
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 수만큼 반복
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine(), "\n");	// 한줄에 다 입력받을 수 있게끔
			String sound = st.nextToken();					// 입력값인 모든 동물의 울음소리 저장
			String[] soundSplit = sound.split(" ");			// 모든 동물의 울음소리가 저장된 문자열에서 공백 단위로 쪼개줌
			
			while(true) {
				st = new StringTokenizer(br.readLine(), "\n");
				String animalSound = st.nextToken();		// 각각 동물마다 매치된 울음소리 입력받음
				String[] animalSoundSplit = animalSound.split(" ");	// 공백단위로 쪼개주기
				
				// 동물들의 울음소리 매치는 "동물 goes 울음소리" 이렇게 매치된다
				// "goes"가 아닌 문자열 나온경우 무한반복 빠져나옴
				// 즉 animalSound에 "what does the fox say?" 문자열이 저장된 경우를 뜻함
				if(!animalSoundSplit[1].equals("goes")) {
					break;
				}
				
				map.put(animalSoundSplit[0], animalSoundSplit[2]);	// map에 key: 동물이름, value: 동물 울음소리 저장해줌
			}
			// 모든 동물의 울음소리 탐색
			for(String soundValue: soundSplit) {
				// 동물이름, 동물 울음소리가 저장된 map에 없는 동물 울음소리인 경우
				if(!map.containsValue(soundValue)) {
					sb.append(soundValue).append(" ");
				}
			}
		}
		System.out.println(sb);

	}

}