package samsungswexpert;

/*
 <DFS + 백트래킹, 개별 합 구하기>
 1. DFS + 백트래킹 까지는 뻔한 문제

 2. 마지막 요소를 문제 다시 확인하면서 DFS + 백트래킹으로 안구해지는 걸 확인
   원소 나올때마다 case DFS 구할까 하다가 어짜피 정해졌기 때문에 case 하드코딩
   각 case 별 요소가 인덱스 벗어나지 않는다면 합 구해주기
 */

/*
 *디버깅 내용 : 조각 중 DFS로 해결안되는 조각 case 찾아서 합 따로 구함
 */

/*

    void lastPieceFind
      1. case 4개로 나누어 합 구하기
      while(true)
       1) 각 요소별 좌표 3개값 구하기
       2) 좌표값이 인덱스 범위 벗어나면 break
       3) 현재 위치 + 요소 3개 값 구하기
       4) 모든 값 합치기
       5) answer와 최댓값 비교한 후 최댓값으로 answer 변경
      
    폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
    정사각형은 서로 겹치면 안 된다.
    도형은 모두 연결되어 있어야 한다.
    정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
    정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.

    붙어있는 4칸을 활용해 최댓값을 구하는 프로그램

    <기능 요구사항>
    1. 정답 초기화
    2. 방문 배열 초기화
    
    for(paper)  
      3. DFS탐색(row, col , 0)

    void DFS탐색
      1. 방문 깊이가 4에 도달하거나 이미 방문한 곳일 경우 방문 종료
      - 방문 깊이가 4일 경우 최댓값 기록

      2. 방문한 곳 표시

      3. 현재 위치에서 방문할 수 있는 곳 DFS탐색 , 방문 깊이 1 증가
       - 방문할 곳이 paper 인덱스 바깥 영역이면 종료

      4. 현재 위치 방문 표시 false


    



      0 0 0 0 0 0 0 0
      0 0 0 4 0 0 0 0
      0 0 4 3 4 0 0 0
      0 4 3 2 3 4 0 0
      4 3 2 1 2 3 4 0
      0 4 3 2 3 4 0 0
      0 0 0 3 0 0 0 0
      0 0 0 0 0 0 0 0

    */
import java.io.*;
import java.util.*;

public class Baekjoon14500 {
    static int[][] paper;
    static int N;
    static int M;
    static int answer;

    static boolean[][] visited;
    static boolean[] lastPiceVisited;
    static List<int[]> visitedLocation;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[] lasePieceCase1 = {0, 1, 2};
    static int[] lasePieceCase2 = {0, 1, 3};
    static int[] lasePieceCase3 = {0, 2, 3};
    static int[] lasePieceCase4 = {1, 2, 3};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sizeInput = br.readLine().split(" ");

        N = Integer.parseInt(sizeInput[0]);
        M = Integer.parseInt(sizeInput[1]);

        paper = new int[N][M];

        for(int i = 0; i < N; i++){
            String[] paperInput = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                paper[i][j] = Integer.parseInt(paperInput[j]);
            }
        }

        visited = new boolean[N][M];
        lastPiceVisited = new boolean[4];
        visitedLocation = new ArrayList<>();

        answer = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                tetrominoDFS(i, j, 0);
                lastPieceFind(i, j);
            }
        }

        System.out.println(answer);
    }

    public static void tetrominoDFS(int row, int col, int depth){
        // 1. 방문 깊이가 4에 도달하거나 이미 방문한 곳일 경우 방문 종료
        if(depth == 4 || visited[row][col]){
            //1 - 1) 방문 깊이가 4일 경우 최댓값 기록
            if(depth == 4){
                int sum = 0;

                for(int[] location : visitedLocation){
                    int sumRow = location[0];
                    int sumCol = location[1];

                    int sumElement = paper[sumRow][sumCol];

                    sum += sumElement;

                    answer = Math.max(answer, sum);
                }
            }

            return;
        }

        // 2. 방문한 곳 표시
        visited[row][col] = true;
        int[] location = new int[] {row, col};
        visitedLocation.add(location);

        // 3. 현재 위치에서 방문할 수 있는 곳 DFS탐색 , 방문 깊이 1 증가
        for(int direction = 0; direction < 4; direction++){
            int nextRow = row + dx[direction];
            int nextCol = col + dy[direction];
            int nextDepth = depth + 1;

            if(nextRow < 0 || nextRow > N - 1 || nextCol < 0 || nextCol > M - 1){
                continue;
            }

            tetrominoDFS(nextRow, nextCol, nextDepth);
        }

        // 4. 현재 위치 방문 표시 해제
        int lastIndex = visitedLocation.size() - 1;
        visitedLocation.remove(lastIndex);
        
        visited[row][col] = false;
    }

    public static void lastPieceFind(int row, int col){
        //lastPiceCase1
        while(true){
            int firstElement = lasePieceCase1[0];
            int firstRow = row + dx[firstElement];
            int firstCol = col + dy[firstElement];
            if(firstRow < 0 || firstRow > N - 1 || firstCol < 0 || firstCol > M - 1){
                break;
            }

            int secondElement = lasePieceCase1[1];
            int secondRow = row + dx[secondElement];
            int secondCol = col + dy[secondElement];
            if(secondRow < 0 || secondRow > N - 1 || secondCol < 0 || secondCol > M - 1){
                break;
            }

            int thirdElement = lasePieceCase1[2];
            int thirdRow = row + dx[thirdElement];
            int thirdCol = col + dy[thirdElement];
            if(thirdRow < 0 || thirdRow > N - 1 || thirdCol < 0 || thirdCol > M - 1){
                break;
            }

            int currentVal = paper[row][col];
            int firstVal = paper[firstRow][firstCol];
            int seoncdVal = paper[secondRow][secondCol];
            int thirdVal = paper[thirdRow][thirdCol];

            int sum = currentVal + firstVal + seoncdVal + thirdVal;
            answer = Math.max(answer, sum);
            break;
        }

        //lastPiceCase2
        while(true){
            int firstElement = lasePieceCase2[0];
            int firstRow = row + dx[firstElement];
            int firstCol = col + dy[firstElement];
            if(firstRow < 0 || firstRow > N - 1 || firstCol < 0 || firstCol > M - 1){
                break;
            }

            int secondElement = lasePieceCase2[1];
            int secondRow = row + dx[secondElement];
            int secondCol = col + dy[secondElement];
            if(secondRow < 0 || secondRow > N - 1 || secondCol < 0 || secondCol > M - 1){
                break;
            }

            int thirdElement = lasePieceCase2[2];
            int thirdRow = row + dx[thirdElement];
            int thirdCol = col + dy[thirdElement];
            if(thirdRow < 0 || thirdRow > N - 1 || thirdCol < 0 || thirdCol > M - 1){
                break;
            }

            int currentVal = paper[row][col];
            int firstVal = paper[firstRow][firstCol];
            int seoncdVal = paper[secondRow][secondCol];
            int thirdVal = paper[thirdRow][thirdCol];

            int sum = currentVal + firstVal + seoncdVal + thirdVal;
            answer = Math.max(answer, sum);
            break;
        }

        //lastPiceCase3
        while(true){
            int firstElement = lasePieceCase3[0];
            int firstRow = row + dx[firstElement];
            int firstCol = col + dy[firstElement];
            if(firstRow < 0 || firstRow > N - 1 || firstCol < 0 || firstCol > M - 1){
                break;
            }

            int secondElement = lasePieceCase3[1];
            int secondRow = row + dx[secondElement];
            int secondCol = col + dy[secondElement];
            if(secondRow < 0 || secondRow > N - 1 || secondCol < 0 || secondCol > M - 1){
                break;
            }

            int thirdElement = lasePieceCase3[2];
            int thirdRow = row + dx[thirdElement];
            int thirdCol = col + dy[thirdElement];
            if(thirdRow < 0 || thirdRow > N - 1 || thirdCol < 0 || thirdCol > M - 1){
                break;
            }

            int currentVal = paper[row][col];
            int firstVal = paper[firstRow][firstCol];
            int seoncdVal = paper[secondRow][secondCol];
            int thirdVal = paper[thirdRow][thirdCol];

            int sum = currentVal + firstVal + seoncdVal + thirdVal;
            answer = Math.max(answer, sum);
            break;
        }

        //lastPiceCase4
        while(true){
            int firstElement = lasePieceCase4[0];
            int firstRow = row + dx[firstElement];
            int firstCol = col + dy[firstElement];
            if(firstRow < 0 || firstRow > N - 1 || firstCol < 0 || firstCol > M - 1){
                break;
            }

            int secondElement = lasePieceCase4[1];
            int secondRow = row + dx[secondElement];
            int secondCol = col + dy[secondElement];
            if(secondRow < 0 || secondRow > N - 1 || secondCol < 0 || secondCol > M - 1){
                break;
            }

            int thirdElement = lasePieceCase4[2];
            int thirdRow = row + dx[thirdElement];
            int thirdCol = col + dy[thirdElement];
            if(thirdRow < 0 || thirdRow > N - 1 || thirdCol < 0 || thirdCol > M - 1){
                break;
            }

            int currentVal = paper[row][col];
            int firstVal = paper[firstRow][firstCol];
            int seoncdVal = paper[secondRow][secondCol];
            int thirdVal = paper[thirdRow][thirdCol];

            int sum = currentVal + firstVal + seoncdVal + thirdVal;
            answer = Math.max(answer, sum);
            break;
        }
    }
}
