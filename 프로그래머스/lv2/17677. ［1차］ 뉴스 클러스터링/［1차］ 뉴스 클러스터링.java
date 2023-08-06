import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        List<String> list1 = new ArrayList<>(); // 문자열 str1에서 두글자씩 끊어서 저장할 리스트 (집합 A)
        List<String> list2 = new ArrayList<>(); // 문자열 str2에서 두글자씩 끊어서 저장할 리스트 (집합 B)
        
        // 문제조건에서 다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다고 했으므로
        // 주어진 해당 문자열들 대문자로 바꿔줌
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        // 문자열 str1 두글자씩 끊어서 저장하는 과정
        for(int i=0; i<str1.length()-1; i++) {
            char ch1 = str1.charAt(i);  // 첫번째 글자
            char ch2 = str1.charAt(i+1);    // 두번째 글자
            
            // 문제 조건에서 영문자로 된 글자쌍만 유효하고, 기타 공백이나, 숫자, 특수문자가 들어있는 경우는 그 글자쌍을 버린다고 했으므로
            // 두글자 모두 대문자 'A' ~ 'Z' 범위에 있는 것을 list에 추가해준다
            if((ch1 >= 'A' && ch1 <= 'Z') && (ch2 >= 'A' && ch2 <= 'Z')) {
                String str = String.valueOf(ch1) + String.valueOf(ch2);
                list1.add(str);
            }
        }
        
        // 문자열 str2 두글자씩 끊어서 저장하는 과정
        for(int i=0; i<str2.length()-1; i++) {
            char ch1 = str2.charAt(i);  // 첫번째 글자
            char ch2 = str2.charAt(i+1);    // 두번째 글자
            
            // 문제 조건에서 영문자로 된 글자쌍만 유효하고, 기타 공백이나, 숫자, 특수문자가 들어있는 경우는 그 글자쌍을 버린다고 했으므로
            // 두글자 모두 대문자 'A' ~ 'Z' 범위에 있는 것을 list에 추가해준다
            if((ch1 >= 'A' && ch1 <= 'Z') && (ch2 >= 'A' && ch2 <= 'Z')) {
                String str = String.valueOf(ch1) + String.valueOf(ch2);
                list2.add(str);
            }
        }
        
        List<String> intersectionList = new ArrayList<>();  // 교집합 저장할 list
        List<String> unionList = new ArrayList<>(); // 합집합을 저장할 list
        
        // 교집합 만들기 과정
        for(String s : list1) {
            // list1에 담긴 다중집합 원소가 list2에 있는 경우 list2에 제거 한뒤 intersectionList에 추가(교집합)
            if(list2.remove(s)) {
                intersectionList.add(s);
            }
            unionList.add(s);   // list1에 있는 원소들은 일단 합집합을 저장하는 unionList에 다 넣어줌
        }
        
        // 합집합 마무리 과정 (교집합 만들때 list2에 남은 다중집합 원소들 unionList에 다 추가)
        for(String s: list2) {
            unionList.add(s); 
        }
        
        // 문제조건에서 집합 A와 집합 B가 모두 공집합인 경우 => 즉 합집합의 크기가 0임
        // 합집합의 크기가 0인 경우 나눗셈이 정의되지 않으므로 자카드 유사드를 1로 정의해준다
        if(unionList.size() == 0) {
            answer = 1 * 65536;
        }
        // 합집합의 크기가 0이 아닌 경우 (집합 A와 집합 B가 둘다 공집합이 아닌 경우임)
        else {
            // 자카드 유사도 -> 교집합 / 합집합
            double jakad = (double)(intersectionList.size()) / (double)(unionList.size());
            answer = (int) (jakad * 65536); // 소수점 아래 버리고 정수부만 출력할 수 있게끔
        }
        return answer;
    }
}