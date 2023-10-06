package searching;

/*
 -1 촌수 트리의 문제로 풀자 부모-자식 (트리 )
 -2 트리를 시작점부터 백트래킹 + DFS해서 목표지점에 도달할 때의 깊이를 찾자
 -3 트리의 순회는 그래프로 표현 한뒤 DFS로 하면 된다.
 -4 백트래킹 -arraylist의 경우 for 루프 끝나고 해준다.(일반 2차원 배열은 DFS 끝날때마다)
 */
/*
  1 -> 2 -> 7
         -> 8
         -> 9
    -> 3

   4 -> 5
     -> 6
 
visited
 f | f | f | f | f | f | f | f | f |
 */
 

import java.util.*;

public class Baekjoon2644 {
    static boolean visited[];
    static ArrayList<Integer> people[];
    static int answer;
    static int p1;
    static int p2;
    static int n;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //전체 사람의 수
        n = sc.nextInt();
        //촌수를 구하느 사람
        p1 = sc.nextInt();
        p2 = sc.nextInt();

        //찾은 사람을 기록
        visited = new boolean[n+1];

        //전체 사람의 수만큼 ArrayList 형성
        people = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++){
            people[i] = new ArrayList<>();
        }

        //부모 자식간의 관계의 개수
        int m = sc.nextInt();
        for(int i = 0; i < m ;i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            //부모 자식 관계 그래프 형성
            people[s].add(e);
            people[e].add(s);
        }
        answer = -1;
        DFS(p1 , 0);
        System.out.println(answer);
    }

    public static void DFS(int x, int cnt){
        if(visited[x] || x == p2){
            if(x == p2){
                answer = cnt;
            } 
            return ;
        }

        visited[x] = true;

        for(int i : people[x]){
            DFS(i, cnt + 1);
        }
        //백트래킹 위해 DFS 한 번 끝나면 현재 방문 경로는 초기화시켜준다
        visited[x] = false;
    }
}
