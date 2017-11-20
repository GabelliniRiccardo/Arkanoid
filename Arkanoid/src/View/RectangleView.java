package View;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class RectangleView extends AbstractDrawingView{
    
    private RoundRectangle2D.Double rect;
    private Color color;
    GradientPaint whiteToColor;

    
    
    public RectangleView(double x, double y, int width, int height, double arcwidth, double archeight){
        rect =new RoundRectangle2D.Double(x,y,width,height,arcwidth,archeight);

        
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(whiteToColor);
	g2d.fill(rect);
        g2d.setColor(Color.WHITE);
	g2d.draw(rect);
        
    
    }

    @Override
    public void setColor(Color color) {
        this.color=color;
        whiteToColor=new GradientPaint((float)rect.getX(),(float)rect.getY(),color.WHITE,(float)(rect.getX()+rect.getWidth()),(float)(rect.getY()+rect.getHeight()),color);

    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setX(double X) {
        rect.setRoundRect(X,rect.getY(),rect.getWidth(),rect.getHeight(),rect.getArcWidth(),rect.getArcHeight());
        
    }

    @Override
    public void setY(double Y) {
        rect.setRoundRect(rect.getX(),Y,rect.getWidth(),rect.getHeight(),rect.getArcWidth(),rect.getArcHeight());
    }

    @Override
    public void setDrawingWidth(int width) {
        rect.setRoundRect(rect.getX(),rect.getY(),width,rect.getHeight(),rect.getArcWidth(),rect.getArcHeight());
    }

    /*@Override
    public void setDrawingHeight(int height) {
        rect.setRoundRect(rect.getX(),rect.getY(),rect.getWidth(),height,rect.getArcWidth(),rect.getArcHeight()); SERVE?
    }*/

    @Override
    public double getY() {
        return rect.getY();
    }

    @Override
    public double getX() {
        return rect.getX();
    }
    
    
}
