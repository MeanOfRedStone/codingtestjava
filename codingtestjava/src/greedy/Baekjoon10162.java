package greedy;

/*
 가장 적은 버튼 횟수(최적해)를 구하는 기본적은 그리디 문제
 */

import java.util.*;
import java.io.*;

public class Baekjoon10162 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //요리시간
        int T = Integer.valueOf(br.readLine());

        int aClick = 0;
        int bClick = 0;
        int cClick = 0;

        if(T >= 300){
            aClick += T / 300;
            T = T % 300;
        }
        if(T >= 60){
            bClick += T / 60;
            T = T % 60;
        }
        if(T >= 10){
            cClick += T / 10;
            T = T % 10;
        }

        if(T == 0){
            System.out.print(aClick + " " + bClick + " " + cClick);
        } else{
            System.out.println(-1);
        }


    }
}
