package searching;


import java.util.*;
public class Baekjoon2798 {
    static boolean choosed[];
    static int max;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        max = 0;
        choosed = new boolean[N];
        int card[] = new int[N];
        for(int i = 0; i < N; i++){
            card[i] = sc.nextInt();
        }
        for(int i = 0 ; i < N - 2; i++){
            int card1 =  card[i];
            for(int j = i + 1; j < N - 1; j++){
                int card2 = card[j];
                for(int k = j + 1; k < N; k++){
                    int card3 = card[k];
                    int sum = card1 + card2 + card3;
                    if(sum <= M){
                        max = Math.max(max, sum);
                    }
                }
            }  
        }
        System.out.println(max);
    }
}
