import java.io.*;

public class Main {
	
	static int G;	// 게이트의 수
	static int P;	// 비행기의 수
	static int[] parents;	// 각 요소의 부모를 저장할 배열
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		parents = new int[G+1];	// 부모 배열 초기화 [1] ~ [G]
		for(int i=1; i<=G; i++) {
			parents[i] = i;	// 초기에는 각 요소가 자신을 부모로 가지도록 설정
		}
		
		// g번 비행기는 g번 이하 게이트에만 도킹이 가능
		// 이 때, g번 비행기를 g번 게이트에 도킹하는 것이 최선
		// 만약, g번 비행기를 g번게이트에 도킹할 수 없다면
		// g-1번 게이트에 차선책으로 도킹
		// g-1번도 안 된다면, g-2번, ... 0번까지 탐색
		// 이 때, 차선책이 0번을 가리키고 있으면 도킹이 불가능한 상태
		// 차선책을 찾는 과정에서 유니온 파인드를 사용
		
		int count = 0;	// 도킹 가능한 비행기의 수
		boolean connectedGate = true;	// 비행기랑 게이트 도킹 가능한지 여부
		// 각 비행기에 대해 반복
		for(int i=1; i<=P; i++) {
			int gateNum = Integer.parseInt(br.readLine());	// 현재 비행기가 도킹할 수 있는 최대 게이트 번호
			int availableGate = find(gateNum);	// 현재 비행기가 도킹할 수 있는 게이트 찾기
			
			// 도킹할 수 있는 게이트가 없거나(0) 또는 불가능한 경우 
			if(availableGate == 0 || !connectedGate) {
				connectedGate = false;	// 비행기랑 게이트 도킹 불가
				continue;	// 넘어감
			}
			
			union(availableGate, availableGate - 1);	// 사용된 게이트와 그 이전 게이트를 연결
			count++;	// 도킹 가능한 비행기 수 증가
		}
		System.out.println(count);

	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// 자기 자신이 부모인 경우, 즉 루트인 경우
		if(a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]); // 경로 압축을 위해 부모를 루트로 설정
	}
	
	// 유니온파인드의 union 연산 (두 원소의 집합을 합침)
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우
		if(aRoot == bRoot) {
			return;
		}
		// 작은 번호의 원소가 루트가 되도록 합침
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
		}
	}

}
