package com.servant.wiki.common.model.queue;


public class LinkedQueue<Item> {
	
	private Node first, last;

	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public void enqueue(Item item){
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else {
			oldLast.next = last;
		}
	}
	
	public Item dequeue(){
		Item item = first.item;
		first = first.next;
		if(isEmpty()){
			first = null;
		}
		return item;
	}
}
