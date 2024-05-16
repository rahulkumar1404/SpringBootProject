package genericsImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		ArrayList<Student> arrayList = new ArrayList<>();
		Student stud = new Student(24, "xyz");
		
		for(int i = 0;i<3;i++) {
			Student stud1 = new Student(i, "xyz "+ i);
			
			arrayList.add(stud1);
		}
		System.out.println(arrayList+"\n");
		ArrayList<Integer> arrayList2 = new ArrayList<>();
		arrayList2.add(2);
		arrayList2.add(3);
		arrayList2.add(20);
		arrayList2.add(421);
		
		arrayList2.add(6);
		
		Collections.sort(arrayList2);
		System.out.println(arrayList2.get(arrayList2.size()-2));
		
		int[] arr = {9,3,1,10,20};
		
		Arrays.sort(arr);
		System.out.println(arr[arr.length-2]);
		
		System.out.println("Stream");
		
		
		Stream<Integer> stream = arrayList2.stream(); 
		List<Integer> newList = stream.filter(i->i%2==0).collect(Collectors.toList());
		System.out.println(newList);
	}

}
