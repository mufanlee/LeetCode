/**
 * 600. Non-negative Integers without Consecutive Ones
 * @author LiPeng
 * @since 2017/5/2820:47
 */
public class NonnegativeIntegerswithoutConsecutiveOnes {
    public int findIntegers(int num) {
        System.out.println(Integer.toBinaryString(num));
        int []f = new int[32];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < f.length; i++)
            f[i] = f[i-1] + f[i-2];
        int i = 30, sum = 0, prev_bit = 0;
        while (i >= 0) {
            if ((num & (1 << i)) != 0) {
                System.out.println(i);
                sum += f[i];
                if (prev_bit == 1) {
                    sum--;
                    break;
                }
                prev_bit = 1;
            } else {
                prev_bit = 0;
            }
            i--;
        }
        return sum + 1;
    }

    public static void main(String []args) {
        NonnegativeIntegerswithoutConsecutiveOnes s = new NonnegativeIntegerswithoutConsecutiveOnes();
        System.out.println(s.findIntegers(4));
    }
}
