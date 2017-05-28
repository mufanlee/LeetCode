/**
 * 565. Array Nesting
 * @author LiPeng
 * @since 2017/5/2820:43
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int max = 0;
        boolean []vis = new boolean[nums.length];
        for (int k = 0; k < nums.length; k++) {
            if (!vis[k]) {
                vis[k] = true;
                int cnt = 1;
                int id = nums[k];
                while (true) {
                    if (vis[id]) break;
                    cnt ++;
                    id = nums[id];
                }
                if (cnt > max)
                    max = cnt;
            }
        }
        return max;
    }

    public static void main(String []args) {
        int []nums = {5,4,0,3,1,6,2};
        ArrayNesting s = new ArrayNesting();
        System.out.println(s.arrayNesting(nums));
    }
}
