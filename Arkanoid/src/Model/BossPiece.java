
package Model;


public class BossPiece extends Stick {
    
    double vAlongX;
    int lifes;
    int bonus;
    
    public BossPiece(double x, double y, int width, int height){
        super(x,y,width,height);
    }
    
    @Override
    public void periodicUpdate(int period){
        x=x+vAlongX*period;
    }
    
    @Override
    public double getvAlongX(){
        return vAlongX;
    }
    
    @Override
    public void setvAlongX(double v){
        vAlongX=v;
    }
    
    @Override
    public String getID() {
        return "boss";
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
    public int getBonusID() {
        return bonus;
    }
    
    @Override
    public void setBonusID(int bonus) {
        this.bonus=bonus;
    }
    
    @Override
    public void setDrawingWidth(int width) {
       //do nothimg
    }
}
