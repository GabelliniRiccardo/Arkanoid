package Model;
import java.util.ArrayList;

/*dw contiene gli elementi di un livello. si adotta che il primo elemento di questo arraylist è la pallina.
Nella prima posizione di dw c'è la pallina, in ogni livello. Nella seconda c'è la sbarretta(parte centrale), per ogni livello.
Nella terza c'è la parte sinistra della sbarretta nella quarta c'è la parte destra della sbarretta*/

public class Model implements IModel {
    private ArrayList<AbstractDrawingModel> dw;
    static int currentLevel;
    static int lifes;
    static int bossLifes;
    private static Model model = null;
    final static double[][] ballPoints=new double[8][2];
    static int[] ritorno=new int[3];
    static int count=0;
    
    private Model(){
        dw=new ArrayList<>();
    }

    public static IModel getInstance(){
        if (model == null)
                model = new Model();
        return model;
    }
    
    public int getBossLifes() {
        return bossLifes;
    }

    public void setBossLifes(int bossLifes) {
        Model.bossLifes = bossLifes;
    }
    
    public int getCurrentLevel(){
        return currentLevel;
    }
    
    public void setCurrentLevel(int currentLevel){
        this.currentLevel=currentLevel;
    }
    
    public int getLifes(){
        return lifes;
    }
    
    public void setLifes(int lifes){
        this.lifes=lifes;
    }
    
    public void addElem(AbstractDrawingModel elem){
        dw.add(elem);
    }
    
    public Object removeElem(int index){
        return dw.remove(index);
    }
    
    public AbstractDrawingModel getElem(int index){
        return dw.get(index);
    }
    
    public ArrayList<AbstractDrawingModel> getList(){
        return dw;
    }
    
    public int numOfElemList(){
        return dw.size();
    }
    
    public AbstractDrawingModel getLastElemList(){
        return dw.get(dw.size()-1);
    }
    
    public void listUpdate(int period){
        for(AbstractDrawingModel i:dw)
            i.periodicUpdate(period);
    }
    
    public void stickUpdateSx(int step){
        dw.get(1).setX(dw.get(1).getX()-step);
        dw.get(2).setX(dw.get(2).getX()-step);
        dw.get(3).setX(dw.get(3).getX()-step);
    }
    
     public void stickUpdateDx(int step){
        dw.get(1).setX(dw.get(1).getX()+step);
        dw.get(2).setX(dw.get(2).getX()+step);
        dw.get(3).setX(dw.get(3).getX()+step);
    }
    
   public boolean checkLose(int height){
       AbstractDrawingModel ball=dw.get(0);
       boolean ritorno=false;
       if((ball.getY()+ball.getDrawingHeight())>=height){//dal basso
            ritorno=true;
            ball.setvAlongY(-ball.getvAlongY());
       }
       return ritorno;
   }
   
   public boolean ArrivedAtTheBottomOfTheScreen(AbstractDrawingModel bonus, int height){
       return bonus.getY()>=height;
   }
   
   public int[] getTheThreeIndexsOfBossPieces(){
       count =0;
       for(int i=0; i<dw.size(); i++)
            if(dw.get(i).getID().equals("boss")){
               count++;
                switch(count){
                    case 1: 
                       ritorno[0]=i;
                    break;
                    case 2:
                       ritorno[1]=i;
                    break;
                    case 3:
                       ritorno[2]=i;
                    break;
                } 
            }
       return ritorno;
   }
   
   public boolean checkAndSolveTheCollisionOfTheBoss(int width){
        int[] appoggio=getTheThreeIndexsOfBossPieces();
        int leftOfBoss=appoggio[0];
        int centreOfBoss=appoggio[1];
        int rightOfBoss=appoggio[2];
        
        if(dw.get(leftOfBoss).getX()<=0){
            dw.get(leftOfBoss).setvAlongX(-dw.get(leftOfBoss).getvAlongX());
            dw.get(centreOfBoss).setvAlongX(-dw.get(centreOfBoss).getvAlongX());
            dw.get(rightOfBoss).setvAlongX(-dw.get(rightOfBoss).getvAlongX());
        }
        
        if(dw.get(rightOfBoss).getX()+dw.get(rightOfBoss).getDrawingWidth()>=width){
            dw.get(leftOfBoss).setvAlongX(-dw.get(leftOfBoss).getvAlongX());
            dw.get(centreOfBoss).setvAlongX(-dw.get(centreOfBoss).getvAlongX());
            dw.get(rightOfBoss).setvAlongX(-dw.get(rightOfBoss).getvAlongX());
        }
        return (checkAndSolveCollisionBetweenBallAndARectangle(dw.get(0), dw.get(leftOfBoss))||
                checkAndSolveCollisionBetweenBallAndARectangle(dw.get(0), dw.get(centreOfBoss))||
                checkAndSolveCollisionBetweenBallAndARectangle(dw.get(0), dw.get(rightOfBoss)));
   }
   
    public boolean checkAndSolveCollisionBetweenBallAndPanel(int width){
        AbstractDrawingModel ball=dw.get(0);
        boolean ritorno=false;
        
        if(ball.getX()<=0){//da sinistra
            ball.setvAlongX(-ball.getvAlongX());
            ritorno=true;
        }
        
        if((ball.getX()+ball.getDrawingWidth())>=width){//da destra
            ball.setvAlongX(-ball.getvAlongX());
            ritorno=true;
        }
        
        if(ball.getY()<=30){//dall' alto
            ball.setvAlongY(-ball.getvAlongY());
            ritorno=true;
        }
        
        return ritorno;
    }
     
    public boolean checkAndSolveCollisionsBetweenBallAndStick(int width){
        boolean ritorno=false;
        double alfa=0;//fattore
        double beta=0;
        final double COEFFICIENT = 0.025;
        double vModuleBeforeCollision=0;
        
        AbstractDrawingModel ball=dw.get(0);
        AbstractDrawingModel stick=dw.get(1);
        AbstractDrawingModel stickS=dw.get(2);
        AbstractDrawingModel stickD=dw.get(3);

        //collisione con la parte centrale della sbarretta
        ritorno=checkAndSolveCollisionBetweenBallAndARectangle(ball,stick);
        
        //collisione con la parte a sinistra della sbarretta
        if(checkAndSolveCollisionBetweenBallAndARectangle(ball, stickS)){
                vModuleBeforeCollision=Math.sqrt(Math.pow(ball.getvAlongX(), 2)+Math.pow(ball.getvAlongY(), 2));
                alfa=1+((stickS.getX()+stickS.getDrawingWidth())-(ball.getX()+ball.getDrawingWidth()/2))*COEFFICIENT;
                if(ball.getvAlongX()>=0)
                    ball.setvAlongX(ball.getvAlongX()/alfa);
                if(ball.getvAlongX()<0)
                    ball.setvAlongX(ball.getvAlongX()*alfa);
                beta=vModuleBeforeCollision/(Math.sqrt(Math.pow(ball.getvAlongX(), 2)+Math.pow(ball.getvAlongY(), 2)));
                ball.setvAlongX(ball.getvAlongX()*beta);
                ball.setvAlongY(ball.getvAlongY()*beta);
                ritorno=true;
            }
        
        //collisione con la parte destra della sbarretta
        if(checkAndSolveCollisionBetweenBallAndARectangle(ball, stickD)){
                vModuleBeforeCollision=Math.sqrt(Math.pow(ball.getvAlongX(), 2)+Math.pow(ball.getvAlongY(), 2));
                alfa=1+((ball.getX()+ball.getDrawingWidth()/2)-stickD.getX())*COEFFICIENT;
                if(ball.getvAlongX()>=0)
                    ball.setvAlongX(ball.getvAlongX()*alfa);
                if(ball.getvAlongX()<0)
                    ball.setvAlongX(ball.getvAlongX()/alfa);
                beta=vModuleBeforeCollision/(Math.sqrt(Math.pow(ball.getvAlongX(), 2)+Math.pow(ball.getvAlongY(), 2)));
                ball.setvAlongX(ball.getvAlongX()*beta);
                ball.setvAlongY(ball.getvAlongY()*beta);
                ritorno=true;
            }
        return ritorno;
    }
    
    public boolean checkAndSolveCollsionsBetweenStickAndPanel(int width){
        AbstractDrawingModel stick=dw.get(1);
        AbstractDrawingModel stickS=dw.get(2);
        AbstractDrawingModel stickD=dw.get(3);
        boolean ritorno=false;
        if(stickS.getX()<=0){
            stickS.setX(0);
            stick.setX(stickS.getDrawingWidth());
            stickD.setX(stickS.getDrawingWidth()+stick.getDrawingWidth());
            ritorno=true;
        }
        if((stickD.getX()+stickD.getDrawingWidth())>=width){
            stickD.setX(width-stickD.getDrawingWidth());
            stick.setX(width-stickD.getDrawingWidth()-stick.getDrawingWidth());
            stickS.setX(width-stickD.getDrawingWidth()-stick.getDrawingWidth()-stickS.getDrawingWidth());
            ritorno=true;
        }
        return ritorno;
    }
    
    public boolean checkAndSolveCollisionBetweenBallAndARectangle(AbstractDrawingModel ball, AbstractDrawingModel currentRectangle){
        double x=0;
        double y=0;
        int widthBlock=0;
        int heightBlock=0;
        double raggio=ball.getDrawingWidth()/2;
        boolean collided=false;
        ballPoints[0][0]=ball.getX()+raggio;
        ballPoints[0][1]=ball.getY();
        ballPoints[1][0]=ball.getX()+raggio*(1+(Math.sqrt(2))/2);
        ballPoints[1][1]=ball.getY()+raggio*(1-(Math.sqrt(2))/2);
        ballPoints[2][0]=ball.getX()+2*raggio;
        ballPoints[2][1]=ball.getY()+raggio;
        ballPoints[3][0]=ballPoints[1][0];
        ballPoints[3][1]=ballPoints[1][1]+Math.sqrt(2)*raggio;
        ballPoints[4][0]=ballPoints[0][0];
        ballPoints[4][1]=ball.getY()+2*raggio;
        ballPoints[5][0]=ball.getX()+raggio*(1-(Math.sqrt(2))/2);
        ballPoints[5][1]=ballPoints[3][1];
        ballPoints[6][0]=ball.getX();
        ballPoints[6][1]=ball.getY()+raggio;
        ballPoints[7][0]=ballPoints[5][0];
        ballPoints[7][1]=ballPoints[1][1];
        
        
        x=currentRectangle.getX();
        y=currentRectangle.getY();
        widthBlock=currentRectangle.getDrawingWidth();
        heightBlock=currentRectangle.getDrawingHeight();

        //coll da sopra un blocco
        if(ball.getvAlongY()>=0 && 
           ((ballPoints[4][0]>=x && ballPoints[4][0]<=(x+widthBlock) && ballPoints[4][1]>=y && ballPoints[4][1]<y+heightBlock)||
           (ballPoints[3][0]>=x && ballPoints[3][0]<=(x+widthBlock) && ballPoints[3][1]>=y && ballPoints[3][1]<y+heightBlock)||
           (ballPoints[5][0]>=x && ballPoints[5][0]<=(x+widthBlock) && ballPoints[5][1]>=y) &&  ballPoints[5][1]<y+heightBlock)){
                collided=true;
                ball.setvAlongY(-ball.getvAlongY());
        }
        //coll da sotto un blocco 
        if(ball.getvAlongY()<=0 && 
          ((ballPoints[0][0]>=x && ballPoints[0][0]<=(x+widthBlock) && ballPoints[0][1]<=(y+heightBlock) && ballPoints[0][1]>y)||
          (ballPoints[1][0]>=x && ballPoints[1][0]<=(x+widthBlock) && ballPoints[1][1]<=(y+heightBlock) && ballPoints[1][1]>y)||
          (ballPoints[7][0]>=x && ballPoints[7][0]<=(x+widthBlock) && ballPoints[7][1]<=(y+heightBlock) && ballPoints[7][1]>y))){
                collided=true;
                ball.setvAlongY(-ball.getvAlongY());
        }
        //coll da sinistra di un blocco
         if(ball.getvAlongX()>=0 && 
            ((ballPoints[1][1]<=(y+heightBlock) && ballPoints[1][1]>=y && ballPoints[1][0]>=x && ballPoints[1][0]<x+widthBlock)||
            (ballPoints[2][1]<=(y+heightBlock) && ballPoints[2][1]>=y && ballPoints[2][0]>=x) && ballPoints[2][0]<x+widthBlock||
            (ballPoints[3][1]<=(y+heightBlock) && ballPoints[3][1]>=y && ballPoints[3][0]>=x) && ballPoints[3][0]<x+widthBlock)){
                collided=true;
                ball.setvAlongX(-ball.getvAlongX());
        }

        //coll da destra di un blocco
        if(ball.getvAlongX()<=0 && 
          ((ballPoints[6][1]<=(y+heightBlock) && ballPoints[6][1]>=y && ballPoints[6][0]<=(x+widthBlock) && ballPoints[6][0]>x)||
          (ballPoints[5][1]<=(y+heightBlock) && ballPoints[5][1]>=y && ballPoints[5][0]<=(x+widthBlock) && ballPoints[5][0]>x)||
          (ballPoints[7][1]<=(y+heightBlock) && ballPoints[7][1]>=y && ballPoints[7][0]<=(x+widthBlock) && ballPoints[7][0]>x))){
                collided=true;   
                ball.setvAlongX(-ball.getvAlongX());
        }
        return collided;
    }
}
