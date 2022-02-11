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
	private static int[][] mazeTwo = {{14, 14}, {15, 14}, {16, 14},	{17, 14},
			{17, 15},
			{17, 16},
			{18, 16},
			{19, 16},
			{19, 17},
			{20, 18},
			{19, 18},
			{21, 18},
			{22, 18},
			{22, 16},
			{22, 17},
			{22, 15},
			{22, 14},
			{22, 13},
			{22, 12},
			{22, 11},
			{22, 10},
			{22, 9},
			{22, 7},
			{22, 8},
			{21, 7},
			{20, 7},
			{20, 6},
			{18, 0},
			{16, 0},
			{19, 6},
			{19, 5},
			{19, 5},
			{18, 5},
			{18, 4},
			{17, 3},
			{17, 2},
			{18, 2},
			{18, 1},
			{16, 1},
			{15, 1},
			{15, 2},
			{15, 3},
			{15, 4},
			{15, 5},
			{14, 5},
			{13, 5},
			{12, 5},
			{11, 5},
			{10, 5},
			{9, 5},
			{9, 3},
			{9, 4},
			{12, 6},
			{12, 7},
			{12, 8},
			{12, 9},
			{13, 9},
			{14, 9},
			{15, 9},
			{16, 9},
			{17, 9},
			{18, 9},
			{18, 10},
			{18, 11},
			{19, 11},
			{19, 12},
			{19, 13},
			{19, 14},
			{20, 14},
			{20, 15},
			{8, 2},
			{8, 3},
			{7, 2},
			{6, 2},
			{6, 1},
			{5, 1},
			{5, 0},
			{10, 9},
			{11, 9},
			{9, 9},
			{8, 9},
			{7, 9},
			{7, 8},
			{6, 8},
			{6, 7},
			{5, 7},
			{5, 6},
			{4, 6},
			{3, 6},
			{2, 5},
			{2, 6},
			{1, 5},
			{1, 4},
			{0, 4},
			{7, 10},
			{7, 11},
			{6, 11},
			{6, 12},
			{6, 13},
			{6, 14},
			{6, 15},
			{6, 17},
			{6, 16},
			{5, 17},
			{4, 17},
			{4, 18},
			{8, 12},
			{9, 12},
			{9, 13},
			{10, 13},
			{10, 14},
			{11, 14},
			{11, 15},
			{12, 15},
			{13, 15},
			{11, 12},
			{10, 12},
			{12, 12},
			{13, 12},
			{14, 12},
			{15, 12},
			{15, 13},
			{5, 19},
			{4, 19},
			{5, 20},
			{6, 20},
			{6, 21},
			{7, 21},
			{8, 21},
			{9, 21},
			{10, 21},
			{11, 21},
			{12, 21},
			{12, 20},
			{12, 19},
			{11, 19},
			{10, 19},
			{10, 18},
			{9, 18},
			{9, 17},
			{9, 16},
			{9, 15},
			{9, 14},
			{14, 16},
			{15, 17},
			{14, 17},
			{13, 16},
			{15, 18},
			{15, 19},
			{15, 20},
			{16, 20},
			{16, 20},
			{16, 21},
			{16, 22},
			{16, 23},
			{3, 17},
			{2, 17},
			{1, 17},
			{0, 17},
			{22, 19},
			{22, 20},
			{23, 20}};;



	
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
