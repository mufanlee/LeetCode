# Palindrome Number

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/palindrome-number/)_
Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

## Approach
>取头尾两个数字比较是否相同，取头部数字时，先用base变量计算本数字有多少位。

## Solution
```c++
#include <iostream>
using namespace std;

class Solution {
public:
    bool isPalindrome(int x) 
    {
        if(x < 0) return false;
        int base = 1;
        while(x / base >= 10)
            base *= 10;
        while(x)
        {
            if(x % 10 != x / base)
                return false;
            x = x % base / 10;
            base /= 100;
        }
        return true;
    }
};

int main()
{
    Solution s;
    cout << s.isPalindrome(1001) <<endl;
    cout << s.isPalindrome(12321) <<endl;
    return 0;
}
```

## Error

1. "1001"，计算base数字多少位时，x/base要>=10，而不是>10
