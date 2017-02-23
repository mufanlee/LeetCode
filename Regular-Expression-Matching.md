# Regular Expression Matching

_**Difficulty: Hard**_

## _[Problem](https://leetcode.com/problems/regular-expression-matching/?tab=Description)_
Implement regular expression matching with support for '.' and '*'.

>'.' Matches any single character.
>'*' Matches zero or more of the preceding element.

>The matching should cover the entire input string (not partial).

>The function prototype should be:
>bool isMatch(const char *s, const char *p)

>Some examples:
>isMatch("aa","a") → false
>isMatch("aa","aa") → true
>isMatch("aaa","aa") → false
>isMatch("aa", "a*") → true
>isMatch("aa", ".*") → true
>isMatch("ab", ".*") → true
>isMatch("aab", "c*a*b") → true

## Approach
>1.递归。

这题若没有’\*’，则依次匹配，遇到p[j]!=s[i]时返回false即可，遇到’.’就算s[i]和p[j]匹配，但是有了’\*’，则这题的关键点就是考虑后面带’\*’的字符有0个和多个的情况。

基本思路就是先看字符串s和p的从i和j开始的子串是否匹配，用递归的方法直到串的最后，最后回溯回来得到结果。假设现在走到s的i位置，p的j位置，情况分为下列两种：

(1)p[j+1]不是'*'。情况比较简单，只要判断当前s的i和p的j上的字符是否一样（如果有p在j上的字符是'.',也是相同），如果不同，返回false，否则，递归下一层i+1，j+1; 

(2)p[j+1]是'\*'。那么此时看从s[i]开始的子串，假设s[i],s[i+1],...s[i+k]都等于p[j]那么意味着这些都有可能是合适的匹配，那么递归对于剩下的(i,j+2),(i+1,j+2),...,(i+k,j+2)都要尝试（j+2是因为跳过当前和下一个'\*'字符）。

>2.字符串匹配是一个典型的DP问题。

创建一个二维数组dp[s.length()+1][p.length()+1]。

dp[i][j]表示字符串s[0,i-1]与匹配串p[0,j-1]是否匹配，dp[i+1][j+1]表示s[0,i]和p[0,j]是否匹配。

初始化，dp[0][0]为true，空串匹配空串。dp[i][0]为false，字符串s不为空，但匹配串为空。d[0][j]取决于
有没有'\*'，因为'\*'能匹配空串，例如'a*'能匹配空串。

dp[i+1][j+1]：
1.简单的情况，p[j]!='*'，如果s[i]==p[j]||p[j]=='.'，意味着我们要检查s[0,i-1]是否匹配p[0,j-1]，
dp[i+1][j+1]=dp[i][j]，即dp[i+1][j+1]=(s[i]==p[j]||p[j]=='.')&&dp[i][j]。

2.复杂的情况，p[j]=='*'，意味着我们要去检查掩码，掩码可能重复字符0到多次。

如果重复0次，意味着之前的字符匹配一个空串，dp[i+1][j+1]=dp[i+1][j-1]。（现在看的是i的情况，这种情况取决于s[0，i]与p[0，j-2]匹配）

如果重复1次，dp[i+1][j+1]=dp[i+1][j]。（p[j-1]是被重复的字符，这种情况取决于p[0，j-1]和s[0，i]比较。）

如果重复多次，dp[i+1][j+1]=dp[i][j+1]&&(s[i]==p[j-1]||p[j-1]=='.')。（p[j-1]是被重复的字符，这种情况取决于p[j-1]和s[i]比较，s[0,i-1]与p[j]匹配。）

3.其他情况，为false。

## Solution
```c++
#include <iostream>

using namespace std;

/*class Solution {
public:
    bool match(string s, string p, int i, int j)
    {
        if(p[j] == '\0') return s[i] == '\0';
        if(p[j+1] == '*')
        {
            while(s[i] == p[j] || (i < s.length() && p[j] == '.'))
            {
                if(match(s,p,i,j+2)) return true;
                i++;
            }
            return match(s, p, i, j+2);
        }else if(s[i] == p[j] || (i < s.length() && p[j] == '.'))
            return match(s, p, i+1, j+1);
        return false;
    }
    bool isMatch(string s, string p) {
        return match(s, p, 0, 0);
    }
};*/

class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.length();
        int n = p.length();

        /**
        * dp[i+1][j+1]: if s[0,i] matches p[0,j]
        * if p[j] != '*'
        * dp[i+1][j+1] = dp[i][j] && (s[i]=p[j] || p[j]='.')
        * if p[j] == '*', denote p[j-1] with x,
        * then dp[i+1][j+1] is true if any of the following is true
        * 1) "x*" repeats 0 time and matches empty: dp[i+1][j-1]
        * 2) "x*" repeats 1 time and matches x: dp[i+1][j]
        * 3) "x*" repeats >= 2 times and matches "x*x": dp[i][j+1] && (s[i]==x || x == '.')
        */
        bool dp[m+1][n+1];
        dp[0][0] = true;
        for(int i = 0; i < m; i++)
            dp[i+1][0] = false;

        for(int j = 0; j < n; j++)
            dp[0][j+1] = j > 0 && ((p[j] == '*') && dp[0][j-1]);

        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(p[j]!='*')
                    dp[i+1][j+1] = dp[i][j] && (s[i]==p[j]||p[j]=='.');
                else
                    dp[i+1][j+1] = (dp[i+1][j-1] && j > 0) || dp[i+1][j] || (dp[i][j+1] && j > 0 && (s[i]==p[j-1]||p[j-1]=='.'));
            }
        }
        return dp[m][n];
    }
};

int main()
{
    Solution s;
    cout << s.isMatch("", ".*") << endl;
    cout << s.isMatch("aa", "a") << endl;
    cout << s.isMatch("aa", "aa") << endl;
    cout << s.isMatch("aaa", "aa") << endl;
    cout << s.isMatch("aa", "a*") << endl;
    cout << s.isMatch("aa", ".*") << endl;
    cout << s.isMatch("ab", ".*") << endl;
    cout << s.isMatch("aab", "c*a*b") << endl;
    cout << s.isMatch("ab", ".*b") << endl;
    cout << s.isMatch("abbbbc", ".*bbbbbc") << endl;
    cout << s.isMatch("aaab", "a*c") << endl;
    return 0;
}
```

## Error
1.“.*”

#Editorial Solution
http://blog.csdn.net/fzzying3/article/details/42057935
http://blog.csdn.net/u014265088/article/details/52574639
http://blog.csdn.net/linhuanmars/article/details/21145563