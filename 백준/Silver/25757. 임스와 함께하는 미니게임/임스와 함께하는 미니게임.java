import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String game = st.nextToken();
		Set<String> set = new HashSet<>();	// 게임에 참가하는 목록을 담은 자료구조 HashSet
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			set.add(name);
		}
		int count = 0;
		
		// 게임이 윷놀이인 경우 (2명에서 하는 게임인데 임스랑 하는거므로 그냥 set.size() 구하면 됨)
		if(game.equals("Y")) {
			count = set.size();
		}
		// 게임이 같은그림 찾기인 경우 (3명에서 하는 게임 -> 임스랑 x1, x2 이런식)
		else if(game.equals("F")) {
			count = set.size() / 2;
		}
		// 게임이 원카드인 경우 (4명에서 하는 게임 -> 임스랑 x1, x2, x3 이렇게)
		else if(game.equals("O")) {
			count = set.size() / 3;
		}
		System.out.println(count);
	}

}