package com.lxl.dataStructures.link;

/**
 * 自己实现有序链表
 * 升序
 *
 * @author lxl
 */
public class SortLinkListM {

    //指向两端的链结点
    private LinkM2 first;

    //初始化的时候  显式把first赋值为null
    public void LinkListM() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insert(long dd) {
        //插入一个新的链结点，初始化一个链结点
        LinkM2 newlink = new LinkM2(dd);
        LinkM2 previous = null;
        LinkM2 current = first;
        //从头开始找链结点，如果dd比链结点数据小，就在链结点前插入newlink
        while (current != null && dd > current.dData) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = newlink;
        } else {
            previous.next = newlink;
        }
        newlink.next = current;
    }

    //此方法假定链表不为空
    public LinkM2 remove() {
        LinkM2 temp = first;
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
