# Add Two Numbers

_**Difficulty: Medium**_

## _[Problem](https://leetcode.com/problems/add-two-numbers/)_

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

## Approach

>首先处理其中一个链表为空情况，当两个链表都不是空时，先按照其中最短的链表将数据相加，再处理另一个多出长度的链表数据。

## Solution

```c++
#include <iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        if(l1 == NULL) return l2;
        if(l2 == NULL) return l1;
        ListNode *font = l1;
        ListNode *last = NULL;
        int c = 0;
        while(l1 != NULL && l2 != NULL)
        {
            int val = l1->val + l2->val;
            l1->val = (val + c) % 10;
            c = (val + c) / 10;
            last = l1;
            l1 = l1->next;
            l2 = l2->next;
        }
        if(l2)
        {
            l1 = l2;
            last->next = l1;
        }
        while(l1)
        {
            int val = l1->val + c;
            l1->val = val % 10;
            c = val / 10;
            last = l1;
            l1 = l1->next;
        }
        if(c)
            last->next = new ListNode(c);
        return font;
    }
};
# testing
int main()
{
    ListNode *l1 = new ListNode(9);
    ListNode *l2 = new ListNode(1);
    ListNode *p = new ListNode(9);
    l1->next = p;
    Solution s;
    ListNode *l = s.addTwoNumbers(l1,l2);
    while(l)
    {
        cout << l->val << "->";
        l = l->next;
    }
    return 0;
}
```
[Editorial Solution](https://leetcode.com/articles/add-two-numbers/)