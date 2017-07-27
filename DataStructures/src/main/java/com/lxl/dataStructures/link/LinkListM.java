package com.lxl.dataStructures.link;

/**
 * 自己实现linklist
 * linklist类只包含一个数据项，即对连败哦第一个链结点的引用
 * 叫做first，通过它就可以找到其他字段
 *
 * @author lxl
 */
public class LinkListM {

    private LinkM first;

    //初始化的时候  显式把first赋值为null
    public void LinkListM() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(int id, double dd) {
        //插入一个新的链结点，初始化一个链结点
        LinkM newlink = new LinkM(id, dd);
        //把链结点的next指向现在的first指向
        newlink.next = first;
        //把现在的first指向只想新的链结点
        first = newlink;
    }

    //此方法假定链表不为空
    public LinkM deleteFirst() {
        LinkM temp = first;
        first = first.next;
        return temp;
    }

    //输出链表所有链接点
    public void displayList() {
        System.out.print("List (first-->last): ");
        //取first链结点，顺序往后查找
        LinkM current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public LinkM find(int key) {
        LinkM current = first;
        while (current.iData != key) {
            if (current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current;
    }

    //删除的时候，找到key指向的链结点，把链结点前面的next连接到链结点的next
    public LinkM delete(int key) {
        LinkM current = first;
        LinkM previous = first;
        while (current.iData != key) {
            if (current.next == null) {
                return null;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkListM thisll = new LinkListM();
        thisll.insertFirst(12, 21.1);
        thisll.insertFirst(22, 132.1);
        thisll.insertFirst(32, 23);
        thisll.insertFirst(11, 2121.1);
        thisll.displayList();
        while (!thisll.isEmpty()) {
            LinkM alink = thisll.deleteFirst();
            System.out.println("Deleted ");
            alink.displayLink();
            System.out.println("");
        }
        thisll.displayList();
    }

}
