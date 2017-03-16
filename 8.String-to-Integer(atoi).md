# String to Integer (atoi)

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/string-to-integer-atoi/)_

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

## Approach

>题目不难，主要注意细节
- 数字前面可以有空格，如“  123”
- 数字前面不可以出现其他字符，如“ b123”、“ ++123”、“ +-123”、“ -+123”
- 数字中出现不必要字符，返回字符前数字，如“12a12”、“ 123 123”
- 数字越界，若超过负数范围输出INT_MIN(-2147483648)，若超出正数范围输出INT_MAX(2147483647)

## Solution

```c++
#include <iostream>
#include <limits.h>
#include <string>

using namespace std;

class Solution {
public:
    int myAtoi(string str) {
        long long ans = 0;
        int sign = 1;
        int i = 0;
        while(str[i] != '\0' && str[i] == ' ') i++; //数字前面的空格
        if(str[i] == '-') {sign = -sign; i++;}//符号
        if(str[i] == '+' && sign == 1) i++;
        if(str[i] < '0' || str[i] > '9') return 0;//数字前面有非数字
        for(; i < str.length(); i++)
        {
            if(str[i]>= '0' && str[i] <= '9')
                ans = ans * 10 + str[i] - '0';
            else
                break;
            if(ans * sign > INT_MAX || ans * sign < INT_MIN)//数字越界
                return ans * sign > 0 ? INT_MAX : INT_MIN;
        }
        return ans * sign;
    }
};

int main()
{
    Solution s;
    /*cout << s.myAtoi("  -123") << endl;
    cout << s.myAtoi("  b1234") << endl;
    cout << s.myAtoi("  ++1234") << endl;
    cout << s.myAtoi("  12a12") << endl;
    cout << s.myAtoi("  123 123") << endl;
    cout << s.myAtoi("-+1") << endl;*/
    cout << s.myAtoi("-2147483649") << endl;
    return 0;
}
```

## Error

1. "-+1"，起始对符号处理有问题
2. "2147483648"、"-2147483648"、"-2147483649"，越界处理有问题，在比较ans和INT_MAX、INT_MIN时，忘记乘上sign比较