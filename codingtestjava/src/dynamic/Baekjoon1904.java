package dynamic;




/*
 1로 끝나는 수 , 0으로 끝나는 수
 D[1][1] - 1    |      D[1][0] -
 D[2][1] - 11   |     D[2][0] - 00
 D[3][1] - 111 , (100)    |      D[3][0] - 001  
 D[4][1] - 1111, 1100 , (1001)    |     D[4][0] - 0000 0011
 D[5][1] - 11111, 11100, 11001,  (10000, 10011)     |       D[5][0] - 00001 ,00111, 00100
 D[6][1] - 11111, 111100, 111001, 110000, 110011, (100001) D[6][0] - 001111, 001100, 001001, 000000, 000011

 00을 앞에 붙일 때는 2단계 이전에 수를 모조리 사용
 1을 붙일때는 앞단계의 수를 모조리 사용

 D[1][0] = 0 , D[1][1] = 1
 D[2][0] = 1, D[2][1] = 1

 D[i][1] = D[i-1][1] + D[i-1][0]
 D[i][0] = D[i-2][1] + D[i-2][0]
 -> 나누기 항목이 있어서 (15736) 합했을때 mod보다 커지면 값이 오류가 나온다 그러므로 식 정리되는건 하나로 합쳐줘야함
 DP[i] = DP[i-1] +DP[i-2]
 */
import java.util.*;
import java.io.*;

public class Baekjoon1904 {
    static long mod = 15746;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
        long D[] = new long[N+1];

        D[0] = 1;
        D[1] = 1;
        D[2] = 2;
        if(N >= 2){
            for(int i = 2; i <= N; i++){
                D[i] = (D[i-1] + D[i-2]) % mod;
            }
        }
        

        bw.write(D[N] + "\n");
		bw.flush();
		bw.close();
		br.close();
    }
}
