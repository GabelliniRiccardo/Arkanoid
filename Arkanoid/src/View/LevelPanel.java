
package View;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


public class LevelPanel extends JPanel{
    Image imm;
    public void setImage(Image imm){
        this.imm=imm;
    }
    
    public Image getImage(){
        return imm;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //if(imm!=null)
        g.drawImage(imm, 0, 0, this);
    }
}
