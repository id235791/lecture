package collection;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class SpeedTest {
	public static void main(String[] args) {
		final int SIZE = 10_000_000;
		final List<Integer> arList = new ArrayList<Integer>(SIZE);
		final Set<Integer> hSet = new HashSet<Integer>(SIZE);
		
//		for (int i = 0; i < SIZE; i++) {
//			arList.add(i);
//			hSet.add(i);
//		}
		
		IntStream.range(0, SIZE).forEach(value -> {
			arList.add(value);
			hSet.add(value);
		});
		
		int data = 9_274_362;
		
		Instant start = Instant.now();
		arList.contains(data);
		Instant end = Instant.now();
		
		long elapsedTime = Duration.between(start, end).toMillis();
		System.out.println("List : "+elapsedTime);
		
		start = Instant.now();
		hSet.contains(data);
		end = Instant.now();
		
		elapsedTime = Duration.between(start, end).toMillis();
		System.out.println("Set : "+elapsedTime);
	}
}








