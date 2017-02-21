# Longest Palindromic Substring

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/longest-palindromic-substring/?tab=Description)_
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.

Example:

Input: "cbbd"

Output: "bb"

## Approach
>1.暴力法。遍历s中的每一个子串。用两个for循环找出所有子串，第三个for循环用于判断该子串是否为回文串，算法时间复杂度为O(n^3)。

>2.动态规划。删除暴力法中的很多重复判断，很容易想到动态规划，时间复杂度O(n^2)，空间复杂度O(n^2)。

动态规划方程：
 - pal[i][j] 表示子串s[i][j]是否是回文串(true, false)
 - 初始化：pal[i][i] = true(0<=i<n)，if(s[i]==s[i+1])pal[i][i+1]=true(0<=i<n-1)，其余初始化为false
 - pal[i][j] = true if(p[i+1][j-1] && s[i]==s[j])

 >3.中心扩展法。以某个字母为中心，向两边扩展。要考虑两种情况，"aba"，这种长度为奇数的，"abba"，这种长度为偶数的。算法时间复杂度为O(n^2)。

 >4.Manacher算法。

 算法基本要点：首先用一个非常巧妙的方式，将所有可能的奇数/偶数长度的回文子串转换成了奇数长度：在每个字符的两边都插入一个特殊的符号。比如abba变成#a#b#b#a#。为了进一步减少编码的复杂度，可以在字符串的开始加入另一个特殊的字符，这样就不用处理越界问题了，比如$#a#b#b#a#。
然后用一个数组p[i]来记录以字符s[i]为中心的最长回文子串向左/向右扩张的长度。下面计算P[i]，该算法增加两个辅助变量id和mx，其中id表示最大回文子串中心的位置，mx则为id+P[id]，也就是最大回文子串的边界。这个算法的关键点就在这里了：如果mx > i，那么P[i] >= MIN(P[2 * id - i], mx - i)。

[Manacher算法](http://www.jianshu.com/p/799bc53d4e3d#)

## Solution
```c++
#include <iostream>
#include <cstring>
using namespace std;

// 暴力法
/*class Solution {
public:
    bool isPalindrome(string s) {
        for(int i = 0; i < s.length() / 2 ; i++)
            if(s[i] != s[s.length() - 1 - i]) return false;
        return true;
    }
    string longestPalindrome(string s) {
        int maxL = 1;
        int l = 0;
        for(int i = 0; i < s.length(); i++)
            for(int j = 1; j <= s.length() - i; j++)
            {
                string str = s.substr(i, j);
                if(isPalindrome(str) && str.length() > maxL) {
                    maxL = str.length();
                    l = i;
                }
            }
        return s.substr(l, maxL);
    }
};*/

// 动态规划
/*class Solution {
public:
    string longestPalindrome(string s) {
        bool pal[1001][1001] = {false};
        int maxL = 1;
        int x = 0;
        int len = s.length();
        bool flag = true;
        for(int i = 0; i < len; i++)
        {
            pal[i][i] = true;
            if(i < (len-1) && s[i] == s[i+1])
            {
                pal[i][i+1] = true;
                if(flag)
                {
                    x = i;
                    maxL = 2;
                    flag = false;
                }
            }
        }

        for(int l = 3; l <= len; l++)
        {
            for(int i = 0; i <= len - l; i++)
            {
                int j = i + l - 1;
                if(pal[i+1][j-1] && s[i] == s[j])
                {
                    pal[i][j] = true;
                    if(l > maxL)
                    {
                        maxL = l;
                        x = i;
                    }
                }
            }
        }
        return s.substr(x, maxL);
    }
};*/

//中心扩展法
/*class Solution {
public:
    string longestPalindrome(string s) {
        int x = 0;
        int maxL = 1;
        int n = s.length();
        for(int i = 0; i < n; i++)
        {
            int p = i - 1, q = i + 1;
            while(p >= 0 && q < n && s[p] == s[q])
            {
                if((q-p+1) > maxL)
                {
                    maxL = q - p + 1;
                    x = p;
                }
                p--;
                q++;
            }
        }

        for(int i = 0; i < n; i++)
        {
            int p = i, q = i + 1;
            while(p >= 0 && q < n && s[p] == s[q])
            {
                if((q-p+1) > maxL)
                {
                    maxL = q - p + 1;
                    x = p;
                }
                p--;
                q++;
            }
        }
        return s.substr(x, maxL);
    }
};*/

// Manacher
class Solution {
public:
    string longestPalindrome(string s) {
        string str = "$#";
        for(int i = 0; i < s.length(); i++)
        {
            str += s[i];
            str += "#";
        }
        int *p = new int[str.length()];
        memset(p, 0, sizeof(p));
        int id = 0, mx = 0;
        for(int i = 1; i < str.length(); i++)
        {
            p[i] = mx > i ? min(p[2*id-i],mx-i) : p[i] = 1;

            while(str[i-p[i]] == str[i+p[i]]) // 会出现越界问题，所以在开始加上$
                p[i]++;

            if((i+p[i]) > mx)
            {
                mx = i + p[i];
                id = i;
            }
        }

        int maxL = 0, x = 0;
        for(int i = 1; i < str.length(); i++)
        {
            if(p[i] > maxL)
            {
                maxL = p[i];
                x = i;
            }
        }
        //cout << str.substr(x-maxL+1, maxL*2-1) << endl;
        delete p;
        //return s.substr((x-maxL+1)/2, maxL-1);
        return s.substr(x/2-maxL/2, maxL-1);
    }
};

int main()
{
    Solution s;
    cout << s.longestPalindrome("babad") << endl;
    cout << s.longestPalindrome("cbbd") << endl;
    cout << s.longestPalindrome("bb") << endl;
    cout << s.longestPalindrome("aaaa") << endl;
    cout << s.longestPalindrome("ukxidnpsdfwieixhjnannbmtppviyppjgbsludrzdleeiydzawnfmiiztsjqqqnthwinsqnrhfjxtklvbozkaeetmblqbxbugxycrlzizthtuwxlmgfjokhqjyukrftvfwikxlptydybmmzdhworzlaeztwsjyqnshggxdsjrzazphugckgykzhqkdrleaueuajjdpgagwtueoyybzanrvrgevolwssvqimgzpkxehnunycmlnetfaflhusauopyizbcpntywntadciopanyjoamoyexaxulzrktneytynmheigspgyhkelxgwplizyszcwdixzgxzgxiawstbnpjezxinyowmqsysazgwxpthloegxvezsxcvorzquzdtfcvckjpewowazuaynfpxsxrihsfswrmuvluwbdazmcealapulnahgdxxycizeqelesvshkgpavihywwlhdfopmmbwegibxhluantulnccqieyrbjjqtlgkpfezpxmlwpyohdyftzgbeoioquxpnrwrgzlhtlgyfwxtqcgkzcuuwagmlvgiwrhnredtulxudrmepbunyamssrfwyvgabbcfzzjayccvvwxzbfgeglqmuogqmhkjebehtwnmxotjwjszvrvpfpafwomlyqsgnysydfdlbbltlwugtapwgfnsiqxcnmdlrxoodkhaaaiioqglgeyuxqefdxbqbgbltrxcnihfwnzevvtkkvtejtecqyhqwjnnwfrzptzhdnmvsjnnsnixovnotugpzuymkjplctzqbfkdbeinvtgdpcbvzrmxdqthgorpaimpsaenmnyuyoqjqqrtcwiejutafyqmfauufwripmpcoknzyphratopyuadgsfrsrqkfwkdlvuzyepsiolpxkbijqw") << endl;
    return 0;
}
```