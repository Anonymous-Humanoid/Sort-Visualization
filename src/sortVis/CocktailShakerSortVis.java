/**
 * Date: May 29, 2021
 * Description: A cocktail shaker sorting visualizer
 * @author Matthew
 * 
 */

package sortVis;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

// Driver class
public class CocktailShakerSortVis extends SortPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3371704123754388209L;
	
	// Driver code
	public static void main(String[] args) throws InterruptedException
	{
		JFrame f = new JFrame("Cocktail Shaker Sort Visualizer");
		SortPanel p = new CocktailShakerSortVis(150);
		
		// Setting up frame
		f.add(p);
		f.pack();
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.validate();
		f.setVisible(true);
		
		p.paintComponent(p.getGraphics());
		f.repaint();
		
		// Waiting 1 second
		TimeUnit.SECONDS.sleep(1);
		
		p.sort();
	}
	
	// Method constructor
	public CocktailShakerSortVis(int[] d)
	{
		super(d);
	}
	
	// Method constructor
	public CocktailShakerSortVis(int n)
	{
		super(n);
	}
	
	// Cocktail shaker sorting data
	@Override
	public synchronized void sort()
	{
		boolean sorted;
		int i = 0;
		
		do
		{
			// Resetting check
			sorted = false;
			
			// Performing forwards (maximum) sort
			for (int j = i; j < data.length - 1 - i; j++)
			{
				// Selecting data
				select(j, j + 1);
				
				// Swapping out-of-order data
				if (data[j] > data[j + 1])
				{
					int temp = data[j];
					
					sorted = true;
					data[j] = data[j + 1];
					data[j + 1] = temp;
					
					select(j, j + 1);
				}
			}
			
			// Performing backwards (minimum) sort
			for (int j = data.length - 1 - i; j > i; j--)
			{
				// Selecting data
				select(j, j - 1);
				
				// Swapping out-of-order data
				if (data[j - 1] > data[j])
				{
					int temp = data[j];
					
					sorted = true;
					data[j] = data[j - 1];
					data[j - 1] = temp;
					
					select(j, j - 1);
				}
			}
			
			i++;
		}
		while (sorted);
		
		// Unselecting data
		select(-1);
	}
}
