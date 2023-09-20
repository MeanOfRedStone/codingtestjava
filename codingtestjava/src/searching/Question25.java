package searching;

/*
 <문제 25 친구 관계 파악하기>
 BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번부터 N-1번으로 번호가 매겨져 있꼬, 일부 사람은 친구다.
 오늘은 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 - A와 B는 친구다.
 - B와 C는 친구다.
 - C와 D는 친구다.
 - D와 E는 친구다.
 위와 같은 친구관계가 존재하는 지 여부를 구하는 프로그램을 작성하시오.
 */

 /*
  <출제 포인트>
  (1) DFS의 이해
  -1 양방향 그래프에서 DFS 활용 방법 : 연결리스트 , visited
  -2 방문 깊이 탐색
  (2) 자바 라이브러리 활용
  - buffered reader / StringTokenizer
  */

  /*
   <몰랐던 점>
   (1) DFS 유형 파악 부족
   -1 완전 탐색과 방문 깊이 탐색은 또 다르다
   -2 한 노드에 모든 점이 연결된 경우 방문 깊이 깊어지지 않음.
   -3 완전 탐색 아니라 깊이 측정해야 할 경우 break 포인트 파악! : 재귀 함수 목적에 따라 break 포인트 달라짐
   */

import java.util.*;
import java.io.*;

public class Question25 {
    static ArrayList<Integer>[] A;
    static boolean visited[];
    static boolean arrive;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //사람의 수
        int N = Integer.parseInt(st.nextToken());
        //친구 관계의 수
        int M = Integer.parseInt(st.nextToken());
        arrive = false;

        //인접 리스트로 크기 설정
        A = new ArrayList[N];
        //방문한 노드 표시
        visited = new boolean[N];

        //인접 리스트 연결 리스트 초기화
        for(int i = 0; i < N; i++){
            A[i] = new ArrayList<Integer>();
        }

        //친구 관계 그래프 A에 삽입
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            //친구 관계는 양방향 그래프이다.
            A[f1].add(f2);
            A[f2].add(f1);
        }

        //visited를 기준으로 DFS 순환
        for(int i = 0; i < N; i++){
            if(!visited[i]){
                DFS(i, 1);
                //arrive를 원인으로 종료된 경우 방문 되지 않은 점이 있더라도 바로 종료
                //만약 단절되서 종료된 경우는 계속 탐색
                if(arrive){
                    break;
                }
            }
        }
        if(arrive){
            System.out.println("1");
        }else{
            System.out.println(0);
        }
        
    }

    public static void DFS(int v, int depth){
        //
        if(depth == 5 || arrive){
            //5번 순환 호출 하면 종료 || 5번 돈 경우 종료(그니깐 5번 돌면 이후 재귀호출은 다 종료되는거이다)
            arrive = true;
            return;
        }
        // 방문한 노드 표시
        visited[v] = true;
        for(int i : A[v]){
            if(!visited[i]){
                DFS(i, depth+1);
            }
        }
        //노드가 끝난 지점은 다시 방문할 수 있어야 깊이 측정 가능(다른데서 방문하는 3이 있을 수도 있으니 or 순서가 바뀌어서는 가능할수도)
        // 예) 1 -> 2 -> 3 -> 5 (x) : 1 -> 3 -> 2 -> 5 -> 4(o)로 순서가 달라지면 가능할 수 있기 때문에 방문 끝나면 재방문 가능하게 바꿔줘야함.
        visited[v] = false;
    }
    
}
