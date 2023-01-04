class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        new_id = changeToLowerCase(new_id);
        new_id = deleteCharater(new_id);
        new_id = toSingleDot(new_id);
        new_id = noStartEndDot(new_id);
        new_id = noEmptyMakeA(new_id);
        new_id = noLengthGraterThan16(new_id);
        new_id = nolessThan2(new_id);
            
        //System.out.println(new_id);
        answer = new_id;
        return answer;
    }
    
    // 1단계
    public String changeToLowerCase(String new_id) {
        return new_id.toLowerCase();
    }
    // 2단계 (알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자 제거)
    public String deleteCharater(String new_id) {
        return new_id.replaceAll("[^0-9a-z-_.]", "");
    }
    // 3단계
    public String toSingleDot(String new_id) {
        return new_id.replaceAll("[.]{2,}", ".");
    }
    // 4단계
    public String noStartEndDot(String new_id) {
        return new_id.replaceAll("^[.]|[.]$", "");
    }
    // 5단계
    public String noEmptyMakeA(String new_id) {
        if(new_id.equals("")) {
            new_id = "a";
        }
        return new_id;
    }
    // 6단계
    public String noLengthGraterThan16(String new_id) {
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15).replaceAll("[.]$","");
        }
        return new_id;
    }
    // 7단계
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