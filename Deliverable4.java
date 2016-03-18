import java.util.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class Deliverable4 {
	
	static Random rand = new Random();
	static int randNum;
	static ArrayList<int[]> list = new ArrayList<int[]>();
	
	//generates random number from 1 to 100,000
	public static void generateRandNum() {
		randNum = rand.nextInt(100000) + 1;
	}
	
	//generates 100 arrays of random length, with random integers as elements
	@BeforeClass
	public static void generateArrays(){
		for (int i = 0; i < 100; i++){
			generateRandNum();
			int[] a = new int[randNum];
			for (int j = 0; j < a.length; j++) {
				generateRandNum();
				a[j] = randNum;
			}
			list.add(a);
		}
	}
	
	//tests that both sorted and unsorted array have the same number of elements
	@Test
	public void testLength() {
		for (int[] a : list) {
			int unsortedLength = a.length;
			Arrays.sort(a);
			int sortedLength = a.length;
			assertEquals(unsortedLength, sortedLength);
		}
	}
	
	//tests that every element in sorted array is (not strictly) increasing
	@Test
	public void testIsIncreasing() {
		for (int[] a : list) {
			Arrays.sort(a);
			for (int i = 0; i < a.length - 1; i++) {
				assertTrue(a[i] <= a[i+1]);
			}
		}
	}
	
	//tests that sorting an already-sorted array results in same, sorted array
	@Test
	public void testIdempotence() {
		int[] b;
		for (int a[] : list) {
			b = new int[a.length];
			Arrays.sort(a);
			System.arraycopy(a, 0, b, 0, a.length);
			Arrays.sort(b);
			for (int i = 0; i < a.length; i++)
				assertEquals(a[i], b[i]);
		}
	}
	
	//tests that each element in unsorted array is still in sorted array
	@Test
	public void testSameElements() {
		int[] unsorted;
		for (int sorted[] : list) {
			unsorted = new int[sorted.length];
			System.arraycopy(sorted, 0, unsorted, 0, sorted.length);
			Arrays.sort(sorted);
			//change each element in unsorted[] to 0 that matches an element in sorted[]
			for (int i = 0; i < sorted.length; i++) {
				for (int j = 0; j < unsorted.length; j++) {
					if (sorted[i] == unsorted[j]) {
						sorted[i] = 0;
					}
				}
			}
			//test that every element of unsorted[] has matched an element of sorted[] (is 0)
			for (int i = 0; i < sorted.length; i++) {
				assertEquals(sorted[i], 0);
			}
		}
	}
	
	
	
}
