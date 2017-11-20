
package View;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class View extends JFrame implements IView{
    Timer timer;
    Timer timerForBall;
    static DrawingPanel drawPanel;
    LevelPanel levelPanel;
    ArrayList<AbstractDrawingView> dw;
    ArrayList<TextView> dwString;
    BorderLayout test;
    private static View view = null;
    
    private View(){
        super("Arkanoid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusCycleRoot(false);
        this.setFocusable(true);
        this.requestFocus();
        drawPanel=new DrawingPanel();
        levelPanel = new LevelPanel();
        dw=new ArrayList<>();
        dwString=new ArrayList<TextView>();
        this.setVisible(true);
        this.setResizable(false);//aggiunto
    }
    // serve per definire l'oggetto su cui si disegna nella classe IMAGEVIEW
    protected static DrawingPanel getDrawpanel(){
        return drawPanel;
    }
    
    public static IView getInstance(){
            if (view == null)
                    view = new View();
            return view;
    }
    
    public void addElem(AbstractDrawingView elem){
        dw.add(elem);
    }
    
    public void removeElem(int index){
        dw.remove(index);
    }
    
    public AbstractDrawingView getElem(int index){
        return dw.get(index);
    }
    
    public AbstractDrawingView getLastElemList(){
        return dw.get(dw.size()-1);
    }
    
    public ArrayList<AbstractDrawingView> getList(){
        return dw;
    }

    public void setDrawing(){
        drawPanel.setDrawing(dw);
        drawPanel.setStrings(dwString);
    }
    
    public void addDrawPanel(int panelWidth, int panelHeight){
        drawPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        Container contPane = getContentPane();
        contPane.add(drawPanel);
	pack();
    }

    public void removeDrawPanel(){
        this.remove(drawPanel);
    }

    public void addLevelPanel(int panelWidth, int panelHeight){
        levelPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        Container contPane = getContentPane();
	contPane.setLayout(new BorderLayout());
        contPane.add(levelPanel);
	pack();
    }

    public void removeLevelPanel(){
        this.remove(levelPanel);
    }

    public void setImage(Image imm){
        levelPanel.setImage(imm);
    }

    public void doRepaint(){
        drawPanel.repaint();
    }

    public void doRepaintImage(){
        levelPanel.repaint();
    }
    
    public String getTheNameOfTheUser(String title){
        return JOptionPane.showInputDialog(title);
    }

    public void addTimerAndListener(int MS, ActionListener listener){
        timer=new Timer(MS,listener);
    }

    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
    public void restartTimer(){
        timer.restart();
    }
    
    public void putKeyListener(KeyListener listener){
        this.addKeyListener(listener);
    }
    public boolean isTimerRunning(){
        return timer.isRunning();
    }
    
    public void addString(TextView string){
        dwString.add(string);
    }
    
    public void removeString(int index){
        dwString.remove(index);
    }
    
    public TextView getString(int index){
        return dwString.get(index);
    }
      
    public ArrayList<TextView> getStrings(){
        return dwString;
    }
    
    public TextView getLastString(){
        return dwString.get(dwString.size()-1);
    }
}
