package night;

import java.io.*;
import java.util.Scanner;
class array{
    public static long pNew;
    public static long pPrev;
}

public class Problem3 {
    private static final int MAX_PROB = 1000;
    private static final int MAX_N = 10000;
    private static final int MAX_M = 100;
    public  final static long[][] oneCount = new long[2][MAX_N];
    private static int[] pNew;
    private static int[] pPrev;

    public static void  main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int nprob, curprob, index;
        int m, n;
        long result;
        char[] inbuf = new char[1024];
        nprob = sc.nextInt();
        if((nprob < 5) || (nprob > MAX_PROB)) {
            System.err.println("problem count "+ nprob+" not in range 5 .. "+MAX_PROB +"\n");
        }
        for(curprob = 1; curprob <= nprob ; curprob++)
        {
            index = sc.nextInt();
            m = sc.nextInt();
            n = sc.nextInt();
            if(index != curprob) {
                System.err.println( "problem index "+index+" not = expected problem "+curprob+"\n");
            }
            if((m < 3) || (m > MAX_M) || (n < 2) || (n > MAX_N)) {
                System.err.println("problem "+index+": m "+m+" out of rande [2, "+MAX_M+"] or n "+n+" out of range [2, "+MAX_N+"]\n");
            }
            result = FindMPartions(n, m, 0);
            System.out.println(+curprob+" "+result);
        }
    }

    private static long FindMPartions(int n, int m, int level) {
        long total, rem1, subcnt;
        int n1, rem, j, i;
        
        if(n < m) {
            for(int k=0; k<MAX_N; k++) {
               oneCount[level & 1][0]= 0;
                }
            oneCount[level & 1][n] = 1;
            return 1;
        }
        n1 = n/m;
        rem = n % m;
        rem1 = n1 % m;
        subcnt = FindMPartions(n1, m, level+1);
        total = subcnt;
        for(int k=0; k<MAX_N; k++) {
               oneCount[level & 1][0]= 0;
                }
         array.pNew = oneCount[level & 1][0];
        array.pPrev = oneCount[(level+1) & 1][0];
        
        pNew[rem] = (int) subcnt;   
        for(i = 1; i <= n1 ; i++) { 
            total = (total + i*pPrev[i]);
            if(pPrev[i] > 0) {
                for(j = 1; j <= i ; j++) {  
                    pNew[j*m+rem] += pPrev[i];
                }
            }
        }
        
        return total;
    }
}
