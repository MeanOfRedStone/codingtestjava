package samsungswexpert;

/*
 * 브루트 포스
 모든 경우의 수 다 찾보았다.

 *디버깅 포인트
 (1) 시간복잡도 문제
 -> 계산 방식을 간단히 바꿔서 해결

 시간 복잡도 : nCr * n(팀 편성) + n * (n - 1) ( 합 구하기 ) n <= 20이기 때문에 2초미만 가능

 (2) 방문 확인을 ArrayList -> boolean[] 로 바꿈
 */

import java.util.*;
import java.io.*;

public class Baekjoon14889 {
    static int[][] board;
    static int N;
    static int visitCount;

    static boolean[] startVisited;
    static int min;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        board = new int[N][N];

        for(int i = 0; i < N; i++){
            String[] inputScore = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(inputScore[j]);
            }
        }

        startVisited = new boolean[N];
        visitCount = 0;
        min = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            startCombination(i, 0);
        }

        System.out.println(min);
    }

    public static void startCombination(int peopleIndex, int depth){
        //1. start의 사이즈가 N / 2가 되면 점수를 비교하고 종료
        if(depth == N / 2){
            compareTeamScore();
            return;
        }

        //2. start 팀에 현재 선수가 포함되있지 않다면  추가
        if(startVisited[peopleIndex]){
            return;
        }

        startVisited[peopleIndex] = true;

        //3. 다음 차례의 선수를 추가해준다.
        for(int i = peopleIndex; i < N; i++){
            startCombination(i, depth + 1);
        }

        //4. 조합을 찾은뒤 다음 조합을 찾기 위해 마지막 요소를 삭제
        startVisited[peopleIndex] = false;
    }

    static int startScore;
    static int linkScore;
    static List<Integer> playerCombination;
    static boolean[][] visitedCombination;

    public static void compareTeamScore(){
        //1. 초기화
        startScore = 0;
        linkScore = 0;

        //2. 이중 루프를 돌며 방문한 곳끼리 조합된 경우 startScore 계산, 그렇지 않은 곳끼리 조합된 경우 link의 값으로 더해준다.
        for(int i = 0; i < N - 1; i++){
            for(int j = i; j < N; j++){
                // 방문한 곳끼리 조합된 경우 startScore 계산
                if(startVisited[i] == true && startVisited[j] == true){
                    startScore += board[i][j] + board[j][i];
                }

                if(startVisited[i] == false && startVisited[j] == false){
                    linkScore += board[i][j] + board[j][i];
                }
            }
        }
        
        // 3. 두 팀 점수의 차를 구해줌
        int scoreDifference = Math.abs(linkScore - startScore);

        //4. 기존의 min 값과 비교
        min = Math.min(min, scoreDifference);
    }
}

/*

public static void addScore(리스트){
    score += board[리스트1][리스트2]
}
 */

/*

List<Integer> start = new ArrayList<>();
int min = Integer.MAX_VALUE;
for(N)
  findCombination(peopleIndex)

public static void startCombination(int peopleIndex){
    1. start.size() == N / 2
        List<Integer> link = linkCombination(start);
        compareTeamScore(people, link)
        return;

    2. start.add(peopleIndex);    

    3. for(i = peopleIndex + 1; i < N){
        findCombination(i)   
    }

    4. lastInex
    start.remove(lastIndex);

    
}

public static void linkCombination(List<Integer> start){
    1. List<Integer> link = new ArrayList<>();

    2. 전체 사람들의 인덱스 중에서 start팀에 없는 수를 link팀에 넣어준다
    for(N)
      if(!start.contains(N)){

      }

    3. 반환
    return link;
}


public static void compareTeamScore(List<Integer> people, List<Integer> start){
    1. start 팀의 사람들의 스코어를 구해줌
    int startTotalScore = 0;
    for(int playerIndex : start){
        findPlayer(plyaerIndex, start)
    }

    2. link 팀 사람들의 스코어를 구해줌
    int linkTotalScore = 0;

    3. 두 팀 점수의 차를 구해줌

    4. 기존의 min 값과 비교
}

public static void findPlayer(int playerIndex, List<Integer> team){
    1. 리스트의 수가 2가되면 합을 구해준다 후 종료
    addScore(리스트)

    2. 리스트에 현재 인덱스를 넣어줌

    3. team에서 다음 인덱스의 player로 addScore

    4. 다음 player의 인덱스 삭제
}

public static void addScore(리스트){
    score += board[리스트1][리스트2]
}

  
 */

 /*
  10개
  45개 조합

  200 * 45
  */