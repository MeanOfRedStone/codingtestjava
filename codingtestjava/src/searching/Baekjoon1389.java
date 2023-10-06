package searching;

/*
 (1)최소 몇 단계 만에 연결 되었는지
 -> 그래프를 BFS 하는 문제

 (2) 방문 배열, 깊이 배열 둘 다 생성

 (3) 최소값의 인덱스 찾아주기 위해 배열 두 개 생성

 */

 /*
  
 
 
 1 -> 3 -> 4
 2 -> 3
 3 -> 1 -> 4 -> 2
 4 -> 1 -> 5 -> 3
 5 -> 4

 t | t | t | t | t |
 1 방문
 친구 3 cnt 1 | 친구 4 cnt 1

 3 방문
 친구 2 cnt 2 

 4 방문
 친구 5 cnt 2

 2 방문

 5 방문

  */

import java.util.*;

public class Baekjoon1389 {
    static ArrayList<Integer> friends[];

    static int cnt[];
    static boolean visited[];
    static int kevin[];
    static Kevin list[];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //유저의 수
        int N = sc.nextInt();
        //친구 관계의 수
        int M = sc.nextInt();

        //친구 관계 그래프
        friends = new ArrayList[N + 1];
        list = new Kevin[N+1];
        for(int i = 1; i < N+1; i++){
            friends[i] = new ArrayList<>();
        }

        //친구관계 그래프 노드 추가
        for(int i = 0; i < M; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            friends[s].add(e);
            friends[e].add(s);
        }

        kevin = new int[N+1];
        for(int i = 1; i < N + 1; i++){
            //친구 방문기록 초기화
            cnt = new int[N+1];
            //친구 방문 확인 초기화
            visited = new boolean[N+1];
            BFS(i);
        }
        Arrays.sort(kevin);
        int min = kevin[1];
        int answer = 0;
        for(int i = 1; i < N+1; i++){
            if(list[i].count == min){
                answer = list[i].index;
                break;
            }
        }
        System.out.println(answer);
    }
    //남은 것 모든 친구들 최단 경로 방문하는 것 & 방문하는 것 기록 
    public static void BFS(int x){
        Queue<Integer> q = new LinkedList<>();
        //BFS 시작한 사람의 관계 수는 0
        cnt[x] = 0;
        //BFS 시작한 사람 방문 확인
        visited[x] = true;

        q.offer(x);
        //현재 BFS중인 사람의 친구 관계 수
        int sum = 0;
        while(!q.isEmpty()){
            //현재 친구
            int now = q.poll();
            //현재 친구마다 관계수를 더해줌
            sum += cnt[now];
            //현재 친구와 연결된 친구 중에 방문하지 않은 사람을 모두 큐에 넣어줌 && 연결관계 체크
            for(int i : friends[now]){
                if(!visited[i]){
                    cnt[i] = cnt[now] + 1;
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }

        //케빈 베이커 리스트 추가
        kevin[x] = sum;
        Kevin item = new Kevin(x, sum);
        list[x] = item;
    }
}

class Kevin {
    int index;
    int count;

    public Kevin(int index, int count){
        this.index = index;
        this.count = count;
    }
}