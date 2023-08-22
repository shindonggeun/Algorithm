import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];	// 각 집합의 부모노드를 담을 배열
			makeSet();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 0인 경우 union
				if(command == 0) {	
					unionSet(a, b);	// a가 포함되어 있는 집합과 b가 포함되어 있는 집합 합치기
				}
				// 1인 경우 find
				else if(command == 1) {
					// 비교하는 과정
					int findA = findSet(a);
					int findB = findSet(b);
					
					// a와 b가 같은 집합에 포함되어 있으면
					if(findA == findB) {
						sb.append(1);	// 1 출력
					}
					// a와 b가 같은 집합에 포함되어 있지 않은 경우
					else {
						sb.append(0);	// 0 출력
					}
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}
	
	// 각자 자기 자신만 가지는 집합 만들기 메서드
	public static void makeSet() {
		for(int i=1; i<=N; i++) {
			parents[i] = i;	// 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다. (자신이 곧 대표자인 루트로 표현)
		}
	}
	
	
	public static int findSet(int a) {
		if(a == parents[a]) return a;
//		return find(parents[a]);
		return parents[a] = findSet(parents[a]);	// 최적화한 버전
	}
	
	// a가 포함되어 있는 집합과 b가 포함되어 있는 집합 합치기 (합집합)
	// a가 속한 집합과 b가 속한 집합을 합칠 수 있다면 합치고 true 반환, 아니면 false 반환
	public static boolean unionSet(int a, int b) {	
		int aRoot = findSet(a);	// a의 루트노드(부모노드) 찾기
		int bRoot = findSet(b);	// b의 루트노드(부모노드) 찾기
		
		if(aRoot == bRoot) return false;	// 서로의 대표자가 같은 즉, 같은 집합의 상황이므로 union하지 않음.
		// union 처리 (bRoot를 aRoot 아래로 붙이기!! : 임의로 ..)
		parents[bRoot] = aRoot;
		return true;
	}

}
