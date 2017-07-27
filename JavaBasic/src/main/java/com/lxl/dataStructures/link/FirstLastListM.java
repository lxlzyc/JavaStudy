package com.lxl.dataStructures.link;

/**
 * 自己实现双端链表
 * 双端链表相对与传统链表，多了一个新特性，即对最后一个链结点的引用
 * 对最后一个链结点的引用允许在最后插入链结点
 *
 * @author lxl
 */
public class FirstLastListM {

    //指向两端的链结点
    private LinkM2 first;
    private LinkM2 last;

    //初始化的时候  显式把first赋值为null
    public void LinkListM() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(long dd) {
        //插入一个新的链结点，初始化一个链结点
        LinkM2 newlink = new LinkM2(dd);
        //把链结点的next指向现在的first指向
        newlink.next = first;
        //把现在的first指向只想新的链结点
        first = newlink;
    }

    public void insertLast(long dd) {
        //插入一个新的链结点，初始化一个链结点
        LinkM2 newlink = new LinkM2(dd);

        if (isEmpty()) {
            first = newlink;
        } else {
            last.next = newlink;
            last = newlink;
        }
    }

    //此方法假定链表不为空
    public long deleteFirst() {
        long temp = first.dData;
        if (first.next == null) {
            last = null;
        }
        first = first.next;
        return temp;
    }

    //输出链表所有链接点
    public void displayList() {
        System.out.print("List (first-->last): ");
        //取first链结点，顺序往后查找
        LinkM2 current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

//	public LinkM2 find(int key){
//		LinkM2 current = first;
//		while(current.iData != key){
//			if(current.next == null){
//				return null;
//			}else{
//				current = current.next;
//			}
//		}
//		return current;
//	}
//	//删除的时候，找到key指向的链结点，把链结点前面的next连接到链结点的next
//	public LinkM2 delete(int key){
//		LinkM2 current = first;
//		LinkM2 previous = first;
//		while(current.iData != key){
//			if(current.next == null){
//				return null;
//			}else{
//				previous = current;
//				current = current.next;
//			}
//		}
//		if(current == first){
//			first = first.next;
//		}else{
//			previous.next = current.next;
//		}
//		return current;
//	}

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
