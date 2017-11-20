package View;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.util.ArrayList;


public class DrawingPanel extends JPanel {
    private ArrayList<AbstractDrawingView> dw = null;
    private ArrayList<TextView> dwString = null;
    private Image backGround=IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\Background.gif"));
    private Image top=IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\Top.gif"));

	public void setDrawing(ArrayList<AbstractDrawingView> dw) {
		this.dw = dw;
	}
        
        public ArrayList<AbstractDrawingView> getDrawing(){
            return dw;
        }

        public void setStrings(ArrayList<TextView> dwString) {
            this.dwString = dwString;
	}
        
        public ArrayList<TextView> getStrings() {
            return dwString;
	}

	@Override
	public void paintComponent(Graphics g) {
           // super.paintComponent(g);
            g.drawImage(backGround, 0, 0, this);
            g.drawImage(top, 0, 0, this);
            for(int i=0;i<dw.size();i++)
                dw.get(i).draw(g);
            for(TextView i:dwString)
                i.draw(g);
            
	}

}
