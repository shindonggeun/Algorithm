import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String S = st.nextToken();	// 개강총회 시작한 시간 S
		String E = st.nextToken();	// 개강총회를 끝낸 시간 E
		String Q = st.nextToken();	// 개강총회 스트리밍 끝낸 시간 Q
		
		// [0] -> 시간 [1] -> 분
		String[] S_arr = hhmmSplit(S);	
		String[] E_arr= hhmmSplit(E);
		String[] Q_arr = hhmmSplit(Q);
		
		// 분 계산
		int s_time = timeCal(S_arr[0], S_arr[1]);
		int e_time = timeCal(E_arr[0], E_arr[1]);
		int q_time = timeCal(Q_arr[0], Q_arr[1]);
		
		Map<String, Integer> map = new HashMap<>();	// key: 해당 유저 닉네임, value: 1 -> 입장만, 2-> 입장 퇴장 둘다 제대로 한 경우
		
		String s = "";
		while((s = br.readLine()) != null) {
			String[] str = s.split(" ");
			String[] time_result = hhmmSplit(str[0]);
			int minute = timeCal(time_result[0], time_result[1]);
			
			// 제시간에 들어온사람은 map에 추가
			// 채팅기록에 해당 유저 닉네임이 없으면 
			if(map.containsKey(str[1]) != true) {
				// 개강총회 시작 전에 들어왔으면 (시작하자마자도 포함)
				if(minute <= s_time) {
					map.put(str[1], 1);
				}
			}
			// 채딩기록에 해당 유저 닉네임이 있으면 (제 시간안에 입장한 닉네임이 있는 경우)
			else {
				// 개강총회 끝낸시간 이후면서 스트리밍 끝낸 시간인 경우
				if(minute >= e_time && minute <= q_time) {
					map.put(str[1], 2);
				}
			}
		}
		
		int count = 0;
		// value값이 2인것을 갯수 세기 (2 -> 입장과 퇴장 제대로 한 경우)
		for(String key: map.keySet()) {
			if(map.get(key) == 2) {
				count++;
			}
		}
		System.out.println(count);
		
	}
	
	// 시간문자열 쪼개기 메서드 (시간:분) -> [0] = 시간, [1] -> 분
	public static String[] hhmmSplit(String time) {
		String[] str = time.split(":");
		return str;
	}
	
	// 분 계산해주는 메서드
	public static int timeCal(String hh, String mm) {
		int time = Integer.parseInt(hh) * 60 + Integer.parseInt(mm);
		return time;
	}

}