package samsungswexpert;

/*
 * 브루트 포스
 
 * 디버깅 포인트
 (1) 모든 조건은 명시된 이상 사용된다고 봐야한다.
 (2) row, col 둘 다 사용하는 경우는 나눠서 쓰는게 오히려 덜 헷갈려서 빠를 수도
 (3) 계단 높이 차이나는 방식은 2가지가 있다

 * 시간 복잡도
 Big-O : N[첫 방문] * ((N)[행 탐색] + (N)[행 경사로 위한 탐색] + (N) + (N)) = 4 * N^2 

 */

import java.io.*;

public class Baekjoon14890 {
    static int[][] map;
    static int N;
    static int L;

    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mapAndSlide = br.readLine().split(" ");
        N = Integer.parseInt(mapAndSlide[0]);
        L = Integer.parseInt(mapAndSlide[1]);

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            String[] height = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(height[j]);
            }
        }

        answer = 0;

        for(int i = 0; i < N; i++){ // 예상 시간복잡도 2*N^2 20,000회
            findRoad(i);
        }

        System.out.println(answer);
    }

    public static void findRoad(int index){
        // 1. 첫 번째 값과 다른 경우 경사로 확인으로 넘어가고 그렇지 않으면 answer에 1을 더해준다.
        // (Row에서 길 찾기 -> N)

        // 1) 첫 번째 값
        int rowValue = map[index][0];

        // 2) 길의 높이가 같은지 확인
        boolean isRowRoad = true;

        // 2) 해당 row에서 모두 같은지 확인
        for(int i = 1; i < N; i++){
            // 3) 값이 다른 경우 경사로 설치 확인
            if(rowValue != map[index][i]){
                checkSlide(index, true);
                isRowRoad = false;
                break;
            }
        }
            
        // 4) 해당 길의 모든 높이가 같은 경우 answer + 1
        if(isRowRoad){
            answer++;
        }
        

        // 2. Col에서 길 찾기 -> N
        // 1) 첫 번째 값
        int colValue = map[0][index];

        // 2) 길의 높이가 같은지 확인
        boolean isColRoad = true;

        // 2) 해당 col에서 모두 같은지 확인
        for(int i = 1; i < N; i++){
            // 3) 값이 다른 경우 경사로 설치 확인
            if(colValue != map[i][index]){
                checkSlide(index, false);
                isColRoad = false;
                break;
            }
        }
            
        // 4) 해당 길의 모든 높이가 같은 경우 answer + 1
        if(isColRoad){
            answer++;
        }
    }

    public static void checkSlide(int index, boolean isRow){
        boolean[] isSlide = new boolean[N];

        if(isRow){
            // 1)첫 번째 값
            int rowValue = map[index][0];
            
            // 2) 해당 길을 모두 탐색하며 경사로 설치가 성공적으로 가능한지 확인
            for(int i = 1; i < N; i++){
                if(rowValue != map[index][i]){
                    // 3)두 블럭의 높이차가 1보다 큰 경우 종료
                    int heightDifference = rowValue - map[index][i];
                    
                    if(Math.abs(heightDifference) > 1){
                        return;
                    }

                    // 4)왼쪽 블록의 높이가 더 높을 때 경사로의 길이 공간을 확보할 수 있는지 확인. 확보가 안된다면 종료  
                    if(heightDifference > 0){
                        //(1) rowValue 값을 현재의 높이로 변경
                        rowValue = map[index][i];

                        //(2) 확보 가능한 공간이 L만큼 있는지 확인. 그렇지 않다면 종료
                        for(int j = 0; j < L; j++){
                            // 분기1 index값 확인
                            int slideCheck = i + j;
                            // 분기2 index값이 전체 map의 크기보다 커진다면 함수 종료
                            if(slideCheck >= N){
                                return;
                            }
                            //분기 3 이미 계단이 설치돼었다면 함수 종료
                            if(isSlide[slideCheck]){
                                return;
                            }

                            //분기 4 해당 index에서 값이 다르다면 함수 종료
                            if(rowValue != map[index][slideCheck]){
                                return;
                            }
                        }

                        //(3) 계단 설치 표시
                        for(int j = 0; j < L; j++){
                            // 분기1 index값 확인
                            int slideCheck = i + j;
                            // 분기2 계단설치 표시 
                            isSlide[slideCheck] = true;
                        }
                    }

                    // 5) 오른쪽 블록의 높이가 더 높을 때 경사로의 길이 공간을 확보할 수 있는지 확인. 확보가 안된다면 종료  
                    if(heightDifference < 0){
                        //(1) 확보 가능한 공간이 L만큼 있는지 확인. 그렇지 않다면 종료
                        for(int j = 0; j < L; j++){
                            // 분기1 index값 확인
                            int slideCheck = i - 1 - j;
                            // 분기2 index값이 전체 map의 크기보다 작아진면 함수 종료
                            if(slideCheck < 0){
                                return;
                            }
                            //분기 3 이미 계단이 설치돼었다면 함수 종료
                            if(isSlide[slideCheck]){
                                return;
                            }
                            //분기 4 해당 index에서 값이 다르다면 함수 종료
                            if(rowValue != map[index][slideCheck]){
                                return;
                            }
                        }

                        //(2) colValue 값을 현재의 높이로 변경
                        rowValue = map[index][i];

                        //(3) 계단 설치 표시
                        for(int j = 0; j < L; j++){
                            // 분기 1 index 확인
                            int slideCheck = i - 1 - j;
                            // 분기2 계단설치 표시 
                            isSlide[slideCheck] = true;
                        }
                    }
                }
            }        
            //6) 경사로 설치 조건에 위배되지 않는다면 길의 갯수 1 증가
            answer++;
        }

        if(!isRow){
            // 1)첫 번째 값
            int colValue = map[0][index];
            
            // 2) 해당 길을 모두 탐색하며 경사로 설치가 성공적으로 가능한지 확인
            for(int i = 1; i < N; i++){
                if(colValue != map[i][index]){
                    // 3)두 블럭의 높이차가 1보다 큰 경우 종료
                    int heightDifference = colValue - map[i][index];
                    
                    if(Math.abs(heightDifference) > 1){
                        return;
                    }

                    // 4)위쪽 블록의 높이가 더 높을 때 경사로의 길이 공간을 확보할 수 있는지 확인. 확보가 안된다면 종료  
                    if(heightDifference > 0){
                        //(1) colValue 값을 현재의 높이로 변경
                        colValue = map[i][index];

                        //(2) 확보 가능한 공간이 L만큼 있는지 확인. 그렇지 않다면 종료
                        for(int j = 0; j < L; j++){
                            // 분기1 index값 확인
                            int slideCheck = i + j;
                            // 분기2 index값이 전체 map의 크기보다 커진다면 함수 종료
                            if(slideCheck >= N){
                                return;
                            }
                            //분기 3 이미 계단이 설치돼었다면 함수 종료
                            if(isSlide[slideCheck]){
                                return;
                            }
                            //분기 4 해당 index에서 값이 다르다면 함수 종료
                            if(colValue != map[slideCheck][index]){
                                return;
                            }
                        }

                        //(3) 계단 설치 표시
                        for(int j = 0; j < L; j++){
                            // 분기1 index값 확인
                            int slideCheck = i + j;
                            // 분기2 계단설치 표시 
                            isSlide[slideCheck] = true;
                        }
                    }

                    // 5) 아래쪽 블록의 높이가 더 높을 때 경사로의 길이 공간을 확보할 수 있는지 확인. 확보가 안된다면 종료  
                    if(heightDifference < 0){
                        //(1) 확보 가능한 공간이 L만큼 있는지 확인. 그렇지 않다면 종료
                        for(int j = 0; j < L; j++){
                            // 분기1 index값 확인
                            int slideCheck = i - 1 - j;
                            // 분기2 index값이 전체 map의 크기보다 작아진면 함수 종료
                            if(slideCheck < 0){
                                return;
                            }
                            //분기 3 이미 계단이 설치돼었다면 함수 종료
                            if(isSlide[slideCheck]){
                                return;
                            }
                            //분기 4 해당 index에서 값이 다르다면 함수 종료
                            if(colValue != map[slideCheck][index]){
                                return;
                            }
                        }

                        //(2) colValue 값을 현재의 높이로 변경
                        colValue = map[i][index];

                        //(3) 계단 설치 표시
                        for(int j = 0; j < L; j++){
                            // 분기 1 index 확인
                            int slideCheck = i - 1 - j;
                            // 분기2 계단설치 표시 
                            isSlide[slideCheck] = true;
                        }
                    }
                }
            }        
            //6) 경사로 설치 조건에 위배되지 않는다면 길의 갯수 1 증가
            answer++;
        }
    }    
}

/*
findRoad(int index)
1. 첫 번째 값과 다른 경우 경사로 확인으로 넘어가고 그렇지 않으면 answer에 1을 더해준다.
(Row에서 길 찾기 -> N)

1) 첫 번째 값
int rowValue = map[index][0]

2) 해당 row에서 모두 같은지 확인
for(int i = 1; i < N; i++)
    3) 값이 다른 경우 경사로 설치 확인
    if(rowValue != map[index][i]){
        checkSlide(index, true);
        return;
    }

    4) 모든 값이 같은 경우 answer + 1
    answer++;


2. Col에서 길 찾기 -> N
1) 첫 번째 값
int colValue = map[0][index]

2) 해당 col에서 모두 같은지 확인
for(int i = 1; i < N; i++)
    3) 값이 다른 경우 경사로 설치 확인
    if(colValue != map[i][index]){
        checkSlide(index, false);
        return;
    }

    4) 모든 값이 같은 경우 answer + 1
    answer++;


checkSlide(int index, boolean isRow)
1. 행 탐색
if(isRow)
    1)첫 번째 값
    int rowValue = map[index][0]
    
    2) 해당 길을 모두 탐색하며 경사로 설치가 성공적으로 가능한지 확인
    for(int i = 1; i < N; i++)
        if(rowValue != map[index][i])
            3)두 블럭의 높이차가 1보다 큰 경우 종료
            int heightDifference = Math.abs(rowValue - map[index][i])
            if(heihgtDifference > 1){
                return;
            }

            4)경사로의 길이 공간을 확보할 수 있는지 확인. 확보가 안된다면 종료
            //(1) 확보된 공간의 크기
            int affordableSize = 1;
            
            //(2) rowValue 값을 현재의 높이로 변경
            rowValue = map[index][i];

            //(3) 확보 가능한 공간이 L만큼 있는지 확인. 그렇지 않다면 종료
            for(int j = 0; j < L; j++){
                int slideCheck = i + j;

                if(slideCheck >= N){
                    return;
                }

                if(rowValue != map[index][slideCheck]){
                    return;
                }
            }

    //(6) 경사로 설치 조건에 위배되지 않는다면 길의 갯수 1 증가
    answer++;


 (1) 길 : 행, 열 각각을 의미
   -> 길을 지나가려면 길에 속한 모든 칸의 높이가 같아야 한다.
   -> 또는 경사로를 설치해 지나갈 수 있게 해야 한다.
   
 (2) 경사로 설치 조건
   -> 경사로 중복 설치 불가
   -> 높이차가 1인 곳만 설치 가능
   -> 경사로가 설치될 곳은 높이가 모두 같아야 함
   -> 경사로 길이만큼 바닥 있어야 함
 */