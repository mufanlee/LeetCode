# ZigZag Conversion

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/zigzag-conversion/?tab=Description)_
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N

A P L S I I G

Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);

convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

## Approach
>1.沿着对勾的周期，循环添加到数组中，先是竖着numRows个，接着斜着numRows-2个。最后合并起来。

>2.找规律，发现所有行的周期为2\*numRows-2，对于首行和末行之间的行，还要额外重复一次，重复的这次距离本周期起始字符的距离是2\*numRows-2-2\*i。

## Solution
```c++
#include <iostream>

using namespace std;

/*class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows == 1) return s;
        string res[numRows];
        int i = 0;
        while(i < s.length())
        {
            for(int p = 0; i < s.length() && p < numRows; p++)
                res[p] += s[i++];
            for(int q = numRows - 2; i < s.length() && q > 0; q--)
                res[q] += s[i++];
        }
        string ans = "";
        for(int i = 0; i < numRows; i++)
            ans += res[i];
        return ans;
    }
};*/

class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows == 1) return s;
        string ans = "";
        int p = 2 * numRows - 2;
        for(int i = 0; i < numRows; i++)
        {
            for(int j = i; j < s.length(); j+=p)
            {
                ans += s[j];
                if(i > 0 && i < numRows - 1)
                {
                    if(j+p-2*i < s.length())
                        ans += s[j+p-2*i];
                }
            }
        }
        return ans;
    }
};

int main()
{
    Solution s;
    cout << s.convert("PAYPALISHIRING", 3) << endl;
    cout << s.convert("0123456789", 3) << endl;
    cout << s.convert("0123456789", 4) << endl;
    return 0;
}
```