import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Cell
{
	// ----- static variables.... These belong to the class as a whole; all Cells have access to these individual variables.
	public static final int CELL_SIZE = 25;
	private static Font cellFont = new Font("Times New Roman",Font.BOLD,CELL_SIZE*3/4);
	private static Image[] colorImages; // these will be filled with the images in the following files.
	private static String[] filenames = {"BlueChip.png", "GreenChip.png", "PurpleChip.png", "RedChip.png", "YellowChip.png"};
	private static String[] cellColors = {"Blue","Green","Purple","Red","Yellow"};
	private static int[][] mazeOne = {{11, 1}, {11, 9}, {11, 8},{12, 8}, {13, 8}, {14, 8}, {15, 8}, {16, 8}, {17, 8}, {18, 8}, {19, 8}, {20, 8}, {21, 8}, {22, 8}, {22, 7}, {22, 6}, {22, 5}, {22, 4}, {22, 3}, {22, 2}, {22, 0},
	{21, 1},{20, 1}, {19, 1}, {18, 1}, {17, 1}, {17, 2}, {17, 3}, {18, 3}, {19, 3}, {19, 4}, {19, 5}, {19, 6}, {18, 6}, {17, 6}, {16, 6}, {15, 6}, {14, 6}, {12, 6}, {12, 6}, {13, 6}, {12, 6}, {12, 6}, {12, 6}, {12, 6}, {11, 6}, {10, 6},
	{9, 6},{8, 6},{7, 6},{6, 6},{5, 6},{4, 6},{4, 5},{4, 4},{5, 4},{6, 4},{7, 4},{8, 4},{9, 4},{10, 4}, {10, 3}, {10, 2}, {10, 1}, {10, 0}, {11, 2}, {12, 2}, {13, 2}, {13, 1}, {13, 0},
	{9, 2},{8, 2},{7, 2},{6, 2},{5, 2},{4, 2},{3, 2},{2, 2},{1, 2},{1, 1},{1, 0},{1, 3},{1, 4},{1, 5},{1, 6},{1, 7},{1, 8},{2, 8},{3, 8},{4, 8},{4, 9},{4, 10}, {5, 10}, {6, 10}, {6, 9},{6, 8},{7, 8},{8, 8},{8, 9},{8, 10},{8, 11},
	{8, 12},{8, 13},{8, 14},{8, 15},{8, 16},{8, 17},{8, 18},{8, 19},{8, 20},{8, 21},{9, 21},{9, 22},{10, 2},{11, 2},{12, 2},{13, 2},{14, 2},{15, 2},{16, 2},{16, 2},{11, 1},{12, 1},{12, 1},{12, 1},{11, 1},{11, 1},{10, 1},{10, 1},{10, 1},
	{11, 1},{11, 1},{11, 1},{11, 1},{11, 1},{11, 2},{12, 2},{13, 2},{14, 2},{14, 1},{14, 1},{14, 1},{14, 1}, {14, 1}, {14, 1}, {14, 1}, {14, 1}, {14, 1}, {15, 1}, {16, 1}, {17, 1}, {17, 1}, {18, 1}, {19, 1}, {20, 1}, {21, 1}, {22, 1},
	{22, 1}, {22, 1}, {22, 1}, {22, 1}, {22, 1}, {22, 1}, {21, 1}, {20, 1}, {20, 1}, {20, 1}, {20, 1}, {20, 1}, {19, 1}, {18, 1}, {18, 1}, {17, 1}, {16, 1}, {16, 1}, {16, 1}, {16, 1}, {16, 1}, {16, 1}, {16, 1}, {16, 2}, {17, 2},{18, 2},
	{18, 1}, {18, 1}, {19, 1}, {20, 1}, {21, 1}, {22, 1}, {22, 1}, {22, 2}, {22, 2}, {22, 2}, {21, 2}, {20, 2}, {19, 2}, {18, 2}, {18, 2}, {7, 12}, {6, 12}, {5, 12}, {4, 12}, {3, 12}, {2, 12}, {1, 12}, {5, 13},{5, 14}, {5, 15}, {5, 16},
	{4, 16}, {3, 16}, {3, 15}, {3, 14}, {2, 14}, {1, 14}, {1, 15}, {1, 16}, {1, 17}, {1, 18}, {2, 18},{3, 18}, {4, 18}, {5, 18}, {6, 18}, {6, 19}, {6, 20}, {6, 21}, {6, 22}, {5, 22}, {4, 22}, {4, 21}, {4, 20}, {3, 20}, {2, 20}, {1, 20}, {1, 21}, {1, 22},
	{1, 23}};




	private int colorID; // which background color should be displayed?
	private int x,y; // screen coordinates of the top left corner
	private String marker; // optional character (typically a letter or number) to show on this cell
	private boolean displayMarker; // whether to show the cell label or not.
	private boolean isLive; // whether the cell should appear at all.
	
	//=====================  CONSTRUCTORS =============================
	public Cell()
	{
		colorID = 1;
		isLive = true;
		// The following is a sneaky trick I am using to initialize a static variable - the first constructor that 
		// gets to it when it hasn't yet been defined will be the one to load up these variables. All of the cell
		// instances will share the same five pictures! This way, we can have hundreds of cells, but they don't all
		// have to load the images.
		marker = "";
		displayMarker = false;
		if (colorImages == null)
		{
			colorImages = new Image[filenames.length];
			for (int i =0; i<filenames.length; i++)
				colorImages[i] = (new ImageIcon(filenames[i])).getImage();
		}
	}
	
	public Cell(int cid)
	{
		this();
		colorID = cid;
	}
	
	public Cell(int inRow, int inCol)
	{
		this((int)(Math.random()*filenames.length));
		y = inRow*CELL_SIZE;
		x = inCol*CELL_SIZE;
	}
	
	public Cell(int cid, int inRow, int inCol, String inMarker, boolean disp)
	{
		this(inRow,inCol);
		colorID = cid;
		marker = inMarker;
		displayMarker = disp;
	}
	//=====================  ACCESSORS/MODIFIERS =============================
	public int getColorID()
	{
		return colorID;
	}

	public void setColorID(int colorID)
	{
		this.colorID = colorID;
	}
	/**
	 * cycles the color forward one notch.
	 */
	public void cycleColorIDForward()
	{
		colorID = (colorID + 1) % filenames.length;
	}

	/**
	 * cycles the color backward one notch.
	 */
	public void cycleColorIDBackward()
	{
		colorID = (colorID+ (filenames.length-1)) %filenames.length;
	}
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	public String getMarker()
	{
		return marker;
	}

	public void setMarker(String marker)
	{
		this.marker = marker;
	}

	public boolean shouldDisplayMarker()
	{
		return displayMarker;
	}

	public void setDisplayMarker(boolean displayMarker)
	{
		this.displayMarker = displayMarker;
	}

	public boolean isLive()
	{
		return isLive;
	}
	
	public void setIsLive(boolean b)
	{
		isLive = b;
	}
	// =============================   DRAW SELF ================================
	public void drawSelf(Graphics g)
	{
		if (!isLive)
			return;
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(colorImages[colorID], x,y, CELL_SIZE-2, CELL_SIZE-2, null);
		   
		g2.setColor(new Color(192,192,192));
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+1, y+1, CELL_SIZE-4, CELL_SIZE-4, 8, 8);
		
		g2.setColor(new Color(64,64,64));
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(x+1, y+1, CELL_SIZE-4, CELL_SIZE-4, 8, 8);
		   
		if (displayMarker)
		{
			g2.setFont(cellFont);
			g2.setColor(Color.WHITE);
			g2.drawString(marker, x+CELL_SIZE/2-6, y+CELL_SIZE/2+7);  //You'll likely want to tinker with these numbers.
			   
			g2.setColor(Color.BLACK);
			g2.drawString(marker, x+CELL_SIZE/2-7, y+CELL_SIZE/2+6);
		}
	
	}
// ===================================  OVERRIDDEN OBJECT METHODS ==============================
	public boolean equals(Object other)
	{
		if (other instanceof Cell)
			if ((((Cell) other).colorID == this.colorID) && 
			   (((Cell) other).marker   == this.marker))
			return true;
		return false;
	}

	public String toString()
	{
		return "Cell: "+marker+": color:"+cellColors[colorID];
	}
	
	// a good habit for us to get into, but we probably won't need this for the current project.
	public int hashCode()
	{
		int result = colorID * 137;
		if (marker!=null) result += marker.hashCode();
		return result;
	}

}
