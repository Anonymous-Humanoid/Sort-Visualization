/**
 * Date: May 29, 2021
 * Description: A bubble sorting visualizer
 * @author Matthew
 * 
 */

package sortVis;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

// Driver class
public class BubbleSortVis extends SortPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5801277911505137953L;
	
	// Driver code
	public static void main(String[] args) throws InterruptedException
	{
		JFrame f = new JFrame("Bubble Sort Visualizer");
		SortPanel p = new BubbleSortVis(150);
		
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
	public BubbleSortVis(int[] d)
	{
		super(d);
	}
	
	// Method constructor
	public BubbleSortVis(int n)
	{
		super(n);
	}
	
	// Bubble sorting data
	@Override
	public synchronized void sort()
	{
		for (int i = data.length - 1; i > 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				// Selecting data
				select(j, j + 1);
				
				// Swapping out-of-order data
				if (data[j] > data[j + 1])
				{
					int temp = data[j];
					
					data[j] = data[j + 1];
					data[j + 1] = temp;
					
					select(j, j + 1);
				}
			}
		}
		
		// Unselecting data
		select(-1);
	}
}
