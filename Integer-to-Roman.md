# Integer to Roman

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/integer-to-roman/?tab=Description)_
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

## Approach
> 1.将所有的情况列出来，直接按位查表。
> 2.贪心算法，建立一个数表，每次通过查表找出当前最大的数，减去该数再继续查表。

## Solution
```c++
#include <iostream>

using namespace std;

/*class Solution {
public:
    string intToRoman(int num) {
        string M[] = {"", "M", "MM", "MMM"};
        string C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        string X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        string I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[num%1000/100] + X[num%100/10] + I[num%10];
    }
};*/

class Solution {
public:
    string intToRoman(int num) {
        string str[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int val[]    = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        string ans = "";
        for(int i = 0; num > 0;)
        {
            if(num >= val[i])
            {
                ans += str[i];
                num -= val[i];
            }
            else
                i++;
        }
        return ans;
    }
};

int main()
{
    Solution s;
    cout << s.intToRoman(56) << endl;
    return 0;
}
```