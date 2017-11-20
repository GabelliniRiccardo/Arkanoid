
package Model;


public class Bonus extends AbstractDrawingModel {
    private double vAlongx=0;
    private double vAlongy;
    private double x;
    private double y;
    private int width;
    private int height;
    private String ID;
    
    public Bonus(double x, double y, double vAlongy, int width, int height, String ID){
        this.vAlongy=vAlongy;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.ID=ID;
    }
    
    @Override
    public int getDrawingWidth() {
        return width;
    }

    @Override
    public int getDrawingHeight() {
        return height;
    }

    @Override
    public void periodicUpdate(int period) {
        y=y+period*vAlongy;
        setY(y);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
    public double getvAlongX() {
        return vAlongx;
    }

    @Override
    public double getvAlongY() {
        return vAlongy;
    }

    @Override
    public void setvAlongX(double v) {
        this.vAlongx=v;
    }

    @Override
    public void setvAlongY(double v) {
        this.vAlongy=v;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String id) {
        this.ID=id;
    }

    @Override
    public int getLifes() {
        return -1;//è indistruttibile
    }

    @Override
    public void setLifes(int lifes) {
        //do nothing
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
