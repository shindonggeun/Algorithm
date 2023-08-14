import java.util.*;
import java.io.*;

public class Main {
	
	static int count = 0;	// 몇번째로 방문했는지 나타내는 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int size = (int) Math.pow(2, N);	// 2^N 크기 만들어주기
		find(r, c, size);
		System.out.println(count);
	}
	
	public static void find(int r, int c, int size) {
		// 종료 조건
		if(size == 1) {
			return;
		}
		
		// 2사분면에 존재하는 경우 
		if(r < size/2 && c < size/2) {
			// 2사분면에서의 r, c의 상대위치와 size의 절반 크기를 넘겨줌
			find(r, c, size/2);
		}
		// 1사분면에 존재하는 경우
		else if(r < size/2 && c >= size/2) {
			// 앞에서 2사분면을 방문해야 하므로 count에 2사분면의 크기만큼을 더해줌
			count += size * size / 4;	
			// 1사분면에서의 r, c의 상대 위치와 size의 절반 크기를 넘겨줌
			find(r, c - size/2, size/2);
		}
		// 3사분면에 존재하는 경우
		else if(r >= size/2 && c < size/2) {
			// 앞에서 2사분면과 1사분면을 방문해야 하므로 count에 2사분면과 1사분면의 크기를 더해준것들을 더해줌
			count += (size * size/4) * 2;	
			// 3사분면에서의 r, c의 상대 위치와 size의 크기를 넘겨줌
			find(r - size/2, c, size/2);
		}
		// 4사분면에 존재하는 경우
		else {
			// 앞에서 2사분면과 1사분면, 3사분면을 방문해야 하므로 count에 2사분면, 1사분면, 3사분면의 크기를 다 더한것들을 더해줌
			count += (size * size/4) * 3;	
			// 4사분면의 r, c의 상대위치와 size의 크기 절반을 넘겨줌
			find(r - size/2, c - size/2, size/2);
		}
	}

}
