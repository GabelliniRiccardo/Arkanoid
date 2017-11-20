

package Model;

public class Block extends Stick{
    int lifes;
    int bonus;
    
    public Block(double x, double y, int width, int eight){
        super(x,y,width,eight);
    }
    
    @Override
    public String getID() {
        return "Block"+lifes;
    }

    @Override
    public int getLifes() {
        return lifes;
    }

    @Override
    public void setLifes(int lifes) {
        this.lifes=lifes;
    }
    
    @Override
    public void setBonusID(int bonus){
        this.bonus=bonus;
    }
    
    @Override
    public int getBonusID(){
        return bonus;
    }
    
    @Override
    public void setDrawingWidth(int width) {
        //do nothing
    }
}
