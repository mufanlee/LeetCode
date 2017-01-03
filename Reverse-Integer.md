# Reverse Integer

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/reverse-integer/)_

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

## Solution

```c++
#include <iostream>

using namespace std;

class Solution {
public:
    int reverse(int x) {
        int ans = 0;
        while(x != 0)
        {
            int res = ans;
            ans = ans * 10 + (x % 10);
            if(ans / 10 != res) return 0;
            x = x / 10;
        }
        return ans;
    }
};

int main()
{
    Solution s;
    cout << s.reverse(123) << endl;
    cout << s.reverse(-123) << endl;
    cout << s.reverse(-100) << endl;
    cout << s.reverse(1000000003) << endl;
    return 0;
}
```