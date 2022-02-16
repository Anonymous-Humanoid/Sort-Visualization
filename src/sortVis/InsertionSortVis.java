/**
 * Date: April 21, 2021
 * Description: An insertion sorting visualizer
 * @author Matthew
 * 
 */

package sortVis;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

// Driver class
public class InsertionSortVis extends SortPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6038215847047985559L;
	
	// Driver code
	public static void main(String[] args) throws InterruptedException
	{
		JFrame f = new JFrame("Insertion Sort Visualizer");
		SortPanel p = new InsertionSortVis(150);
		
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
	public InsertionSortVis(int[] d)
	{
		super(d);
	}
	
	// Method constructor
	public InsertionSortVis(int n)
	{
		super(n);
	}
	
	// Insertion sorting data
	@Override
	public synchronized void sort()
	{
		/* Sorting code modified from: https://www.geeksforgeeks.org/insertion-sort/ */
		for (int i = 1; i < data.length; i++)
		{
			int key = data[i];
			int j;
			
			for (j = i - 1; j >= 0 && data[j] > key; j--)
			{
				data[j + 1] = data[j];
				select(j, j + 1, i);
			}
			
			data[j + 1] = key;
			select(j + 1, i);
		}
		
		// Unselecting data
		select(-1);
	}
}
