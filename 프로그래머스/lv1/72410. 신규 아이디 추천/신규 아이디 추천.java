class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        new_id = changeToLowerCase(new_id);     // 1단계
        new_id = deleteCharater(new_id);        // 2단계
        new_id = toSingleDot(new_id);           // 3단계
        new_id = noStartEndDot(new_id);         // 4단계
        new_id = noEmptyMakeA(new_id);          // 5단계
        new_id = noLengthGraterThan16(new_id);  // 6단계
        new_id = nolessThan2(new_id);           // 7단계
        //System.out.println(new_id);
        answer = new_id;
        return answer;
    }
    
    // 1단계 (대문자 -> 소문자로 치환)
    public String changeToLowerCase(String new_id) {
        return new_id.toLowerCase();
    }
    // 2단계 (알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자 제거)
    public String deleteCharater(String new_id) {
        // 정규표현식 []안에서 ^가 앞에 있으면 not을 표현한다
        // 즉 0~9 (숫자)와 a~z(소문자), 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자는 빈 문자열로(문자 제거) 만듬
        return new_id.replaceAll("[^0-9a-z-_.]", "");    
    }
    // 3단계 (마침표(.)가 2번이상 연속된 부분 마침표(.)으로 치환
    public String toSingleDot(String new_id) {
        return new_id.replaceAll("[.]{2,}", ".");   // .이 2번 이상 연속된 경우 마침표(.)로 치환
    }
    // 4단계 (마침표(.)기 처음이나 끝에 위치하면 제거)
    public String noStartEndDot(String new_id) {
        // 정규표현식에서 ^는 문자열의 시작, $는 문자열의 끝을 의미한다
        // []안에서 |는 OR을 의미
        return new_id.replaceAll("^[.]|[.]$", "");  
    }
    // 5단계 (빈 문자열인 경우 new_id에 "a" 대입)
    public String noEmptyMakeA(String new_id) {
        if(new_id.equals("")) {
            new_id = "a";
        }
        return new_id;
    }
    // 6단계 (new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자 모두 제거)
    // 제거 한 뒤 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거
    public String noLengthGraterThan16(String new_id) {
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15).replaceAll("[.]$",""); 
        }
        return new_id;
    }
    // 7단계 (new_id의 길이가 2자 이하라면, new_id의 마지막 문자 new_id의 길이가 3 될때까지 반복해서 끝에 붙임)
    public String nolessThan2(String new_id) {
        if(new_id.length() <= 2) {
            char ch = new_id.charAt(new_id.length() - 1);
            while(new_id.length() != 3) {
                new_id+=ch;
            }
        }
        return new_id;
    }
    
}