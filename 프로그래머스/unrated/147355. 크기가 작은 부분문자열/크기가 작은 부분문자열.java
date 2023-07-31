class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        // p의 길이가 18이므로 18자리의 수는 int형으로 표현하기에는 한계가 있음
        // 그래서 Long타입으로 변환해서 비교해야한다!!!(안그러면 런타임에러 발생)
        for(int i=0; i<t.length(); i++) {
            // 탐색할 인덱스가 넘어간 경우
            // 즉 t의 문자열 길이까지 간 경우 반복문 빠져나오게끔 하기
            if(i + p.length() - 1 == t.length()) {
                break;
            }
            String str = t.substring(i, i + p.length());
            // 문자열에서 부분 문자열의 숫자 크기가 주어진 수보다 작거나 같은 경우
            if(Long.parseLong(str) <= Long.parseLong(p)) {
                answer++;   // 나온 횟수 증가
            }    
        }
        return answer;
    }
}