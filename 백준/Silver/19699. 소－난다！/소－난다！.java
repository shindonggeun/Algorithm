import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 농장에 있는 소들의 수
	static int M;	// 선별한 소의 수
	static int[] cowWeight;	// 소들의 몸무게를 담은 배열
	static int[] selectedCow;	// 선택한 소들을 담을 배열 (조합 결과)
	static Set<Integer> sumWeightSet = new HashSet<>();	// 조합으로 만들어진 소들의 몸무게 합들을 저장할 집합
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cowWeight = new int[N];	
		selectedCow = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cowWeight[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0, 0);	// 조합 메서드 호출
		
		// 소들의 몸무게 합으로 만들 수 있는 소수가 아예 없는 경우 -1 출력한뒤 종료
		if(sumWeightSet.size() == 0) {
			System.out.println(-1);	
			return;	// 메서드 종료
		}
		
		List<Integer> resultList = new ArrayList<>(sumWeightSet);
		Collections.sort(resultList);	// 소들의 몸무게의 합을 저장한 리스트 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		
		for(int result: resultList) {
			sb.append(result).append(" ");
		}
		System.out.println(sb);
		
	}
	
	// 소들의 몸무게 조합을 만들어내는 메서드 (백트래킹)
	public static void combination(int depth, int idx) {
		// 해당 깊이(선택 횟수)가 M이 된 경우 (종료 조건)
		// 즉, 조합 완성된 경우
		if(depth == M) {
			int sumWeight = 0;	// 해당 조합의 소들의 무게를 합한 변수
			// 해당 조합의 소들의 무게 합하는 과정
			for(int i=0; i<M; i++) {
				sumWeight += selectedCow[i];
			}
			boolean primeFind = isPrime(sumWeight);	// 소들의 몸무게 합 소수인지 판별
			// 소수인 경우
			if(primeFind) {
				// 소들의 몸무게의 합들을 저장한 집합(set)에 해당 몸무게 합 추가 (중복 제거하기 위해 사용)
				sumWeightSet.add(sumWeight);	
			}
			return;	// 메서드 종료
		}
		
		// 조합 만드는 과정
		for(int i=idx; i<N; i++) {
			selectedCow[depth] = cowWeight[i];
			combination(depth+1, i+1);
		}
	}
	
	// 소수인지 판별해주는 메서드 (에라토스테네스의 체 이용)
	public static boolean isPrime(int num) {
		// 해당 숫자가 1인 경우 소수 아님
		if(num == 1) {
			return false;
		}
		
		// 해당 숫자가 2 또는 3인 경우 소수임
		if(num == 2 || num == 3) {
			return true;
		}
		
		// 해당 숫자가 2 또는 3으로 나누어지는 경우 소수가 아님
		if(num % 2 == 0 || num % 3 == 0) {
			return false;	
		}
		
		// 에라토스테네스의 체에서 최적화 된 부분 (외우자)
		for(int i=5; i*i <= num; i+=6) {
			if(num % i == 0 || num % (i + 2) == 0) {
				return false;	// 소수 아님
			}
		}
		
		// 위의 과정을 거쳐서 이상 없는것들은 소수임
		return true;
	}

}
