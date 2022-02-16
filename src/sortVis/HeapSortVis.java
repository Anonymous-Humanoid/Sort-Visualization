/**
 * Date: April 21, 2021
 * Description: A heap sorting visualizer
 * @author Matthew
 * 
 */

package sortVis;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

// Driver class
public class HeapSortVis extends SortPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2467111045326179002L;
	
	// Driver code
	public static void main(String[] args) throws InterruptedException
	{
		JFrame f = new JFrame("Heap Sort Visualizer");
		SortPanel p = new HeapSortVis(500);
		
		// Setting up frame
		f.add(p);
		f.pack();
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
		f.setVisible(true);
		
		// Painting data
		p.paintComponent(p.getGraphics());
		f.repaint();
		
		// Waiting 1 second
		TimeUnit.SECONDS.sleep(1);
		
		p.sort();
	}
	
	// Method constructor
	public HeapSortVis(int[] d)
	{
		super(d);
	}
	
	// Method constructor
	public HeapSortVis(int n)
	{
		super(n);
	}
	
	// Heap sort data
	@Override
	public void sort()
	{
		// Build heap
		for (int i = data.length / 2 - 1; i >= 0; i--)
		{
			heapify(data.length	, i);
		}
		
		// Extracting individual elements from heap
		for (int i = data.length - 1; i > 0; i--)
		{
			// Moving current root to end
			int temp = data[0];
			select(0, i);
			
			data[0] = data[i];
			data[i] = temp;
			select(0, i);
			
			// Max heapifying reduced heap
			heapify(i, 0);
		}
		
		// Unselecting data
		select(-1);
	}
	
	// Heapify subtree
	private void heapify(int heapSize, int nodeIndex)
	{
		// Method code sourced from: https://www.geeksforgeeks.org/heap-sort/
		int max   = nodeIndex;
		int left  = 2 * nodeIndex + 1;
		int right = 2 * nodeIndex + 2;
		
		select(left, max);
		
		// Checking if left child is larger than root
		if (left < heapSize && data[left] > data[max])
			max = left;
		
		select(right, max);
		
		// Checking if right child is larger than current maximum
		if (right < heapSize && data[right] > data[max])
			max = right;
		
		// Checking whether or not maximum is the root
		if (max != nodeIndex)
		{
			int swap = data[nodeIndex];
			select(nodeIndex, max);
			
			data[nodeIndex] = data[max];
			data[max] = swap;
			select(nodeIndex, max);
			
			// Recursively heapifying affected sub-tree
			heapify(heapSize, max);
		}
	}
}
