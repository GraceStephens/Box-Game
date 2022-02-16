import javax.swing.*;
import java.awt.*;

public class StartHerePanel extends JPanel
{

    private Cell myCell;

    public StartHerePanel()
    {
        super();
        myCell = new Cell(1,2,3,"X",true);



    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        myCell.drawSelf(g);
    }


}
