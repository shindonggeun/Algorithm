import java.util.*;

class Solution {
    
    static int N; // 주사위의 개수
    static int[] combination; // A가 선택한 주사위의 인덱스 조합을 저장한 배열
    static int[] bestCombination; // A가 승리 확률이 가장 높은 조합의 인덱스를 저장한 배열
    static int maxWinCount; // 최대 승리 횟수를 저장하는 변수
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        
        N = dice.length; // 주사위의 개수 설정
        combination = new int[N/2]; // N/2개의 주사위를 선택할 조합 배열 초기화 ([0] ~ [N/2 - 1])
        bestCombination = new int[N/2]; // A가 승리 확률이 가장 높은 조합을 저장할 배열 초기화
        maxWinCount = Integer.MIN_VALUE; // 최대 승리 횟수 최소값으로 초기화
        
        // a가 선택할 주사위 조합 생성하는 메서드 호출
        generateCombination(dice, 0, 0);
        
        answer = new int[N/2]; // 최종 결과를 저장할 배열 초기화
        
        for (int i=0; i<N/2; i++) {
            // 최종 결과 배열 인덱스 값 1부터 시작하도록 변환하여 저장
            answer[i] = bestCombination[i] + 1;
        }
        
        return answer;
    }
    
    // A가 가져갈 주사위 조합을 생성하는 메서드 (백트래킹 이용)
    public void generateCombination(int[][] dice, int depth, int idx) {
        // 해당 깊이가 N/2가 된 경우 (즉, N/2개의 주사위를 선택한 경우) (기저 조건)
        if (depth == N/2) {
            // 현재 조합의 승리 횟수를 계산하는 메서드 호출
            int winCount = calculateWin(dice, combination);
            
            // 혀냊 조합의 승리 횟수가 최대 승리 횟수보다 큰 경우
            if (winCount > maxWinCount) {
                maxWinCount = winCount; // 최대 승리 횟수 갱신
                bestCombination = combination.clone(); // A의 승리 확률이 제일 높은 최고의 조합을 현재 조합으로 복사
            }
            return; // 메서드 종료
        }
        
        // 가능한 모든 조합 생성
        for (int i=idx; i<N; i++) {
            combination[depth] = i; // 현재 깊이에서 주사위 인덱스 설정
            generateCombination(dice, depth+1, i+1); // 다음 깊이의 조합 탐색 
        }
    }
    
    // 선택한 조합에 대한 승리 횟수를 계산하는 메서드
    public int calculateWin(int[][] dice, int[] aCombination) {
        List<Integer> aDiceList = new ArrayList<>(); // A의 주사위 인덱스를 저장할 리스트
        List<Integer> bDiceList = new ArrayList<>(); // B의 주사위 인덱스를 저장할 리스트
        
        boolean[] isSelected = new boolean[N]; // 주사위 선택 여부를 나타내는 배열
        
        // A가 선택한 주사위 조합 탐색
        for (int i: aCombination) {
            aDiceList.add(i); // A가 선택한 주사위의 조합 인덱스를 리스트에 저장
            isSelected[i] = true; // A가 선택했음을 표시
        }
        
        // 모든 주사위에 대해서 탐색
        for (int i=0; i<N; i++) {
            // 해당 주사위를 A가 선택하지 않은 경우 -> B가 선택하게끔 만들어줘야함
            if (!isSelected[i]) {
                bDiceList.add(i); // B가 선택한 주사위의 조합 인덱스를 리스트에 저장
            }
        }
        
        List<Integer> aSumList = new ArrayList<>(); // A의 주사위 합을 저장할 리스트
        List<Integer> bSumList = new ArrayList<>(); // B의 주사위 합을 저장할 리스트
        
        // A의 주사위 합을 계산하여 aSumList에 저장하게끔 메서드 호출
        calculateSum(dice, aDiceList, 0, 0, aSumList);
        // B의 주사위 합을 계산하여 bSumList에 저장하게끔 메서드 호출
        calculateSum(dice, bDiceList, 0, 0, bSumList);
        
        Collections.sort(bSumList); // B의 주사위 합 리스트 오름차순 정렬
        
        int winCount = 0; // 승리 횟수를 나타내는 변수 초기화
        
        // A의 주사위 합 리스트 탐색
        for (int aSum: aSumList) {
            // B의 주사위 합과 비교하여 승리 횟수를 계산하는 메서드 호출하여 승리 횟수 누적
            winCount += calculateWinCount(bSumList, aSum);
        }
        
        // 최종 승리 횟수 반환
        return winCount;
    }
    
    // 주사위의 합을 재귀적으로 계산하여 리스트에 저장하는 메서드
    public void calculateSum(int[][] dice, List<Integer> diceList, int idx, int currentSum, List<Integer> sumList) {
        // 모든 주사위를 탐색한 경우 (가저 조건)
        if (idx == diceList.size()) {
            sumList.add(currentSum); // 현재까지 주사위의 합을 sumList에 저장
            return; // 메서드 종료
        }
        
        int diceIdx = diceList.get(idx); // 현재 주사위의 인덱스를 추출
        // 주사위의 6개의 각 면에 대해서 탐색
        for (int i=0; i<6; i++) {
            // 주사위의 합을 계산할 수 있도록 재귀 호출
            calculateSum(dice, diceList, idx+1, currentSum + dice[diceIdx][i], sumList);
        }
    }
    
    // 이분 탐색을 이용하여 A의 주사위 합보다 작은 B의 주사위 합의 개수를 계산하는 메서드
    public int calculateWinCount(List<Integer> bSumList, int aSum) {
        // 이분 탐색 알고리즘 시작
        // B의 주사위의 합에 대해서 탐색할거임
        int start = 0; // 이분 탐색의 시작점
        int end = bSumList.size() - 1; // 이분탐색의 끝점 
        int count = 0; // A가 승리하는 횟수
        
        while (start <= end) {
            int mid = (start + end) / 2; // 중간값 계산
            
            // B의 주사위의 합이 A의 주사위의 합보다 작은 경우 -> 이 경우 A가 승리하는 경우임
            if (bSumList.get(mid) < aSum) {
                start = mid + 1; // 하한 조정 (탐색 범위 줄임)
                count = mid + 1; // 해당 인덱스 + 1이 승리 횟수이므로 해당 승리 횟수 갱신
            }
            // B의 주사위의 합이 A의 주사위의 합보다 같거나 큰 경우
            else {
                end = mid - 1; // 상한 조정 (탐색 범위 줄임)
            }
        }
        
        // 최종 승리 횟수 반환
        return count;
    }
}