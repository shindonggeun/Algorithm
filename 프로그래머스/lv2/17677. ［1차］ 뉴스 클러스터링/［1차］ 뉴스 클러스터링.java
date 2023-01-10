import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        
        ArrayList<String> union = new ArrayList<>();
        ArrayList<String> intersection = new ArrayList<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i=0; i<str1.length() - 1; i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str1.charAt(i+1);
            
            if((ch1 >= 'a' && ch1 <= 'z') && (ch2 >= 'a' && ch2 <= 'z')) {
                String str = String.valueOf(ch1) + String.valueOf(ch2);
                list1.add(str);
            }
        }
        
        for(int i=0; i<str2.length() - 1; i++) {
            char ch1 = str2.charAt(i);
            char ch2 = str2.charAt(i+1);
            
            if((ch1 >= 'a' && ch1 <= 'z') && (ch2 >= 'a' && ch2 <= 'z')) {
                String str = String.valueOf(ch1) + String.valueOf(ch2);
                list2.add(str);
            }
        }
        
        Collections.sort(list1);
        Collections.sort(list2);
        
        // 교집합 만들기
        for(String s : list1) {
            // list1에 담긴 다중집합 원소가 list2에 있는 경우 list2에 제거 한뒤 intersection 리스트에 추가(교지합)
            if(list2.remove(s)) {
                intersection.add(s);
            }
            union.add(s);
        }
        
        // 합집합 마무리 (교집합 만들때 list2에 남은 다중집합 원소들 union 리스트에 다 추가)
        for(String s: list2) {
            union.add(s);
        }
        
        if(union.size() == 0) {
            answer = 1 * 65536;
        }
        else {
            // 자카드 유사도 -> 교집합 / 합집합
            double jakad = (double)(intersection.size()) / (double)(union.size());
            answer = (int) (jakad * 65536);
        }
        
        
        
        return answer;
    }
    
    
}