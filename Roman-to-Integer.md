# Roman to Integer

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/roman-to-integer/)_
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

## Approach
>罗马数字有如下符号：
字符 | I | V | X | L | C | D | M
----|----|---|---|---|---|---|---
数字 | 1 | 5 | 10 | 50 | 100 | 500 | 1000

>计数规则：
- 相同的数字连写，所表示的数等于这些数字相加得到的数，例如：III = 3
- 小的数字在大的数字右边，所表示的数等于这些数字相加得到的数，例如：VIII = 8
- 小的数字，限于（I、X和C）在大的数字左边，所表示的数等于大数减去小数所得的数，例如：IV = 4
- 正常使用时，连续的数字重复不得超过三次
- 在一个数的上面画横线，表示这个数扩大1000倍（本题只考虑3999以内的数，所以用不到这条规则）

>1.从前向后遍历罗马数字，如果某个数比前一个数小，则加上该数;反之，减去前一个数的两倍然后加上该数。
2.也可以从后向前遍历罗马数字，如果i数字比i+1小，则减去该数；反之，加上该数。

## Solution
```c++
#include <iostream>
#include <map>

using namespace std;

/*class Solution {
public:
    int romanToInt(string s) {
        int ans = getValue(s[0]);
        for(int i = 1; i < s.length(); i++)
        {
            int cur = getValue(s[i]);
            int pre = getValue(s[i-1]);
            if(cur > pre)
                ans = ans - pre * 2 + cur;
            else
                ans += cur;
        }
        return ans;
    }

    int getValue(char c)
    {
        switch(c)
        {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            return 0;
        }
    }
};*/

class Solution {
public:
    int romanToInt(string s) {
        map<char, int> num;
        num['I'] = 1;
        num['V'] = 5;
        num['X'] = 10;
        num['L'] = 50;
        num['C'] = 100;
        num['D'] = 500;
        num['M'] = 1000;
        int ans = num[s[s.length() - 1]];
        for (int i = s.length() - 2; i >= 0; i--) {
            if (num[s[i]] < num[s[i + 1]])
                ans -= num[s[i]];
            else
                ans += num[s[i]];
        }
        return ans;
    }
};

int main()
{
    Solution s;
    cout << s.romanToInt("III") << endl;
    cout << s.romanToInt("IV") << endl;
    cout << s.romanToInt("V") << endl;
    cout << s.romanToInt("VII") << endl;
    cout << s.romanToInt("IX") << endl;
    cout << s.romanToInt("XL") << endl;
    cout << s.romanToInt("CM") << endl;
    cout << s.romanToInt("MMM") << endl;
    return 0;
}
```
