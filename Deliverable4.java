import java.util.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class Deliverable4 {
	
	static Random rand = new Random();
	static int randSize;
	static int randNum;
	static ArrayList<int[]> list = new ArrayList<int[]>();
	
	//generates random number from 0 to 9,999
	public static void generateRandSize() {
		randSize = rand.nextInt(10000);
	}
	
	//generates random number to fill elements with
	public static void generateRandNum() {
		randNum = rand.nextInt();
	}
	
	//generates 100 arrays of random length (from 0 to 9,999), with random integers as elements
	@BeforeClass
	public static void generateArrays(){
		for (int i = 0; i < 100; i++){
			generateRandSize();
			int[] a = new int[randSize];
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
	
	//tests that each value in unsorted array is still in sorted array
	@Test
	public void testSameValues() {
		int[] unsorted;
		
		for (int sorted[] : list) {
			int matchedValues = 0;
			unsorted = new int[sorted.length];
			System.arraycopy(sorted, 0, unsorted, 0, sorted.length);	//copies unsorted array, then sorts it
			Arrays.sort(sorted);
			//checks that each element in the sorted array exists in the unsorted array
			for (int i = 0; i < sorted.length; i++) {
				for (int j = 0; j < unsorted.length; j++) {
					if (sorted[i] == unsorted[j]) {
						matchedValues++;
					}
				}
			}
			
			assertEquals(matchedValues, sorted.length);	//assert that as many elements as in array matched to element in unsorted array 
		}
	}
	
	
	
}
