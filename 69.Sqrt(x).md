# Sqrt(x)

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/sqrtx/?tab=Description)_
Implement int sqrt(int x).

Compute and return the square root of x.

## Approach
>1.二分法

>2.牛顿法 x[n+1] = (x[n] - N / x[n]) / 2

>3.位操作。

>Since sqrt(x) is composed of binary bits, I calculate sqrt(x) by deciding every bit from the most significant to least significant. Since an integer n can have O(log n) bits with each bit decided within constant time, this algorithm has time limit O(log n), actually, because an Integer can have at most 32 bits, I can also say this algorithm takes O(32)=O(1) time.

## Solution
```c++
class Solution {
public:
    int mySqrt(int x) {
        long l = 0, r = x;
        while(l < r)
        {
            long mid = (l + r) / 2;
            if(mid*mid < x)
                l = mid + 1;
            else if(mid*mid > x)
                r = mid - 1;
            else return mid;
        }
        return l*l <= x ? l : l-1;
    }
};

class Solution {
public:
    int mySqrt(int x) {
        if(x == 0) return 0;
        int p = 0;
        while((long)(1<<p)*(long)(1<<p) <= x)
            p++;
        int k = p - 1;
        int ans = (1<<k);
        while(k--)
        {
            if((long)(ans | (1<<k)) * (long)(ans | (1<<k)) <= x)
                ans |= (1<<k);
        }
        return ans;
    }
};

class Solution {
public:
    int mySqrt(int x) {
        long ans = x;
        while(ans * ans > x)
            ans = (ans + x / ans) / 2;
        return ans;
    }
};
```

## Error
1.2147395599，注意使用二分法时，整数溢出。