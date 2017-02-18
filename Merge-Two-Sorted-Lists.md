# Merge Two Sorted Lists

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/merge-two-sorted-lists/?tab=Description)_
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

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
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* font = new ListNode(0);
        ListNode* tail = font;
        while(l1 && l2)
        {
            if(l1->val <= l2->val)
            {
                tail->next = l1;
                l1 = l1->next;
            }
            else
            {
                tail->next = l2;
                l2 = l2->next;
            }
            tail = tail->next;
        }
            tail->next = l1 ? l1 : l2;
        return font->next;
    }
};

int main()
{
    ListNode *l1 = new ListNode(1);
    ListNode *l2 = new ListNode(2);
    l1->next = new ListNode(3);
    l1->next->next = new ListNode(5);
    l2->next = new ListNode(4);
    l2->next->next = new ListNode(6);
    Solution s;
    ListNode *l = s.mergeTwoLists(l1,l2);
    while(l)
    {
        cout << l->val << " ";
        l = l->next;
    }
    return 0;
}
```