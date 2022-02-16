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

	private static int[][] mazeOne = {{11, 9}, {11, 8},{12, 8}, {13, 8}, {14, 8}, {15, 8},
			{16, 8}, {17, 8}, {18, 8}, {19, 8}, {20, 8}, {21, 8}, {22, 8}, {22, 7}, {22, 6},
			{22, 5}, {22, 4}, {22, 3}, {22, 2}, {22, 0}, {17, 2},{19, 3},
			{19, 4}, {19, 5}, {19, 6}, {18, 6}, {17, 6}, {16, 6}, {15, 6}, {14, 6}, {12, 6},
			{12, 6}, {13, 6}, {12, 6}, {12, 6}, {12, 6}, {12, 6}, {11, 6}, {10, 6},
			{9, 6},{8, 6},{7, 6},{6, 6},{5, 6},{4, 6},{4, 5},{4, 4},{5, 4},{6, 4},{7, 4},
			{8, 4},{9, 4},{10, 4}, {10, 3}, {10, 2}, {10, 1}, {10, 0}, {11, 2}, {12, 2},
			{13, 2}, {13, 0}, {9, 2},{8, 2},{7, 2},{6, 2},{5, 2},{4, 2},{3, 2},{2, 2},
			{1, 2},{1, 1},{1, 0},{1, 3},{1, 4},{1, 5},{1, 6},{1, 7},{1, 8},{2, 8},{3, 8},
			{4, 8},{4, 9},{4, 10}, {5, 10}, {6, 10}, {6, 9},{6, 8},{7, 8},{8, 8},{8, 9},
			{8, 10},{8, 11}, {8, 12},{8, 13},{8, 14},{8, 15},{8, 16},{8, 17},{8, 18},{8, 19},
			{8, 20},{8, 21},{9, 21},{9, 22},{10, 2},{11, 2},{12, 2},{13, 2},{14, 2},{15, 2},
			{16, 2},{16, 2},{10, 1},{10, 1},{10, 1}, {11, 2},{12, 2},{13, 2},{14, 2},{22, 1},
			{22, 1}, {22, 1}, {22, 1}, {22, 1}, {22, 1}, {22, 1},{16, 2}, {17, 2},{18, 2},
			{22, 1}, {22, 2}, {22, 2}, {22, 2}, {21, 2}, {20, 2}, {19, 2}, {18, 2}, {18, 2},
			{7, 12}, {6, 12}, {5, 12}, {4, 12}, {3, 12}, {2, 12}, {1, 12}, {5, 13},{5, 14},
			{5, 15}, {5, 16}, {4, 16}, {3, 16}, {3, 15}, {3, 14}, {2, 14}, {1, 14}, {1, 15},
			{1, 16}, {1, 17}, {1, 18}, {2, 18},{3, 18}, {4, 18}, {5, 18}, {6, 18}, {6, 19},
			{6, 20}, {6, 21}, {6, 22}, {5, 22}, {4, 22}, {4, 21}, {4, 20}, {3, 20}, {2, 20},
			{1, 20}, {1, 21}, {1, 22}, {1, 23}, {13,1}, {11, 12}, {12, 12}, {13, 12},{11, 13},
			{12, 13}, {11, 14}, {12, 14}, {13, 14}, {11, 10}, {12, 15}, {12, 16}, {12, 17},
			{12, 18}, {12, 19}, {12, 20}, {12, 21}, {12, 22}, {13, 22}, {14, 22}, {15, 22},
			{16, 22}, {16, 21}, {16, 20}, {15, 20}, {14, 20}, {14, 19}, {14, 18}, {15, 18},
			{16, 18}, {16, 17}, {16, 16}, {16, 15}, {16, 14}, {16, 13}, {16, 12}, {16, 11},
			{16, 10}, {15, 10}, {14, 10}, {13, 10}, {17, 12}, {18, 12}, {19, 12}, {20, 12},
			{21, 12}, {21, 13}, {21, 14}, {22, 14}, {22, 15}, {22, 16}, {21, 16}, {20, 16},
			{20, 17}, {20, 18}, {21, 18}, {22, 18}, {22, 19}, {22, 20}, {21, 20}, {20, 20},
			{19, 20}, {18, 20}, {18, 19}, {18, 18}, {18, 17}, {18, 16}, {18, 15}, {18, 14},
			{18, 21}, {18, 22}, {18, 23},

			{13, 13} };
	private static int[][] mazeTwo = {{14, 14}, {15, 14}, {16, 14},	{17, 14}, {17, 15},
			{17, 16}, {18, 16}, {19, 16}, {19, 17}, {20, 18}, {19, 18}, {21, 18}, {22, 18},
			{22, 16}, {22, 17}, {22, 15}, {22, 14}, {22, 13}, {22, 12}, {22, 11}, {22, 10},
			{22, 9}, {22, 7}, {22, 8}, {21, 7}, {20, 7}, {20, 6}, {18, 0}, {16, 0}, {19, 6},
			{19, 5}, {19, 5}, {18, 5}, {18, 4}, {17, 3}, {17, 2}, {18, 2}, {18, 1}, {16, 1},
			{15, 1}, {15, 2}, {15, 3}, {15, 4}, {15, 5}, {14, 5}, {13, 5}, {12, 5}, {11, 5},
			{10, 5}, {9, 5}, {9, 3}, {9, 4}, {12, 6}, {12, 7}, {12, 8}, {12, 9}, {13, 9},
			{14, 9}, {15, 9}, {16, 9}, {17, 9}, {18, 9}, {18, 10}, {18, 11}, {19, 11}, {19, 12},
			{19, 13}, {19, 14}, {20, 14}, {20, 15}, {8, 2}, {8, 3}, {7, 2}, {6, 2}, {6, 1}, {5, 1},
			{5, 0}, {10, 9}, {11, 9}, {9, 9}, {8, 9}, {7, 9}, {7, 8}, {6, 8}, {6, 7}, {5, 7},
			{5, 6}, {4, 6}, {3, 6}, {2, 5}, {2, 6}, {1, 5}, {1, 4}, {0, 4}, {7, 10}, {7, 11},
			{6, 11}, {6, 12}, {6, 13}, {6, 14}, {6, 15}, {6, 17}, {6, 16}, {5, 17}, {4, 17},
			{4, 18},{8, 12}, {9, 12}, {9, 13}, {10, 13}, {10, 14}, {11, 14}, {11, 15}, {12, 15},
			{13, 15}, {11, 12}, {10, 12}, {12, 12}, {13, 12}, {14, 12}, {15, 12}, {15, 13},
			{5, 19}, {4, 19}, {5, 20}, {6, 20}, {6, 21}, {7, 21}, {8, 21}, {9, 21}, {10, 21},
			{11, 21}, {12, 21}, {12, 20}, {12, 19}, {11, 19}, {10, 19}, {10, 18}, {9, 18},
			{9, 17}, {9, 16}, {9, 15}, {9, 14}, {14, 16}, {15, 17}, {14, 17}, {13, 16}, {15, 18},
			{15, 19}, {15, 20}, {16, 20}, {16, 20}, {16, 21}, {16, 22}, {16, 23}, {3, 17}, {2, 17},
			{1, 17}, {0, 17}, {22, 19}, {22, 20}, {23, 20}, {13, 1}, {8, 1}, {5, 3}, {4, 4}, {3, 4},
			{3, 5}, {22, 6}, {22, 5}, {22, 4}, {22, 3}, {22, 1}, {20, 2}, {20, 2}, {20, 3}, {20, 3},
			{21, 3}, {19, 2}, {20, 2}, {20, 2}, {1, 20}, {1, 21}, {4, 22}, {7, 17}, {7, 17}, {8, 17},
			{13, 2}, {14, 2}, {15, 2}, {3, 22}, {2, 22}, {2, 22}, {1, 22}, {1, 19}, {1, 18}, {5, 22},
			{6, 22}, {12, 22}, {13, 22}, {14, 22}, {13, 22}, {15, 22}, {18, 21}, {21, 20}, {20, 20},
			{19, 8}, {9, 1}, {10, 1}, {10, 2}, {11, 2}, {11, 1}, {12, 1}, {4, 1}, {3, 1}, {2, 1},
			{1, 2}, {1, 2}, {1, 3}, {20, 1}, {21, 1}, {22, 2}, {19, 20}, {19, 20}, {19, 21}, {18, 20},
			{17, 21}, {4, 11}, {5, 11}, {3, 11}, {2, 11}, {1, 11}, {1, 12}, {1, 13}, {1, 13}, {1, 14},
			{1, 14}, {2, 14}, {3, 13}, {2, 13}, {3, 14}, {2, 11}, {2, 11}, {4, 11}, {1, 6}, {1, 7},
			{1, 7}, {1, 8}, {1, 9}, {1, 10}, {1, 1}, {21, 1}, {20, 1}, {22, 2}, {15, 6}, {16, 7},
			{16, 6}, {15, 6}, {16, 6}, {16, 7}, {16, 7}, {2, 12}, {3, 12}, {8, 5}, {7, 5}, {6, 5},
			{6, 6}, {3, 15}, {3, 15}, {3, 16}, {17, 7}, {18, 7}};


	//private static int [][] action_squares = {{7, 6}, {9, 4}, {7, 2}, {21, 1}};		// Action squares
	//private static int [][] reaction_squares = {{11, 1},{8, 16},{20, 1},{16, 8}};	// Reaction Squares


	public int player_x = 10;
	public int player_y = 11;


	
	public GridDemoPanel(GridDemoFrame parent)
	{
		super();
		resetCells();

		for (int ix = 0; ix < 24; ix++)
		{
			for (int iy = 0; iy <24; iy++)
			{
				theGrid[ix][iy].setMarker("#");
				theGrid[ix][iy].setDisplayMarker(false);
			}
		}

		//theGrid[2][2].setMarker("A");
		//theGrid[2][2].setDisplayMarker(true);
		//theGrid[3][3].setIsLive(false);
		setBackground(Color.BLACK);
		addMouseListener(this);
		parent.addKeyListener(this); // activate this if you wish to listen to the keyboard.
		myParent = parent;

		theGrid[player_y][player_x].setMarker("#");
		theGrid[player_y][player_x].setDisplayMarker(true);

//		if (player_y == theGrid[13][8].getY())
//		{
//			if (player_x == theGrid[13][8].getX())
//			{
//				theGrid[12][8].setColorID(2);
//			}
//		}


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
	//
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);


		for (int r = 0; r<NUM_ROWS; r++)		// sets everything to black
			for (int c = 0; c<NUM_COLS; c++)
			{
				theGrid[r][c].setColorID(2);
				theGrid[r][c].drawSelf(g);
			}

		for (int r = 0; r< mazeOne.length; r++){// sets path to white
				theGrid[mazeOne[r][0]][mazeOne[r][1]].setColorID(1);
				theGrid[mazeOne[r][0]][mazeOne[r][1]].drawSelf(g);

		/*
			for (int r = 0; r< mazeTwo.length; r++){// sets path to white
				theGrid[mazeTwo[r][0]][mazeTwo[r][1]].setColorID(1);
				theGrid[mazeTwo[r][0]][mazeTwo[r][1]].drawSelf(g);
//
*/

			}

//		for (int r = 1; r<NUM_ROWS; r=r+4)		// sets every 4 rows to be purple
//			for (int c = 1; c<NUM_COLS; c++)
//			{
//				theGrid[r][c].setColorID(2);
//				theGrid[r][c].drawSelf(g);
//			}
//		for (int r = 1; r<NUM_ROWS; r++)		// sets every 4 columns to be purple
//			for (int c = 1; c<NUM_COLS; c=c+4)
//			{
//				theGrid[r][c].setColorID(2);
//				theGrid[r][c].drawSelf(g);
//			}



//		for (int r =0; r<NUM_ROWS; r++)
//			for (int c=0; c<NUM_COLS; c++)
//				theGrid[r][c].drawSelf(g);
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

	public void playerLeaves(int row, int col)			// deletes marker when user wants player to leave cell
	{
		theGrid[row][col].setDisplayMarker(false);

	}
	public void playerArrives(int row, int col)			// makes marker when user wants to enter cell
	{
		theGrid[row][col].setDisplayMarker(true);

//		for (int r = 0; r<action_squares.length; r++)
//		{
//			if (row == action_squares[r][0])
//			{
//				if (col == action_squares[r][1])
//				{
//					theGrid[reaction_squares[r][0]][reaction_squares[r][1]].setColorID(0);
//					//theGrid[reaction_squares[r][0]][reaction_squares[r][1]].drawSelf(getGraphics());
//
//				}
//			}
//		}


//		if (theGrid[row][col].getX() == 13)
//		{
//			if (theGrid[row][col].getY() == 8)
//			{
//				theGrid[12][8].setColorID(2);
//			}
//
//		}
//		System.out.println(row + " , " + col);
	}

	public void userPressedKey(KeyEvent e)			// Player movement
	{
		char w = 'w';
		char a = 'a';
		char s = 's';
		char d = 'd';

			if (e.getKeyChar() == w)
			{
				if (theGrid[player_y-1][player_x].getColorID() == 1)
				{
					playerLeaves(player_y, player_x);
					playerArrives(player_y - 1, player_x);
					player_y -= 1;
				}
			}

			if (e.getKeyChar() == a)
			{
				if (theGrid[player_y][player_x-1].getColorID() == 1)
				{
					playerLeaves(player_y, player_x);
					playerArrives(player_y, player_x - 1);
					player_x -= 1;
				}
			}

			if (e.getKeyChar() == s)
			{
				if (theGrid[player_y+1][player_x].getColorID() == 1)
				{
					playerLeaves(player_y, player_x);
					playerArrives(player_y + 1, player_x);
					player_y += 1;
				}
			}
			if (e.getKeyChar() == d)
			{
				if (theGrid[player_y][player_x+1].getColorID() == 1)
				{
					playerLeaves(player_y, player_x);
					playerArrives(player_y, player_x + 1);
					player_x += 1;
				}
			}


//		for (int r = 0; r<action_squares.length; r++)
//		{
//			if (player_y == action_squares[r][0])
//			{
//				if (player_x == action_squares[r][1])
//				{
//					theGrid[reaction_squares[r][0]][reaction_squares[r][1]].setColorID(2);
//				}
//			}
//		}




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
		userPressedKey(e);
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
