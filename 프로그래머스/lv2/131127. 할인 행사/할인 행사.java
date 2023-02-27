import java.util.*;

class Solution {
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> wishList = new HashMap<>();     // key: 구매할 물품, value: 상품 수
        HashMap<String, Integer> map = new HashMap<>();

        // 구매할 상품과 그거에 맞는 수 저장해주기
        for(int i=0; i<want.length; i++){
            wishList.put(want[i], number[i]);
        }

        // 첫날부터 10일까지의 할인되는 물품 탐색
        for(int i=0; i<10; i++){
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }
        
        // 이 날짜에 구매가능한지 확인해서 가능하면 가능한날 + 해줌
        if(want(wishList, map)) {
            answer++;
        }

        // 만약 할인물품이 10일까지만 판매한다면 구매가능한 날짜 리턴해줌
        if(discount.length==10) {
            return answer;
        }

        // 10일이후(11일 부터) 할인 마지막날까지 탐색
        for(int i=10; i<discount.length; i++){
            map.put(discount[i-10], map.getOrDefault(discount[i-10], 0) - 1);
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);


            if(want(wishList, map)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean want(HashMap<String, Integer> wishList, HashMap<String, Integer> map){

        // 구매할 물품을 조회해서
        for(String key : wishList.keySet()){
            // 구매할 물품의 개수가 할인 물품 개수와 다른 경우 구매 못함 
            if(map.get(key) != wishList.get(key)) {
                return false;
            }
        }
        
        // 구매가능
        return true;
    }
}