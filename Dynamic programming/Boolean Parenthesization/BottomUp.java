/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Hemant Dhanuka
 */
//this is very Good problem and this whole code written by me without seeing even any hint
//isme maine top down socha but usko implement krne m dikkat aa ri thi toh bottom up implement ka socha and yes i did bottom up
//got Ac
public class Boolean_Parenthesization_BottomUp {

    static int mod = 1003;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int totalCases = s.nextInt();
        Map<String, Integer> map = new HashMap();
        Map<Character, Integer> map1 = new HashMap();

        map1.put('T', 1);
        map1.put('F', 0);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                String key = i + "|" + j;
                Integer value = i | j;
                map.put(key, value);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                String key = i + "&" + j;
                Integer value = i & j;
                map.put(key, value);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                String key = i + "^" + j;
                Integer value = i ^ j;
                map.put(key, value);
            }
        }
        for (int l = 0; l < totalCases; l++) {
            int N = s.nextInt();
            String str = s.next();

            long dp[][][] = new long[N][N][2];

            //filling [0][0], [2][2], [4][4]
            for (int x = 0; x < N; x = x + 2) {
                int value = map1.get(str.charAt(x));
                dp[x][x][value] = 1;
            }

            //filling of table
            int diff = 0;
            while (diff <= N - 1) {

                for (int i = 0; i + diff < N; i = i + 2) {

                    int j = i + diff;

                    long temp[] = new long[2];
                    for (int k = i; k < j; k = k + 2) {
                        String Operator = str.charAt(k + 1) + "";

                        for (int p = 0; p < 2; p++) {
                            for (int q = 0; q < 2; q++) {
                                temp[map.get(p + Operator + q)] += dp[i][k][p] * dp[k + 2][j][q];
                            }
                        }

                    }

                    if (i != j) {
                        dp[i][j][0] = temp[0];
                        dp[i][j][1] = temp[1];
                    }
                }

                diff = diff + 2;
            }
            System.out.println(dp[0][N - 1][1] % mod);
        }
    }
}
