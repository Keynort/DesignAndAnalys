import java.io.*;
import java.util.*;

public class Bellman {

    public static int n = 4;
    public static int[][] dist = {
            { 0, 0, 0, 0, 0 }, { 0, 0, 10, 15, 20 },
            { 0, 10, 0, 25, 25 }, { 0, 15, 25, 0, 30 },
            { 0, 20, 25, 30, 0 },
    };
    public  static int[][] memo = new int[n + 1][1 << (n + 1)];
    public static int fun(int i, int mask) {
        if (mask == ((1 << i) | 3))
            return dist[1][i];
        if (memo[i][mask] != 0)
            return memo[i][mask];
        int res = 1000000000;
        for (int j = 1; j <= n; j++)
            if ((mask & (1 << j)) != 0 && j != i && j != 1)
                res = Math.min(res,
                        fun(j, mask & (~(1 << i)))
                                + dist[j][i]);
        return memo[i][mask] = res;
    }

    public static void main(String[] args) {
        int ans =1000000000;
        for (int i = 1; i <= n; i++)
            ans = Math.min(ans, fun(i, (1 << (n + 1)) - 1)
                    + dist[i][1]);
        System.out.println(
                "The cost of most efficient tour = " + ans);
    }
}

