
package Model;

import java.util.ArrayList;

public interface IModel {
    
    public int getBossLifes();
    
    public void setBossLifes(int bossLifes);
    
    public int getCurrentLevel();
    
    public void setCurrentLevel(int currentLevel);
    
    public int getLifes();
    
    public void setLifes(int lifes);
    
    public void addElem(AbstractDrawingModel elem);
    
    public Object removeElem(int index);
    
    public AbstractDrawingModel getElem(int index);
    
    public ArrayList<AbstractDrawingModel> getList();
    
    public int numOfElemList();
    
    public AbstractDrawingModel getLastElemList();
    
    public void listUpdate(int period);
    
    public void stickUpdateSx(int step);
    
    public void stickUpdateDx(int step);
    
    public boolean checkLose(int height);
   
    public boolean ArrivedAtTheBottomOfTheScreen(AbstractDrawingModel bonus, int height);
   
    public int[] getTheThreeIndexsOfBossPieces();
   
    public boolean checkAndSolveTheCollisionOfTheBoss(int width);
   
    public boolean checkAndSolveCollisionBetweenBallAndPanel(int width);
     
    public boolean checkAndSolveCollisionsBetweenBallAndStick(int width);
    
    public boolean checkAndSolveCollsionsBetweenStickAndPanel(int width);
    
    public boolean checkAndSolveCollisionBetweenBallAndARectangle(AbstractDrawingModel ball, AbstractDrawingModel currentRectangle);
}
