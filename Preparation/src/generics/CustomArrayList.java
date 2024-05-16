package generics;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayList {
	private int[] data;
	private static int DEFAULT_SIZE = 10;
	private int size = 0;
	
	public CustomArrayList() {
		this.data = new int[DEFAULT_SIZE];
	}
	
	public void add(int num) {
		if(isFull()) {
			resize();
		}
		data[size++] = num;
	}
	
	public void resize() {
		int[] temp = new int[data.length * 2];
		
		for(int i = 0;i<data.length;i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
	
	
	public boolean isFull() {
		return size == data.length;
	}
	
	public int remove() {
		int rem = data[--size];
		return rem;
	}
	
	public void set(int index , int value) {
		data[index] = value;
	}
	
	public int size() {
		return size;
	}
	public int get(int index) {
		return data[index];
	}
	
	public static void main(String[] args) {
		
		CustomArrayList list = new CustomArrayList();
		
		for(int i = 0;i<10;i++) {
			list.add(i);
		}
		for(int i = 0;i<list.size();i++) {
			System.out.print(list.get(i)+ " ");
		}
		
		list.add(11);
		list.add(12);
		list.add(13);
		
		List<Integer> list2 = new ArrayList<>();
		int j = 0;
		while( j < 10) {
			list2.add(j);
			j+=2;
		}
		
		System.out.println("\n"+list.size());
		list2.forEach(x->System.out.println(x));
		System.out.println("_____________________");
		list2.stream()
		.map(x->x*2)
		.forEach(x->System.out.println(x));
	}

}
