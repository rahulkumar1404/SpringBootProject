package Inter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Imp {

	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(6,9,89,34,43,55,66,44));

		List<Integer> list2 = list.stream().filter(i->i%2 == 0).collect(Collectors.toList());
		System.out.println(list2);
		List<Integer> list3 = list.stream().map(i->i*2).collect(Collectors.toList());
		System.out.println(list3);
		
	}
}
