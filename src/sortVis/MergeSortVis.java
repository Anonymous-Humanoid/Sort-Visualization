/**
 * Date: April 21, 2021
 * Description: A merge sorting visualizer
 * @author Matthew
 * 
 */

package sortVis;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

// Driver class
public class MergeSortVis extends SortPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4880882064540679424L;
	
	// Driver code
	public static void main(String[] args) throws InterruptedException
	{
		JFrame f = new JFrame("Merge Sort Visualizer");
		SortPanel p = new MergeSortVis(500);
		
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
	public MergeSortVis(int[] d)
	{
		super(d);
	}
	
	// Method constructor
	public MergeSortVis(int n)
	{
		super(n);
	}
	
	// Merge sort data
	@Override
	public void sort()
	{
		// Beginning sort
		sort(0, data.length - 1);
		
		// Unselecting data
		select(-1);
	}
	
	// Merge sort data subarray
    private void sort(int l, int r)
    {
        if (l < r)
        {
            // Finding midpoint
            int m = l + (r - l) / 2;
            
            // Sort individual halves
            sort(l, m);
            sort(m + 1, r);
            
            // Merge the sorted halves
            merge(l, m, r);
        }
    }
	
	// Merge subarrays data[l ... m] and data[m + 1 ... r]
	private void merge(int l, int m, int r)
    {
		// Method code sourced from: https://www.geeksforgeeks.org/merge-sort/
		
    	// Finding sizes of subarrays
    	int a = m - l + 1;
        int b = r - m;
        
        // Initializing temporary arrays
        int[] left = new int[a];
        int[] right = new int[b];
        
        // Temporarily copying data
        for (int i = 0; i < a; i++)
        {
        	left[i] = data[l + i];
        	select(i);
        }
        for (int i = 0; i < b; i++)
        {
        	right[i] = data[m + i + 1];
        	select(m + i + 1);
        }
        
        // Initial indices of first, second and merged subarrays
        int i = 0, j = 0, k = l;
        
        // Merging temporary arrays
        while (i < a && j < b)
        {
        	if (left[i] <= right[j])
        	{
        		data[k] = left[i];
        		i++;
        	}
        	else
        	{
        		data[k] = right[j];
        		j++;
        	}
        	
        	select(k);
            k++;
        }
 
        // Copying remaining elements of left subarray
        while (i < a)
        {
            data[k] = left[i];
            select(k);
            
            i++;
            k++;
        }
        
        // Copying remaining elements of right subarray
        while (j < b)
        {
            data[k] = right[j];
            select(k);
            
            j++;
            k++;
        }
    }
}
