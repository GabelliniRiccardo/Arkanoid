
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class TextView {

    private Color color;
    int x;
    int y;
    String text;
    Font font;
    
    public TextView(int x, int y, String text,Font font){
        this.x=x;
        this.y=y;
        this.text=text;
        this.font=font;
                
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    public void setColor(Color color) {
        this.color=color;
    }

    
    public Color getColor() {
        return color;
    }
    
    public void setX(int X) {
        this.x=X;
    }
    
    public void setY(int Y) {
        this.y=Y;
    }
    
    public void setText(String text) {
        this.text=text;
    }
    
    public String getText(){
        return this.text;
    }
    
}
