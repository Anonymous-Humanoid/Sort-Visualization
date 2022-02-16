/**
 * Date: April 21, 2021
 * Description: A quicksort sorting visualizer
 * @author Matthew
 * 
 */

package sortVis;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

// Driver class
public class QuickSortVis extends SortPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9131469549268669756L;
	
	// Driver code
	public static void main(String[] args) throws InterruptedException
	{
		JFrame f = new JFrame("Quicksort Sorting Visualizer");
		SortPanel p = new QuickSortVis(500);
		
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
	public QuickSortVis(int[] d)
	{
		super(d);
	}
	
	// Method constructor
	public QuickSortVis(int n)
	{
		super(n);
	}
	
	// Quicksort data
	@Override
	public void sort()
	{
		quicksort(0, data.length - 1);
		
		// Unselecting data
		select(-1);
	}
	
	// Quicksort algorithm code sourced from:
	// https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme
	private void quicksort(int l, int h)
	{
	    if (l < h)
	    {
	        int p = partition(l, h);
	        
	        quicksort(l, p - 1);
	        quicksort(p + 1, h);
	    }
	}
	
	// Partition data
	private int partition(int l, int h)
	{
	    int pivot = data[h];
	    int i = l;
	    
	    select(h);
	    
	    for (int j = l; j < h; j++)
	    {
	    	select(j, pivot);
	    	
	        if (data[j] < pivot)
	        {
	        	// Performing data swap
	        	int temp = data[i];
	        	select(i, j, pivot);
	        	
	        	data[i] = data[j];
	            data[j] = temp;
	            select(i, j, pivot);
	        	
	            i++;
	        }
	    }
	    
	    // Performing final data swap
    	int temp = data[i];
    	select(i, h);
    	
    	data[i] = data[h];
        data[h] = temp;
        select(i, h);
	    
	    return i;
	}
}
