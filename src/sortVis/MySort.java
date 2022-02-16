/**
 * Date: May 29, 2021
 * Description: A custom sorting algorithm
 * @author Matthew
 * 
 */

package sortVis;

import java.util.*;
import java.util.function.*;

public class MySort
{
	// Driver code
	public static void main(String[] args)
	{
		List<Integer> arr = Arrays.asList(22, 31, 66, 23, 84, 45, 26, 18, 83, 24, 96, 49, 98, 94, 66, 23, 86, 13);
		
		// Printing unsorted array
		System.out.println(arr);
		
		// Printing sorted array
		System.out.println(sort(arr));
	}
	
	// Custom sorting algorithm (assumes at least one element)
	public static List<Integer> sort(List<Integer> arr)
	{
		ToIntFunction<List<Integer>> comparator = list -> list.get(0);
		List<List<Integer>> sorter = new ArrayList<List<Integer>>();
		List<Integer> lastRow = new ArrayList<Integer>();
		List<Integer> output = new ArrayList<Integer>();
		
		// Initializing first sorting row
		sorter.add(lastRow);
		lastRow.add(arr.get(0));
		
		// Initializing sorting list -- O(n)
		for (int i = 1; i < arr.size(); i++)
		{
			if (arr.get(i) > lastRow.get(lastRow.size() - 1))
				sorter.add(lastRow = new ArrayList<Integer>());
			
			lastRow.add(arr.get(i));
		}
		
		// Sorting elements
		do
		{
			// Sorting rows by first element
			sorter.sort(Comparator.comparingInt(comparator));
			
//			int index = 1;
			
			// Inserting sorted data into output
			for (int i = 0; i < sorter.size(); )
			{
				// TODO Insert sorted data into output list
				
				// Removing empty rows
				if (sorter.get(i).size() > 1)
				{
					sorter.get(i).remove(0);
					i++;
				}
				else
				{
					sorter.remove(i);
				}
			}
			
//			// TESTING
//			System.out.println();
//			sorter.forEach(System.out::println);
//			System.out.println();
		}
		while (sorter.size() > 0);
		
		return output;
	}
	
	// In-place element insertion
	public static int insert(List<Integer> arr, Integer e, int startIndex)
	{
		// Searching for first lesser element
		for (int i = startIndex; i < arr.size(); i++)
		{
			if (e > arr.get(i))
			{
				arr.add(i, e);
				
				return ++i;
			}
		}
		
		arr.add(e);
		
		return arr.size();
	}
}
