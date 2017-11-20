
package View;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public interface IView {
    
    public void addElem(AbstractDrawingView elem);
    
    public void removeElem(int index);
    
    public AbstractDrawingView getElem(int index);
    
    public AbstractDrawingView getLastElemList();
    
    public ArrayList<AbstractDrawingView> getList();

    public void setDrawing();
    
    public void addDrawPanel(int panelWidth, int panelHeight);

    public void removeDrawPanel();

    public void addLevelPanel(int panelWidth, int panelHeight);

    public void removeLevelPanel();

    public void setImage(Image imm);

    public void doRepaint();

    public void doRepaintImage();
    
    public String getTheNameOfTheUser(String title);

    public void addTimerAndListener(int MS, ActionListener listener);

    public void startTimer();

    public void stopTimer();
    
    public void restartTimer();
    
    public void putKeyListener(KeyListener listener);
    
    public boolean isTimerRunning();
    
    public void addString(TextView string);
    
    public void removeString(int index);
    
    public TextView getString(int index);
      
    public ArrayList<TextView> getStrings();
    
    public TextView getLastString();
    
    public void repaint();

}
