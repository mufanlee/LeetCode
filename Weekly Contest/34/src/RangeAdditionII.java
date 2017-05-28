/**
 * 598. Range Addition II
 * @author LiPeng
 * @since 2017/5/2820:37
 */
public class RangeAdditionII {
    public int maxCount(int m, int n, int[][] ops) {
        int mx = m, my = n;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i][0] < mx)
                mx = ops[i][0];
            if (ops[i][1] < my)
                my = ops[i][1];
        }
        return mx * my;
    }
}
