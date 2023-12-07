import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 학생 수
	static int M;	// 친구 관계 수
	static int K;	// 가지고 있는 돈
	static int[] parents;	// 유니온-파인드에서 각 원소의 부모 노드를 저장하는 배열
	static int[] friendCost;	// 각 학생이 원하는 친구 비용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N], 부모 배열 초기화
		friendCost = new int[N+1];	// 친구 비용 배열 초기화
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 초기에 각 원소의 부모는 자기 자신으로 설정
			friendCost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int friend1 = Integer.parseInt(st.nextToken());	// 친구 관계에서 첫 번째 학생 입력
			int friend2 = Integer.parseInt(st.nextToken());	// 친구 관계에서 두 번째 학생 입력
			
			union(friend1, friend2);	// 두 학생을 유니온 연산으로 연결
		}
		
		Set<Integer> set = new HashSet<>();	// 각 친구 그룹의 대표를 저장하는 집합
		
		for(int i=1; i<=N; i++) {
			int root = find(i);	// 해당 학생의 루트(대표)를 찾음
			set.add(root);	// 루트를 Set(집합)에 추가
		}
		
		int resultCost = 0;	// 친구를 사귀는데 필요한 총 비용
		for(int friendNum: set) {
			resultCost += friendCost[friendNum];	// 각 친구 그룹의 대표에게 지불해야 하는 비용을 더함
		}
		
		// 총 비용이 가지고 있는 돈 이하인 경우
		if(resultCost <= K) {
			System.out.println(resultCost);
		}
		// 돈이 부족한 경우
		else {
			System.out.println("Oh no");
		}
	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// a의 부모가 자기 자신인 경우
		if(a == parents[a]) {
			return a;	// 자기 자신 반환
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정 
	}
	
	// 유니온파인드의 union 연산 (두 원소의 집합을 합침)
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우
		if(aRoot == bRoot) {
			return;	// 두 루트가 같으면 이미 연결된 상태이므로 아무 것도 하지 않음
		}

		// aRoot의 친구 비용이 bRoot의 친구 비용보다 더 큰 경우
		if(friendCost[aRoot] > friendCost[bRoot]) {
			parents[aRoot] = bRoot;	// 친구 비용이 더 낮은 bRoot에 aRoot를 연결
		}
		// aRoot의 친구 비용이 bRoot의 친구 비용보다 더 작거나 같은 경우
		else {
			parents[bRoot] = aRoot;	// 친구비가 더 낮은 aRoot에 bRoot를 연결
		}
		
	}

}
