package link;

import sort.Sort;

import java.util.Random;

/**
 * Created by dell on 2017/4/20.
 */
public class LinkNode {
    public static final int MAX_NODES = 10;
    public static final int MAX_VALUE = 100;
    private int value;
    private LinkNode next;
    private static Random rand = new Random();

    public LinkNode() {
        this.value = 0;
        this.next = null;
    }

    public LinkNode(int value) {
        this.value = value;
    }

    public LinkNode(LinkNode p) {
        this.value = p.getValue();
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public static void printLink(LinkNode head) {
        LinkNode p = head;
        if (p == null) {
            return;
        } else {
            while (p != null) {
                System.out.printf("%6d", p.getValue());
                p = p.getNext();
            }
            System.out.println();
        }
    }

    /**
     * the length of link, O(n)
     * @param head
     *        first node of link
     * @return
     *        length of link
     */
    public static int length(LinkNode head) {
        LinkNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.getNext();
        }
        return len;
    }

    public static LinkNode createALink(int num) {
        LinkNode[] nodes = new LinkNode[num];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new LinkNode(rand.nextInt(MAX_VALUE));
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].setNext(nodes[i + 1]);
        }
        return nodes[0];
    }

    public static LinkNode createALink(int[] arr) {
        LinkNode p = null, q = null;
        for (int i = arr.length - 1; i > -1; i--) {
            p = new LinkNode(arr[i]);
            p.setNext(q);
            q = p;
        }
        return  p;
    }

    public static LinkNode tail(LinkNode head) {
        LinkNode p = head;
        if (p != null) {
            while (p.getNext() != null){
                p = p.getNext();
            }
        }
        return p;
    }
    public static LinkNode createALinkWithRing() {
        int m, n;
        LinkNode p, q, r;
        m = rand.nextInt(MAX_NODES);
        n = rand.nextInt(MAX_NODES);
        p = createALink(m);
        q = createALink(n);
        printLink(p);
        printLink(q);
        r = tail(p);
        r.setNext(q);
        tail(q).setNext(r.getNext());

        return p;
    }

    public static LinkNode reverse(LinkNode head) {
        LinkNode p = head, q = null, r = null;
        if (p == null) {
            return p;
        } else {
            while (p != null) {
                q = p.getNext();
                p.setNext(r);
                r = p;
                p = q;
            }
            return r;
        }
    }

    public static void testReverse() {
        LinkNode head = createALink(MAX_NODES);
        printLink(head);
        head = reverse(head);
        printLink(head);
    }

    public static LinkNode addTwoNumbers(LinkNode l1, LinkNode l2) {
        LinkNode p = null, q = null, p1 = l1, p2 = l2;
        int val = 0, carry = 0;
        while (p1 != null && p2 != null) {
            val = (p1.getValue() + p2.getValue()) % 10 + carry;
            carry = (p1.getValue() + p2.getValue()) / 10;

            p = new LinkNode(val);
            p.setNext(q);
            q = p;

            p1 = p1.getNext();
            p2 = p2.getNext();
        }

        while (p1 != null) {
            p = new LinkNode(p1.getValue() + carry);
            carry = p1.getValue() / 10;
            p.setNext(q);
            q = p;
            p1 = p1.getNext();
        }

        while (p2 != null) {
            p = new LinkNode(p2.getValue() + carry);
            carry = p2.getValue() / 10;
            p.setNext(q);
            q = p;
            p2 = p2.getNext();
        }
        return p;
    }

    public static void testAddTwoNumbers() {
        LinkNode l1 = createALink(3);
        LinkNode l2 = createALink(6);
        LinkNode l = addTwoNumbers(l1, l2);
        l = reverse(l);
        printLink(l1);
        printLink(l2);
        printLink(l);
    }

    /**
     * calculate the union of two list
     * @param l1
     * @param l2
     * @return
     */
    public static LinkNode append(LinkNode l1, LinkNode l2) {
        LinkNode p = null, q = null, p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            if (p1.getValue() < p2.getValue()) {
                p = new LinkNode(p1);
                p.setNext(q);
                q = p;
                p1 = p1.getNext();
            } else if (p1.getValue() == p2.getValue()) {
                p = new LinkNode(p1);
                p.setNext(q);
                q = p;
                p1 = p1.getNext();
                p2 = p2.getNext();
            } else { // p1.val > p2.val
                p = new LinkNode(p2);
                p.setNext(q);
                q = p;
                p2 = p2.getNext();
            }
        }

        while (p1 != null) {
            p = new LinkNode(p1);
            p.setNext(q);
            q = p;
            p1 = p1.getNext();
        }

        while (p2 != null) {
            p = new LinkNode(p2);
            p.setNext(q);
            q = p;
            p2 = p2.getNext();
        }

        return p;
    }

    public static void testAppend() {
        int[] arr1 = Sort.generateRandomArray(15, 20);
        int[] arr2 = Sort.generateRandomArray(13, 20);
        Sort.quickSort(arr1, 0, arr1.length - 1);
        Sort.quickSort(arr2, 0, arr2.length - 1);
        LinkNode l1 = createALink(arr1);
        LinkNode l2 = createALink(arr2);
        LinkNode l = append(l1, l2);
        l = reverse(l);
        printLink(l1);
        printLink(l2);
        printLink(l);
    }

    public static boolean containsRing(LinkNode head) {
        LinkNode fast = head, slow = head;
        boolean isRing = false;
        if (fast != null) {
            do {
                fast = fast.getNext().getNext();
                slow = slow.getNext();
            } while (!(fast == null || fast == slow));

            if (fast != null) {
                isRing = true;
            }
        }
        return isRing;
    }

    public static LinkNode getRingEntrance(LinkNode head) {
        LinkNode fast = head, slow = head, cross = head;
        if (fast != null) {
            do {
                fast = fast.getNext().getNext();
                slow = slow.getNext();
            } while (!(fast == null || fast == slow));

            if (fast != null) {
                fast = head;
                while (fast != slow) {
                    fast = fast.getNext();
                    slow = slow.getNext();
                }
                cross = slow;
            }
        }
        return cross;
    }

    public static void testGetRing() {
        LinkNode head = createALinkWithRing();
        LinkNode cross = getRingEntrance(head);
        System.out.println(cross.getValue());
    }

    /**
     * shift link by k
     * @param head
     *        first node of link
     */
    public static LinkNode shiftLink(LinkNode head, int k) {
        int len = length(head);
        int move;
        LinkNode p = head, q, newHead;
        if (p == null) {
            return p;
        } else {
            k = k % len;
            if (k == 0) {
                return head;
            } else if (k > 0) {
                move = len - k;
                for (int i = 1; i < move; i++) {
                    p = p.getNext();
                }
                q = p;
                p = p.getNext();
                newHead = p;
                while (p.getNext() != null) {
                    p = p.getNext();
                }
                p.setNext(head);
                q.setNext(null);
                head = newHead;
            } else {
                k = -k;
//                k = k % len;
                for (int i = 1; i < k; i++) {
                    p = p.getNext();
                }
                q = p;
                p = p.getNext();
                newHead = p;
                q.setNext(null);
                while (p.getNext() != null) {
                    p = p.getNext();
                }
                p.setNext(head);
            }
            return newHead;
        }
    }

    public static void testShiftLink() {
        LinkNode head = createALink(10);
        printLink(head);
        head = shiftLink(head, 31);
        printLink(head);
    }


    public static void mergeSort(LinkNode head) {

    }

    /**
     * cannot modify front and rear
     * @param head
     * @param front
     * @param rear
     */
    public static void splitLink(LinkNode head, LinkNode front, LinkNode rear) {
        LinkNode fast = head, slow = head;
        front = head;
        if (fast == null) {
            rear = null;
        } else {
            while (fast != null) {
                fast = fast.getNext();
                if (fast != null) {
                    fast = fast.getNext();
                    slow = slow.getNext();
                }
                rear = slow;
            }
        }
    }

    public static void testSplit() {
        LinkNode head = createALink(10);
        LinkNode front = null, rear = null;
        printLink(head);
        splitLink(head, front, rear);
        printLink(rear);
    }
}
