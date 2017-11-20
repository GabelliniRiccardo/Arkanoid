package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class CircleView extends AbstractDrawingView{
    private Ellipse2D.Double circle;
    private Color color;
    
    public CircleView(double x, double y, int width, int height){
        circle=new Ellipse2D.Double(x,y,width,height);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
	g2d.fill(circle);
	g2d.draw(circle);
    }

    @Override
    public void setColor(Color color) {
        this.color=color;
    }

    @Override
    public Color getColor() {
        return color;
    }
    
    @Override
    public void setX(double X) {
        circle.setFrame(X,circle.getY(),circle.getWidth(),circle.getHeight());
        
    }

    @Override
    public void setY(double Y) {
        circle.setFrame(circle.getX(),Y,circle.getWidth(),circle.getHeight());
    }

    @Override
    public void setDrawingWidth(int width) {
        circle.setFrame(circle.getX(),circle.getY(),width,circle.getHeight());
    }

    /*@Override
    public void setDrawingHeight(int height) {
        circle.setFrame(circle.getX(),circle.getY(),circle.getWidth(),height); SERVE?
    }*/
    
    @Override
    public double getY() {
        return circle.getY();
    }

    @Override
    public double getX() {
        return circle.getX();
    }
}
