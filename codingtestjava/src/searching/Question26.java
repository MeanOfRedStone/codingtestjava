package searching;
/*
 <너비 우선 탐색>
 - FIFO(선입선출) | Queue
 - 목표노드에 도착하는 경로가 여러개일 때 최단 경로 보장.
 */

/*
 <문제 26>
 DFS와 BFS 프로그램
 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
 단, 방문할 수 있는 노드가 여러 개일 경우에는 노드 번호가 작은 것을 먼저 방문하고 더 이상 방문할 수 있는 노드가 없을 때 종료한다.
 노드 번호는 1에서 N까지다.
 */

/*
 <출제 포인트>
 (1) DFS에 대한 이해 및 구현 -> 재귀 호출
 (2) BFS에 대한 이해 및 구현 -> 큐 사용
 (3) 자바 자료구조 이해 및 구현
 -1 Queue Class 사용
 -2 Collection Class 사용
 */

 /*
  <잘 몰랐던 부분>
  (1) Queue 완전한 사용법 : 단순히 add, remove만 하면 안됨 (NULL 값때문에)
  (2) Colttecionts sort의 적재적소의 활용 못함 - 35번 문제 참조
  (3) println 과 print의 차이
  */
import java.util.*;

public class Question26 {
    //DFS 탐색
    static ArrayList<Integer>[] A;
    static boolean visited[];
    //BFS 탐색
    static Queue<Integer> q; 
    static boolean visited2[];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int V = sc.nextInt();
        //인접리스트 생성
        A = new ArrayList[N+1];
        //방문한 노드 표시
        visited = new boolean[N+1];
        visited2 = new boolean[N+1];
        //BFS를 위한 queue 생성
        q = new LinkedList<Integer>();
        //노드 초기화
        for(int i = 1; i < N + 1; i++){
            A[i] = new ArrayList<>();
        }

        //노드 삽입
        for(int i = 0; i < M; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            A[start].add(end);
            A[end].add(start);
        }
        //번호가 작은것부터 방문하기 위해 연결리스트 정렬
        for(int i = 1; i < N + 1; i++){
            Collections.sort(A[i]);
        }

        //깊이 우선 탐색 시작
        DFS(V);
        System.out.println();
        BFS(V);
    }

    public static void DFS(int i){
        System.out.print(i + " ");
        if(visited[i]){
            return;
        }
        visited[i] = true;
        for(int v : A[i]){
            if(!visited[v]){
                DFS(v);
            }
            
        }
    }

    public static void BFS(int i){
        
        q.add(i);
        //어짜피 방문 안한 노드만 넣어줄 거라 필요 없음
        // if(visited2[i]){
        //     return;
        // }
        visited2[i] = true;
        
 
        while(!q.isEmpty()){
            //BFS는 일반적으로 큐만 사용 : 내가 사용한 것은 이도저도 아닌 방법
        //     int temp = q.remove();
        // if(!visited2[temp]){
        //     BFS(temp);
        // }
            int temp = q.poll();
            System.out.print(i + " ");
            for(int v : A[temp]){
                if(!visited2[v]){
                    visited2[v] = true;
                    q.add(v);
                }
            }

        }
        
        
    }
}
