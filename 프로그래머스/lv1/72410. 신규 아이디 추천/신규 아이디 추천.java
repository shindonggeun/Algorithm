class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        for(int i=1; i<=7; i++){
            if(i == 1) {
                new_id = new_id.toLowerCase();
            }
            if(i == 2) {    // '-', '_', '.'를 제외한 모든 문자 제거
                new_id = new_id.replaceAll("[^0-9a-z-_.]", "");
            }
            if(i == 3) {
                if(new_id.length() - new_id.replace(String.valueOf('.'), "").length() >= 2) {
                    new_id = new_id.replaceAll("[.]{2,}", ".");
                }
            }
            if(i == 4) {
                /*if(new_id.indexOf('.') == 0 ) {
                    new_id = new_id.substring(1, new_id.length());
                }
                if(new_id.equals("") == false && new_id.lastIndexOf('.') == new_id.length() - 1) {
                    //System.out.println("찾았다");
                    new_id = new_id.substring(0, new_id.length() - 1);
                }*/
                
                new_id = new_id.replaceAll("^[.]", "");
                new_id = new_id.replaceAll("[.]$", "");
            }
            if(i == 5) {
                if(new_id.equals("")) {
                    new_id = "a";
                }
            }
            if(i == 6) {
                if(new_id.length() >= 16) {
                    new_id = new_id.substring(0, 15).replaceAll("[.]$","");;   // 0번 인덱스부터 15번 인덱스 전 까지 (즉 0 ~ 14 -> 15개 문자)
                    /*if(new_id.lastIndexOf('.') == new_id.length() - 1) {
                        new_id = new_id.replace(".", "");
                    }*/
                }
            }
            if(i == 7) {
                if(new_id.length() <= 2) {
                    char ch = new_id.charAt(new_id.length() - 1);
                    while(new_id.length() != 3) {
                        new_id+=ch;
                    }
                }
            }
        }
            
        //System.out.println(new_id);
        answer = new_id;
        return answer;
    }
    
    public String changeToLowerCase(String new_id) {
        return new_id.toLowerCase();
    }
    
    public String deleteCharater(String new_id) {
        return new_id.replaceAll("[~!@#$%^&*()=+[{]}:?,<>/]", "");
    }
    
}