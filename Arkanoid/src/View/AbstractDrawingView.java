package View;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractDrawingView {
    
    public abstract void draw(Graphics g);
    
    public abstract void setColor(Color color);
    
    public abstract Color getColor();
    
    public abstract void setX(double X);
        
    public abstract void setY(double Y);
    
    public abstract double getY();
    
    public abstract double getX();
    
    public abstract void setDrawingWidth(int width);
    
}
