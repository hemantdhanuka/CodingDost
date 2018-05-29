/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Hemant Dhanuka
 */

//this is written after jab mene bottom up se kr liya... yha ulta ho gya case
public class Boolean_Parenthesization_TopDown1 {
    static int mod=1003;
    static Map<String,Integer> map=new HashMap();
        static Map<Character,Integer> map1=new HashMap();
        
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int totalCases=s.nextInt();
        
        map1.put('T', 1);
        map1.put('F', 0);
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                String key=i+"|"+j;
                Integer value=i|j;
                map.put(key, value);
            }
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                String key=i+"&"+j;
                Integer value=i&j;
                map.put(key, value);
            }
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                String key=i+"^"+j;
                Integer value=i^j;
                map.put(key, value);
            }
        }
        for(int l=0;l<totalCases;l++){
            int N=s.nextInt();
            String str=s.next();
            
            long dp[][][]=new  long[N][N][2];
            
            for(long d[][]:dp){
                for(long p[]:d){
                    Arrays.fill(p,-1);
            }
            }
            
            
            fun(0,N-1,str,dp);
            System.out.println(dp[0][N-1][1]%mod);    
        }
    }

    private static long[] fun(int i, int j, String str, long[][][] dp) {
        
        if(dp[i][j][0]!=-1  && dp[i][j][1]!=-1){
            return dp[i][j];
        }
        
        if(i==j){
            char booln=str.charAt(i);
            dp[i][j][map1.get(booln)]=1;
            dp[i][j][map1.get(booln)==1?0:1]=0;
            return dp[i][j];
        }
        
        long temp[]=new long[2];
        for(int k=i;k<=j-2;k=k+2){

            long temp1[]=fun(i, k, str, dp);
            
            long temp2[]=fun(k+2, j, str, dp);
            
           
            for(int p=0;p<2;p++){
                for(int q=0;q<2;q++){
                   String oprter=str.charAt(k+1)+"";
                   String key=p+oprter+q;
                   temp[map.get(key)]+=temp1[p]*temp2[q];
                }
            }
            
            
        }
        
        dp[i][j][0]=temp[0];
        dp[i][j][1]=temp[1];
        
        return dp[i][j];
        
    }
    
    
    
    
}
