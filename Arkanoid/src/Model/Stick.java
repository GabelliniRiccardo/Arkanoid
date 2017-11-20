
package Model;


public class Stick extends AbstractDrawingModel{
    protected int width;
    protected int height;
    protected double x;
    protected double y;

    public Stick(double x, double y, int width, int height){
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
    }

    @Override
    public int getDrawingWidth() {
        return width;
    }

    @Override
    public int getDrawingHeight() {
        return height;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double X){
        x=X;
    }
    
    public void setY(double Y){
        y=Y;
    }
    
    public void periodicUpdate(int period){
        //donothing
    }
    
    public double getvAlongX(){
        return 0;
    }
    
    public double getvAlongY(){
        return 0;
    }
    
    public void setvAlongX(double v){
    }
        
    public void setvAlongY(double v){    
    }

    @Override
    public String getID() {
        return "stick";
    }

    @Override
    public void setID(String id) {
    }

    @Override
    public int getLifes() {
        return -1;//identifica che è indistruttibile 
    }

    @Override
    public void setLifes(int lifes) {
    }
    
    @Override
    public int getBonusID() {
        return 0;
    }

    @Override
    public void setBonusID(int bonus) {
        //do nothing
    }

    @Override
    public void setDrawingWidth(int width) {
        this.width=width;
    }
    
}
