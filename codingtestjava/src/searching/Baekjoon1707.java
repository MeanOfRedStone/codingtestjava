package searching;

/*
 -1 이분 그래프의 정의를 이해하고 있는가?
 인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프.

 -2 이분 그래프 확인하는 방법
 BFS, DFS로 탐색하면서 정점을 방문할 때마다 두 가지 색 중 하나를 칠한다.
 다음 정점을 방문하면서 자신과 인접한 정점은 자신과 다른 색으로 칠한다.
 탐색을 진행할 때 자신과 인접한 정점의 색이 자신과 동일하면 이분 그래프가 아니다.
 BFS의 경우 정점을 방문하다가 만약 같은 레벨에서 정점을 다른 색으로 칠해야 한다면 무조건 이분 그래프가 아니다.
 모든 정점을 다 방문했는데 위와 같은 경우가 없다면 이분 그래프이다.

 */

import java.util.*;
import java.io.*;

public class Baekjoon1707 {
    static ArrayList<Integer> graph[];
    static boolean visited[];
    static boolean bipartite[];
    static boolean bipartiteFlag;
    static ArrayList<String> answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.valueOf(br.readLine());
        answer = new ArrayList<>();
        for(int a = 0; a < K; a++){
            String ve[] = br.readLine().split(" ");
            //정점의 개수
            int V = Integer.valueOf(ve[0]);
            //간선의 개수
            int E = Integer.valueOf(ve[1]);

            //그래프 생성
            graph = new ArrayList[V + 1];
            for(int i = 1; i < V + 1; i++){
                graph[i] = new ArrayList<>();
            }

            //간선 삽입
            for(int i = 0; i < E; i++){
                String node[] = br.readLine().split(" ");
                int s = Integer.valueOf(node[0]);
                int e = Integer.valueOf(node[1]);
                graph[s].add(e);
                graph[e].add(s);
            }

            //방문 확인 그래프
            visited = new boolean[V + 1];

            bipartite = new boolean[V+1];
            boolean flag = false;

            //그래프 노드에 그룹 표시
            for(int i = 1; i < V + 1; i++){
                for(int x : graph[i]){
                    if(!visited[x]){
                        DFS(x, flag);
                    }
                }
            }

            visited = new boolean[V+1];

            bipartiteFlag = true;

            //노드를 바탕으로 이분 그래프 확인
            for(int i = 1; i < V + 1; i++){
                for(int x : graph[i]){
                    if(!visited[x]){
                        flag = bipartite[x];
                        isBipartite(x, flag);
                    }
                }
            }
            if(bipartiteFlag == true){
                answer.add("YES");
            } else{
                answer.add("NO");
            }
        }

        for(String str : answer){
            System.out.println(str);
        }
        
    }

    public static void DFS(int x, boolean flag){
        if(visited[x]){
            return ;
        }

        visited[x] = true;
        bipartite[x] = flag;
        boolean nextFlag;
        if(flag == true){ 
            nextFlag = false;
        }
        else { 
            nextFlag = true; 
        }

        for(int y : graph[x]){
            DFS(y, nextFlag);
        }
    }

    public static void isBipartite(int x, boolean flag){
        if(visited[x]){
            return ;
        }

        visited[x] = true;

        for(int y : graph[x]){
            boolean nowFlag = bipartite[y];

            if(flag == nowFlag){
                bipartiteFlag = false;
            }

            isBipartite(y, nowFlag);
        }

    }
}
