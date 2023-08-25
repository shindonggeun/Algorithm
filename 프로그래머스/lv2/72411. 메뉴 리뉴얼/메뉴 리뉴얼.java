import java.util.*;

class Solution {
    static Map<String, Integer> menuMap = new LinkedHashMap<>();  // key: 코스 요리 조합, value: 주문된 횟수
    static int[] orderMaxCount;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        orderMaxCount = new int[course.length];
        
        for(int i=0; i<orders.length; i++) {
            char[] order = orders[i].toCharArray();
            Arrays.sort(order); // 해당 코스요리(알파벳 배열) 오름차순 정렬
            String orderStr = new String(order); // 문자열로 합쳐서 저장
            
            // 단품메뉴들 단품메뉴 개수만큼 해당 코스요리 조합 뽑기
            for(int j=0; j<course.length; j++) {
                backTracking(0, 0, course[j], "", orderStr, j);
            }
        }
        
        List<String> menuNameList = new ArrayList<>(menuMap.keySet());  // map에서 key값(메뉴 이름)들 list화
        List<String> resultList = new ArrayList<>();    // 가장 많이 함께 주문된 단품메뉴조합(코스요리) 이름을 저장할 list
        
        for(String menuName: menuNameList) {
            for(int i=0; i<course.length; i++) {
                // 해당 단품메뉴조합(코스 요리)가 최대 주문 횟수면서 동시에 2명 이상이 주문 한 경우면서 동시에 단품메뉴개수가 단품메뉴들로 구성된 요리코스이름(메뉴 이름 길이) 와 같은 경우
                if(menuMap.get(menuName) == orderMaxCount[i] && menuMap.get(menuName) >= 2 && course[i] == menuName.length()) {
                    resultList.add(menuName);   // 리스트에 추가
                }
            }
        }
        
        Collections.sort(resultList);   // 오름차순 정렬
        answer = new String[resultList.size()];
        
        for(int i=0; i<answer.length; i++) {
            answer[i] = resultList.get(i);
        }
        return answer;
    }
    // 백트래킹 메서드 (조합 메서드)
    public void backTracking(int depth, int idx, int count, String makeStr, String orderStr, int courseNum) {
        // 해당 깊이(요리코스 선택 개수)가 요리 코스 개수와 같아지는 경우 (종료조건)
        if(depth == count) {
            menuMap.put(makeStr, menuMap.getOrDefault(makeStr, 0) + 1);
            orderMaxCount[courseNum] = Math.max(orderMaxCount[courseNum], menuMap.get(makeStr));
            return; // 메서드 종료
        }
        
        for(int i=idx; i<orderStr.length(); i++) {
            backTracking(depth+1, i+1, count, makeStr + orderStr.charAt(i), orderStr, courseNum);
        }
    }
}