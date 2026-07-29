/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int count = 1;
        if(head == null || head.next == null) return head;

        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;
            count++;
        }
        k = k % count;
        tail.next = head;
        ListNode temp = head;
        int pos = count - k - 1;
        for(int i = 0; i < pos; i++){
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }
}