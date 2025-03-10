import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        
        // 1. 특정 조건에 맞는 데이터를 필터링 하는 과정 -> data에서 ext 값이 val_ext보다 작은 데이터만 뽑기
        int columnIndex = getColumnIndex(ext);
        int[][] filteredData = Arrays.stream(data) // 스트림 생성
            .filter(row -> row[columnIndex] < val_ext) // 조건에 맞는 행(row)만 필터링
            .toArray(int[][]::new); // 결과를 2차원 배열로 변환
        
        // 2. 필터링된 데이터를 특정 기준(sort_by)으로 정렬하는 과정 -> sort_by에 해당하는 값을 기준으로 오름차순 정렬
        sortData(filteredData, sort_by);
        
        // 3. 정렬된 데이터를 반환
        answer = filteredData;
        return answer;
    }
    
    // 해당 2차원 배열 데이터를 선택한 컬럼을 기준으로 오름차순 정렬하는 메서드
    public void sortData(int[][] data, String sort_by) {
        int columnIndex = getColumnIndex(sort_by); // 정렬 기준이 되는 컬럼 인덱스
        
        // 2차원 배열을 해당 컬럼을 기준으로 오름차순 정렬
        Arrays.sort(data, (a, b) -> Integer.compare(a[columnIndex], b[columnIndex]));
    }
    
    // 해당 컬럼명을 인덱스로 변환하는 메서드
    public int getColumnIndex(String column) {
        int index = -1;
        
        if (column.equals("code")) {
            index = 0;
        }
        else if (column.equals("date")) {
            index = 1;
        }
        else if (column.equals("maximum")) {
            index = 2;
        }
        else if (column.equals("remain")) {
            index = 3;
        }
        
        return index;
    }
}