import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 각 앵무새가 한 문장(여러 단어로 이루어진 것)을 저장할 큐 배열 이용
		// [0] => 0번 앵무새의 문장 저장, [1] => 1번 앵무새의 문장 저장, ... , [N-1]
		Queue<String>[] qlist = new Queue[N];	
		
		for(int i=0; i<N; i++) {
			qlist[i] = new LinkedList<>();	// 배열 한 원소(방)마다 큐 이용할 수 있게끔 LinkedList로 초기화
			String input = br.readLine();	// 앵무새가 말한 문장 저장
			String[] inputSplit = input.split(" ");	// 앵무새가 말한 문장 여러 단어로 쪼개기 (공백단위)
			
			// 앵무새가 말한 문장 단어별로 쪼갠것들 저장해주는 과정
			for(String in: inputSplit) {
				qlist[i].add(in);
			}
		}
		
		String L = br.readLine();
		String[] result = L.split(" ");
		
		boolean isOk = true;	// 규칙에 의해 가능한 문장(L)인지 여부를 판단해주는 변수  (일단 만들 수 있다고 표시)
		
		// 완전탐색 이용
		// 문장 L 단어별로 쪼갠것 탐색
		for(String str: result) {
			boolean match = false;	// 해당 번호의 앵무새가 순서대로 말한 단어인지 파악해주는 변수
			// 해당 번호의 앵무새 탐색하기
			for(int i=0; i<N; i++) {
				if(!qlist[i].isEmpty()) {
					// 해당 번호의 앵무새가 말한 단어와 같은 경우
					if(qlist[i].peek().equals(str)) {
						match = true;	// 해당 번호의 앵무새가 순서대로 말한 단어임
						qlist[i].poll();	// 해당 앵무새가 말한 단어를 큐에서 제거
						break;	// 다음 앵무새 탐색 
					}
				}
			}
			
			// 해당 번호의 앵무새가 순서대로 말한 단어가 아닌 경우
			if(!match) {
				isOk = false;	// 맞지 않는 단어가 발견되면 불가능한 문장으로 표시
				break;	// 더이상 탐색할 필요 없이 단어 못만들음
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
		
		if(!isOk) {
			System.out.println("Impossible");
		}
		else {
			System.out.println("Possible");
		}
	}

}
