
package Model;

public abstract class AbstractDrawingModel {

	public abstract int getDrawingWidth();

	public abstract int getDrawingHeight();
        
        public abstract void setDrawingWidth(int width);
        
        public abstract void periodicUpdate(int period);
        
        public abstract double getX();
        
        public abstract double getY();
        
        public abstract void setX(double X);
        
        public abstract void setY(double Y);
        
        public abstract double getvAlongX();
        
        public abstract double getvAlongY();
        
        public abstract void setvAlongX(double v);
        
        public abstract void setvAlongY(double v);
        
        public abstract String getID();
        
        public abstract void setID(String id);
        
        public abstract int getLifes();
        
        public abstract void setLifes(int lifes);
        
        public abstract int getBonusID();
        
        public abstract void setBonusID(int bonus);
} // end class
