
package Model;

public class Ball extends AbstractDrawingModel {
    private double vAlongx;
    private double vAlongy;
    private double x;
    private double y;
    private int width;
    private int height;
    
    public Ball(int x, int y, double vAlongx, double vAlongy, int width, int height){
        this.vAlongx=vAlongx;
        this.vAlongy=vAlongy;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    @Override
    public int getDrawingWidth() {
        return width;
    }

    @Override
    public int getDrawingHeight() {
        return height;
    }
    
    public void setvAlongX(double v){
        vAlongx=v;
    }
    
    public void setvAlongY(double v){
        vAlongy=v;
    }
    
    public double getvAlongX(){
        return vAlongx;
    }
    
    public double getvAlongY(){
        return vAlongy;
    }
    
    public void periodicUpdate(int period){
        x=x+period*vAlongx;
        setX(x);
        y=y+period*vAlongy;
        setY(y);
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double X){
        this.x=X;
    }
    
    public void setY(double Y){
        this.y=Y;
    }

    @Override
    public String getID() {
        return "ball";
    }

    @Override
    public void setID(String ID) {
    }

    @Override
    public int getLifes() {
        return -1;//10 identifica l'indistruttibilità
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
        //do nothing
    }
}
