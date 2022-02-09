import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GridDemoPanel extends JPanel implements MouseListener, KeyListener
{
	private Cell[][] theGrid;
	public final static int NUM_ROWS = 24;
	public final static int NUM_COLS = 24;
	public GridDemoFrame myParent;
	public int score;
	
	public GridDemoPanel(GridDemoFrame parent)
	{
		super();
		resetCells();
		//theGrid[2][2].setMarker("A");
		//theGrid[2][2].setDisplayMarker(true);
		//theGrid[3][3].setIsLive(false);
		setBackground(Color.WHITE);
		addMouseListener(this);
		//parent.addKeyListener(this); // activate this if you wish to listen to the keyboard. 
		myParent = parent;
	}	
	
	/**
	 * makes a new board with random colors, completely filled in, and resets the score to zero.
	 */
	public void resetCells()
	{
		theGrid = new Cell[NUM_ROWS][NUM_COLS];
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c] = new Cell(r,c);
		score = 0;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c].drawSelf(g);
	}
	
	/**
	 * the mouse listener has detected a click, and it has happened on the cell in theGrid at row, col
	 * @param row
	 * @param col
	 */
	public void userClickedCell(int row, int col)
	{
		
		System.out.println("("+row+", "+col+")");
		if (!theGrid[row][col].isLive())
			return;
		score += theGrid[row][col].getColorID();
		myParent.updateScore(score);
		
		theGrid[row][col].cycleColorIDForward();
		repaint();
		
	}
	
	
	
	
	/**
	 * Here's an example of a simple dialog box with a message.
	 */
	public void makeGameOverDialog()
	{
		JOptionPane.showMessageDialog(this, "Game Over.");
		
	}
	
	//============================ Mouse Listener Overrides ==========================

	@Override
	// mouse was just released within about 1 pixel of where it was pressed.
	// NOTE: this is actually kind of obnoxious because if the mouse moved much at all between press
	// and release, it won't register as a click. You may be happier with mouseReleased, instead.
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
		int col = e.getX()/Cell.CELL_SIZE;
		int row = e.getY()/Cell.CELL_SIZE;
		userClickedCell(row,col);
		
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
				
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
		
	}

	@Override
	// mouse just entered this window
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	// mouse just left this window
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	//============================ Key Listener Overrides ==========================

	@Override
	/**
	 * user just pressed and released a key. (May also be triggered by autorepeat, if key is held down?
	 * @param e
	 */
	public void keyTyped(KeyEvent e)
	{
		char whichKey = e.getKeyChar();
		myParent.updateMessage("User just typed \""+whichKey+"\"" );
		System.out.println(whichKey);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	// ============================= animation stuff ======================================
	/**
	 * if you wish to have animation, you need to call this method from the GridDemoFrame AFTER you set the window visibility.
	 */
	public void initiateAnimationLoop()
	{
		Thread aniThread = new Thread( new AnimationThread(500)); // the number here is the number of milliseconds between steps.
		aniThread.start();
	}
	
	/**
	 * Modify this method to do what you want to have happen periodically.
	 * This method will be called on a regular basis, determined by the delay set in the thread.
	 * Note: By default, this will NOT get called unless you uncomment the code in the GridDemoFrame's constructor
	 * that creates a thread.
	 *
	 */
	public void animationStep(long millisecondsSinceLastStep)
	{
		theGrid[0][0].cycleColorIDBackward();
		repaint();
	}
	// ------------------------------- animation thread - internal class -------------------
	public class AnimationThread implements Runnable
	{
		long start;
		long timestep;
		public AnimationThread(long t)
		{
			timestep = t;
			start = System.currentTimeMillis();
		}
		@Override
		public void run()
		{
			long difference;
			while (true)
			{
				difference = System.currentTimeMillis() - start;
				if (difference >= timestep)
				{
					animationStep(difference);
					start = System.currentTimeMillis();
				}
				try
				{	Thread.sleep(1);
				}
				catch (InterruptedException iExp)
				{
					System.out.println(iExp.getMessage());
					break;
				}
			}
			
		}
		
	}
}


/* Maze 1 tiles:
Walls:

Background/ tunnel:
(11, 10)
(11, 9)
(11, 8)
(12, 8)
(13, 8)
(14, 8)
(15, 8)
(16, 8)
(17, 8)
(18, 8)
(19, 8)
(20, 8)
(21, 8)
(22, 8)
(22, 7)
(22, 6)
(22, 5)
(22, 4)
(22, 3)
(22, 2)
(22, 1)
(22, 0)
(21, 1)
(20, 1)
(19, 1)
(18, 1)
(17, 1)
(17, 2)
(17, 3)
(18, 3)
(19, 3)
(19, 4)
(19, 5)
(19, 6)
(18, 6)
(17, 6)
(16, 6)
(15, 6)
(14, 6)
(12, 6)
(12, 6)
(13, 6)
(12, 6)
(12, 6)
(12, 6)
(12, 6)
(11, 6)
(10, 6)
(9, 6)
(8, 6)
(7, 6)
(6, 6)
(5, 6)
(4, 6)
(4, 5)
(4, 4)
(5, 4)
(6, 4)
(7, 4)
(8, 4)
(9, 4)
(10, 4)
(10, 3)
(10, 2)
(10, 1)
(10, 0)
(11, 2)
(12, 2)
(13, 2)
(13, 1)
(13, 0)
(9, 2)
(8, 2)
(7, 2)
(6, 2)
(5, 2)
(4, 2)
(3, 2)
(2, 2)
(1, 2)
(1, 1)
(1, 0)
(1, 3)
(1, 4)
(1, 5)
(1, 6)
(1, 7)
(1, 8)
(2, 8)
(3, 8)
(4, 8)
(4, 9)
(4, 10)
(5, 10)
(6, 10)
(6, 9)
(6, 8)
(7, 8)
(8, 8)
(8, 9)
(8, 10)
(8, 11)
(8, 12)
(8, 13)
(8, 14)
(8, 15)
(8, 16)
(8, 17)
(8, 18)
(8, 19)
(8, 20)
(8, 21)
(9, 21)
(9, 22)
(10, 22)
(11, 22)
(12, 22)
(13, 22)
(14, 22)
(15, 22)
(16, 22)
(16, 23)
(11, 12)
(12, 12)
(12, 13)
(12, 14)
(11, 14)
(11, 13)
(10, 13)
(10, 12)
(10, 14)
(11, 15)
(11, 16)
(11, 17)
(11, 18)
(11, 19)
(11, 20)
(12, 20)
(13, 20)
(14, 20)
(14, 19)
(14, 18)
(14, 17)
(14, 16)
(14, 14)
(14, 15)
(14, 13)
(14, 12)
(14, 11)
(15, 11)
(16, 11)
(17, 11)
(17, 10)
(18, 10)
(19, 10)
(20, 10)
(21, 10)
(22, 10)
(22, 11)
(22, 12)
(22, 13)
(22, 14)
(22, 15)
(22, 16)
(21, 16)
(20, 16)
(20, 15)
(20, 14)
(20, 13)
(20, 12)
(19, 14)
(18, 14)
(18, 13)
(17, 13)
(16, 13)
(16, 14)
(16, 15)
(16, 16)
(16, 17)
(16, 18)
(16, 19)
(16, 20)
(17, 20)
(18, 20)
(18, 19)
(18, 18)
(19, 18)
(20, 18)
(21, 18)
(22, 18)
(22, 19)
(22, 20)
(22, 21)
(22, 22)
(21, 22)
(20, 22)
(19, 22)
(18, 22)
(18, 23)
(7, 12)
(6, 12)
(5, 12)
(4, 12)
(3, 12)
(2, 12)
(1, 12)
(5, 13)
(5, 14)
(5, 15)
(5, 16)
(4, 16)
(3, 16)
(3, 15)
(3, 14)
(2, 14)
(1, 14)
(1, 15)
(1, 16)
(1, 17)
(1, 18)
(2, 18)
(3, 18)
(4, 18)
(5, 18)
(6, 18)
(6, 19)
(6, 20)
(6, 21)
(6, 22)
(5, 22)
(4, 22)
(4, 21)
(4, 20)
(3, 20)
(2, 20)
(1, 20)
(1, 21)
(1, 22)
(1, 23)



 */
