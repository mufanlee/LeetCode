# Remove Duplicates from Sorted List

_**Difficulty: Easy**_

## _[Problem](https://leetcode.com/problems/remove-duplicates-from-sorted-list/?tab=Description)_
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,

Given 1->1->2, return 1->2.

Given 1->1->2->3->3, return 1->2->3.

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
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode* p = head;
        while(p && p->next)
        {
            if(p->val == p->next->val)
            {
                ListNode *q = p->next;
                p->next = p->next->next;
                delete q;
            }
            else
                p = p->next;
        }
        return head;
    }
};

int main()
{
    ListNode* head = new ListNode(1);
    head->next = new ListNode(1);
    head->next->next = new ListNode(2);
    Solution s;
    s.deleteDuplicates(head);
    while(head)
    {
        cout << head->val << " ";
        head = head->next;
    }
    return 0;
}
```

[Editorial Solution](https://leetcode.com/articles/remove-duplicates-sorted-list/)