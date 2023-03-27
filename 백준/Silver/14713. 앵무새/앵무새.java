import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Queue[] qlist = new LinkedList[N];	// 큐 배열 생성(앵무새들이 저장할 문장(단어 리스트들))
		
		for(int i=0; i<N; i++) {
			qlist[i] = new LinkedList<String>();
			st = new StringTokenizer(br.readLine());
			
			// 입력값을 공백단위로 자른 토큰값들 계속해서 각 큐(앵무새)에 저장
			while(st.hasMoreTokens()) {
				qlist[i].add(st.nextToken());
			}
		}
		
		String input = br.readLine();
		String[] tmp = input.split(" ");
		
		boolean isOk = true;	// 규칙에 의해 나올 수 있는 문장인지 여부
		for(String word: tmp) {
			boolean match = false;
			for(int i=0; i<N; i++) {
				if(!qlist[i].isEmpty()) {
					if(qlist[i].peek().equals(word)) {
						qlist[i].poll();
						match = true;
						break;
					}
				}
			}
			
			// 앵무새가 기억하고 있는 단어 리스트들에 매치가 안된 경우
			if(!match) {
				isOk = false;	// 규칙에 의해 나올 수 없는 문장임
				break;
			}
		}
		
		// 앵무새가 기억하고 있는 단어 리스트들과 매치가 다 됐지만 앵무새의 단어가 남아있는 경우까지 고려
		// 남아있는 경우는 불가능한 문장임
		// 이유: 앵무새는 한문장을 기억하고 있는데 이 문장의 단어를 다 써야 하므로
		// 아래 for문을 안쓸경우 
		// 반례) input => 앵무새1 = "ab cd", 앵무새2 = "ef gh ij" 문장 = "ab ef gh ij"
		// answer) "Impassible"
		for(int i=0;i<N;i++) {
			if(!qlist[i].isEmpty()) {
				isOk = false;	// 규칙에 의해 만들 수 없는 문장임
			}
		}
		
		// 규칙에 의해서 나올 수 없는 문장인 경우
		if(!isOk) {
			System.out.println("Impossible");
		}
		// 규칙에 의해 나올 수 있는 문장인 경우
		else {
			System.out.println("Possible");
		}
		

	}

}