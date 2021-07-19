package Utillities;
import try1Amit.Weapon;

public class Queue {
	/*Queue of weapons*/
	
	//attributes
	private Weapon arr[];
	private int front;
	private int last;
	private int capacity;
	private int count;
	
	//Ctor
	public Queue(int size) {
		this.arr = new Weapon[size];
		this.capacity = size;
		this.count = 0;
		this.front = 0;
		this.last = -1;
	}
	//check if queue is empty
	private boolean isEmpty() {
		return this.size() == 0;
	}
	
	//check if queue is full
	private boolean isFull() {
		return this.size() == this.capacity;
	}
	
	//return queue size
	public int size() {
		return this.count;
	}
	
	
	//Dequeue
	public Weapon Dequeue() {
		if(isEmpty()) {
			return null;
		}
		
		Weapon w = arr[this.front];
		this.front = (this.front + 1) % this.capacity;
		this.count--;
		return w;
	}
	
	
	//Enqueue
	public boolean Enqueue(Weapon wpn) {
		if(isFull()) {
			return false;
		}
		this.last = (this.last + 1) % this.capacity;
		arr[this.last] = wpn;
		this.count++;
		return true;
	}

}
