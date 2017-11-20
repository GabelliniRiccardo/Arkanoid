
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageView extends AbstractDrawingView {
    
    private Image image;
    private Color color;
    double x;
    double y;
    
    public ImageView(Image image, int x, int y){
        this.image=image;
        this.x=x;
        this.y=y;
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
	g2d.drawImage(image, (int)x, (int)y, View.getDrawpanel());
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
        this.x=X; 
    }

    @Override
    public void setY(double Y) {
        this.y=Y;
    }

    @Override
    public void setDrawingWidth(int width) {
        this.image=IO.Utilities.resizeImage((BufferedImage)image, BufferedImage.TYPE_BYTE_INDEXED, width, this.image.getHeight(View.getDrawpanel()));
    }

    /*@Override
    public void setDrawingHeight(int height) {
       this.image=IO.Utilities.resizeImage((BufferedImage)image, BufferedImage.TYPE_BYTE_INDEXED, this.image.getWidth(View.getDrawpanel()), height); SERVE?
    }*/

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getX() {
        return x;
    }
}
