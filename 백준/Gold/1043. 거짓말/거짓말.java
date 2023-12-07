import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 사람의 수
	static int M;	// 파티의 수
	static int[] parents;	// 각 원소의 부모 노드를 저장하는 배열
	static ArrayList<ArrayList<Integer>> partyList;	// 각 파티에 참석하는 사람들의 목록을 저장하는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];	// [1] ~ [N], 부모배열 초기화
		for(int i=0; i<=N; i++) {
			parents[i] = i;	// 초기에 각 원소의 부모는 자기 자신으로 설정
		}
		
		st = new StringTokenizer(br.readLine());
		int factCount = Integer.parseInt(st.nextToken());	// 진실을 아는 사람의 수 입력

		for(int i=0; i<factCount; i++) {
			int humanNum = Integer.parseInt(st.nextToken());	// 진실을 아는 사람의 번호
			parents[humanNum] = -1;	// 진실을 아는 사람의 루트를 -1로 설정
		}
		
		partyList = new ArrayList<>();	// 파티 목록 초기화
		
		for(int i=0; i<M; i++) {
			partyList.add(new ArrayList<>());	// 각 파티에 대한 리스트 생성
			st = new StringTokenizer(br.readLine());
			int partyHumanCount = Integer.parseInt(st.nextToken());	// 파티에 참여하는 사람의 수
			
			// 파티에 참여하는 사람의 수가 0보다 큰 경우
			if(partyHumanCount > 0) {
				int firstHuman = Integer.parseInt(st.nextToken());	// 첫 번째 참여자 입력
				partyList.get(i).add(firstHuman);	// 첫 번째 참여자를 리스트에 추가
				
				for(int j=1; j<partyHumanCount; j++) {
					int nextHuman = Integer.parseInt(st.nextToken());	// 다음 참여자 입력
					partyList.get(i).add(nextHuman);	// 다음 참여자를 리스트에 추가
					union(firstHuman, nextHuman);	// 첫 번째 참여자와 다음 참여자를 유니온 연산으로 연결
				}
			}
		}
		
		int resultCount = 0;	// 과장된 이야기를 할 수 있는 경우의 수
		// 여러개의 파티들 탐색하기 
		for(ArrayList<Integer> party: partyList) {
			boolean liePossible = true;	// 거짓말을 할 수 있는지 여부를 나타내는 변수
			// 해당 파티에 참석한 사람들 탐색
			for(int human: party) {
				// 해당 파티에 진실을 아는 사람이 있는 경우
				if(find(human) == -1) {
					liePossible = false;	// 거짓말 할 수 없음
					break;	// 더 탐색할 필요도 없이 해당 파티 탐색 종료
				}
			}
			
			// 거짓말 가능한 경우
			if(liePossible) {
				resultCount++;	// 과장된 이야기를 할 수 있는 경우의 수 증가
			}
		}
		
		System.out.println(resultCount);
	}
	
	// 유니온파인드의 find 연산 (재귀적으로 부모를 찾아감)
	public static int find(int a) {
		// a의 부모가 자기 자신이거나 -1인 경우
		if(a == parents[a] || parents[a] == -1) {
			return parents[a];	// 해당 값(부모)을 반환해줌
		}
		return parents[a] = find(parents[a]);	// 경로 압축을 위해 부모를 루트로 설정 
	}
	
	// 유니온파인드의 union 연산 (두 원손의 집합을 합침)
	public static void union(int a, int b) {
		// 각 원소들의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 두 원소가 같은 집합에 속해 있는 경우 
		if(aRoot == bRoot) {
			return;	// 두 루트가 같으면 이미 연결된 상태이므로 아무 것도 하지 않음
		}
		// 작은 번호의 원소가 루트가 되도록 합침
		else if(aRoot > bRoot) {
			parents[aRoot] = bRoot;	// aRoot를 bRoot에 연결
		}
		else {
			parents[bRoot] = aRoot;	// bRoot를 aRoot에 연결
		}
	}

}
