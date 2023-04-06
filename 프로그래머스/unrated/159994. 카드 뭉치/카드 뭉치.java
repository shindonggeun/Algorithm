class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        
        int start1 = 0; // cards1의 시작 인덱스
        int start2 = 0; // cards2의 시작 인덱스
        
        // 카드뭉치들을 이용해서 만들어야하는 문자열 순서 모두 탐색
        for(int i=0; i<goal.length; i++) {
            // cards1의 마지막인덱스까지만 탐색하기(시작인덱스가 cards1 배열길이 넘어가지 않도록)
            if(start1 < cards1.length) {
                if(goal[i].equals(cards1[start1])) {
                    start1++;
                }
            }
            // cards2의 마지막인덱스까지만 탐색하기(시작인덱스가 cards2 배열길이 넘어가지 않도록)
            if(start2 < cards2.length) {
                if(goal[i].equals(cards2[start2])) {
                    start2++;
                }
            }
        }
        
        // 카드1과 카트2 다 탐색 완료된 경우가 목표한 단어배열로 만들수 있는 경우임
        if(start1 + start2 == goal.length) {
            answer = "Yes";
        }
        // 다 탐색하지 못한경우는 목표한 단어배열 만들수 없는 경우
        else {
            answer = "No";
        }
        
        return answer;
    }
}