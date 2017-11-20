package Controller;
import IO.*;
import View.*;
import Model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements KeyListener {
    boolean stickUpdateSx=false;
    boolean stickUpdateDx=false;
    boolean updateTheStick=false; 
    MusicPlayer music;
    IView view;
    IModel model;
    static boolean listenForEnter=true;//ci dice se il gioco sta aspettando che si prema invio per passare ad un altro livello
    static int countCollisionsBetweenBallAndStickWhenBallIsMagenta;
    static int numOfTimesThatTimeGeneretedAEvent;
    static double currentTime;
    ArrayList<Integer> elemToRemove;
    static boolean won;
    static String configPath=Utilities.getAbsolutePathFromRelativePath("config\\config.txt");
    final static int MS=Config.getInstance(configPath).getMS();
    final static int PANELWIDTH=Config.getInstance(configPath).getPanelWidth();
    final static int PANELHEIGHT=Config.getInstance(configPath).getPanelHeight();
    final static int STEP=Config.getInstance(configPath).getStep();
    final static int XBALL=Config.getInstance(configPath).getXBall();
    final static int YBALL=Config.getInstance(configPath).getYBall();
    final static int XSTICK=Config.getInstance(configPath).getXStick();
    final static int YSTICK=Config.getInstance(configPath).getYStick();
    final static int XSTICKS=Config.getInstance(configPath).getXStickS();
    final static int YSTICKS=Config.getInstance(configPath).getYStickS();
    final static int XSTICKD=Config.getInstance(configPath).getXStickD();
    final static int YSTICKD=Config.getInstance(configPath).getYStickD();
    final static int XBOSS=Config.getInstance(configPath).getXBoss();
    final static double VALONGX=Config.getInstance(configPath).getVAlongX();
    final static double VALONGY=Config.getInstance(configPath).getVAlongY();
    final static double VALONGXBOSS=Config.getInstance(configPath).getVAlongXBoss();
    final static int WIDTHBALL=Config.getInstance(configPath).getWidthBall();
    final static int HEIGHTBALL=Config.getInstance(configPath).getHeightBall();
    final static int WIDTHSTICK=Config.getInstance(configPath).getWidthStick();
    final static int HEIGHTSTICK=Config.getInstance(configPath).getHeightStick();
    final static int ARCWIDTH=Config.getInstance(configPath).getArcWidth();
    final static int ARCHEIGHT=Config.getInstance(configPath).getArcHeight();
    final static int NUMOFLEVELS=Config.getInstance(configPath).getNumOfLevels();
    final static String BALLCOLOR=Config.getInstance(configPath).getBallColor();
    final static String STICKCOLOR=Config.getInstance(configPath).getStickColor();
    final static String DRAWINGPANELCOLOR=Config.getInstance(configPath).getDrawingPanelColor();
    final static int NUMOFPROPERTIESFORIDENTIFYINGEACHBLOCK=Config.getInstance(configPath).getNumOfPropertiesForIdentifyingEachBlock(); //CONTROLLARE SE USATO!!
    final static String ONELIFEBLOCKCOLOR=Config.getInstance(configPath).getOneLifeBlockColor();
    final static String TWOLIFEBLOCKCOLOR=Config.getInstance(configPath).getTwoLifeBlockColor();
    final static String THREELIFEBLOCKCOLOR=Config.getInstance(configPath).getThreeLifeBlockColor();
    final static String SCORECOLOR=Config.getInstance(configPath).getScoreColor();
    final static String BOSSLIFESCOLOR=Config.getInstance(configPath).getBossLifesColor();
    final static int WIDTHBONUS=Config.getInstance(configPath).getWidthBonus();
    final static int HEIGHTBONUS=Config.getInstance(configPath).getHeightBonus();
    final static int SCOREHEIGHT=Config.getInstance(configPath).getScoreHeight();//ALTEZZA RIQUADRO BIANCO IN ALTO
    final static double BONUSVALONGY=Config.getInstance(configPath).getBonusVAlongY();
    final static String COLORBONUSONE=Config.getInstance(configPath).getColorBonusOne();
    final static String COLORBONUSTWO=Config.getInstance(configPath).getColorBonusTwo();
    final static String COLORBONUSTHREE=Config.getInstance(configPath).getColorBonusThree();
    final static String COLORBONUSFOUR=Config.getInstance(configPath).getColorBonusFour();
    final static String COLORBONUSFIVE=Config.getInstance(configPath).getColorBonusFive();
    final static String BOSSCOLOR=Config.getInstance(configPath).getBossColor();
    final static String COLOROFTHESCORES=Config.getInstance(configPath).getColorOfTheScores();
    final static int LIFES=Config.getInstance(configPath).getLifes();
    final static int BOSSLIFES=Config.getInstance(configPath).getBossLifes();
    final static int LIFESY=Config.getInstance(configPath).getLifesY();
    final static int BOSSLIFESX=Config.getInstance(configPath).getBossLifesX();
    final static double FACTOROFMOLTIPLICATIONOFVBOSS=Config.getInstance(configPath).getFactorOfMoltiplicationOfVBoss();
    final static int PERIODOFFALLINGBONUSFROMBOSS=Config.getInstance(configPath).getPeriodOfFallingBonusFromBoss();
    final static int FACTOROFMOLTIPLICATIONOFSCORE=Config.getInstance(configPath).getFactorOfMoltiplicationOfTheScore();
    final static int YOFTHEFIRSTSCORE=Config.getInstance(configPath).getYOfTheFirstScore();
    final static int PERIODOFINCREASINGTHEVOFTHEBALL=Config.getInstance(configPath).getPeriodOfIncreasingTheVOfTheBall();
    final static double FACTOROFMOLTIPLICATIONVBALL=Config.getInstance(configPath).getFactorOfMoltiplicationVBall();
    final static int DISTANCEBETWEENSCORES=Config.getInstance(configPath).getDistanceBetweenScores();
    final static int FONTOFSCORESSIZE=Config.getInstance(configPath).getFontOfScoreSize();
    final static int XOFARKANOIDLOGO=Config.getInstance(configPath).getXOfArkanoidLogo();
    final static int YBOSS=Controller.SCOREHEIGHT+10;
    final static int BOSSWIDTH=Controller.WIDTHSTICK;
    final static int BOSSHEIGHT=Controller.HEIGHTSTICK;
    final static int XOFTHEFIRSTSCORE=Controller.PANELWIDTH/2-50;
    final static int BOSSLIFESY=Controller.LIFESY;
    final static int LIFESX=PANELWIDTH-70;
    final static int YOFARKANOIDLOGO=Controller.SCOREHEIGHT+30;

    public Controller(){
        model=Model.getInstance();
        view=View.getInstance();
        model.setCurrentLevel(1);
        model.setLifes(LIFES);
        view.addTimerAndListener(MS, new TimerListener());
        view.putKeyListener(this);
        view.addLevelPanel(PANELWIDTH, PANELHEIGHT);
        view.setImage(IO.Utilities.getImageLeveli(this.model.getCurrentLevel()));
        view.doRepaintImage();
        music=new MusicPlayer();
        music.playMusic();
        elemToRemove=new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e){
       
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(!listenForEnter)
                    if(view.isTimerRunning())
                        stickUpdateSx=true;
            break;
            case KeyEvent.VK_RIGHT:
                if(!listenForEnter)
                    if(view.isTimerRunning())
                        stickUpdateDx=true;
            break;
            case KeyEvent.VK_ENTER:
             
                if(!won){
                    if(!view.isTimerRunning() && !listenForEnter)
                        view.startTimer();

                    if(listenForEnter){
                        view.removeLevelPanel();
                        if(this.model.getCurrentLevel()==1){
                            model.addElem(new Ball(XBALL,YBALL,VALONGX,VALONGY,WIDTHBALL,HEIGHTBALL));
                            model.addElem(new Stick(XSTICK,YSTICK,WIDTHSTICK,HEIGHTSTICK));
                            model.addElem(new Stick(XSTICKS,YSTICKS,WIDTHSTICK,HEIGHTSTICK));
                            model.addElem(new Stick(XSTICKD,YSTICKD,WIDTHSTICK,HEIGHTSTICK));
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\StickCenter.gif").replace("StickCenter.gif", "Ball.gif")),XBALL,YBALL));
                            view.getLastElemList().setColor(IO.Utilities.getColorFromString(BALLCOLOR));
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\StickCenter.gif")),XSTICK,YSTICK));
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\StickCenter.gif").replace("StickCenter.gif", "StickSx.gif")),XSTICKS,YSTICKS));
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\StickCenter.gif").replace("StickCenter.gif", "StickDx.gif")),XSTICKD,YSTICKD));
                            numOfTimesThatTimeGeneretedAEvent=0;
                            won=false;
                            view.addString(new TextView(LIFESX,LIFESY,"Lifes: "+model.getLifes(), new Font("Cooper Black",Font.PLAIN,16)));
                            view.getLastString().setColor(IO.Utilities.getColorFromString(SCORECOLOR));
                            view.setDrawing();
                            view.getElem(1).setColor(IO.Utilities.getColorFromString(STICKCOLOR));//basta metterlo una volta tanto sono istanze della stessa classe
                        }
                        view.getElem(0).setColor(IO.Utilities.getColorFromString(BALLCOLOR));
                        if(model.getCurrentLevel()==NUMOFLEVELS){
                            currentTime=getCurrentTime();
                            model.addElem(new BossPiece(XBOSS, YBOSS, BOSSHEIGHT, BOSSWIDTH));
                            model.getLastElemList().setvAlongX(VALONGXBOSS);
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\BossSx.gif")), XBOSS, YBOSS));
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\BossCenter.gif")), (int)model.getLastElemList().getX()+model.getLastElemList().getDrawingWidth(), YBOSS));
                            model.addElem(new BossPiece(model.getLastElemList().getX()+model.getLastElemList().getDrawingWidth(), YBOSS, BOSSWIDTH, BOSSHEIGHT));
                            model.getLastElemList().setvAlongX(VALONGXBOSS);
                            view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\BossDx.gif")), (int)model.getLastElemList().getX()+model.getLastElemList().getDrawingWidth(), YBOSS));                           
                            model.addElem(new BossPiece(model.getLastElemList().getX()+model.getLastElemList().getDrawingWidth(), YBOSS, BOSSHEIGHT, BOSSWIDTH));
                            model.getLastElemList().setvAlongX(VALONGXBOSS);
                            model.setBossLifes(BOSSLIFES);
                            view.addString(new TextView(BOSSLIFESX,BOSSLIFESY,"BossLifes: "+model.getBossLifes(), new Font("Cooper Black",Font.PLAIN,16)));
                            view.getLastString().setColor(IO.Utilities.getColorFromString(BOSSLIFESCOLOR));
                            view.setDrawing();
                        }
                        loadTheNewLevel(); 
                        view.addDrawPanel(PANELWIDTH,PANELHEIGHT);
                        view.startTimer();
                        listenForEnter=false;
                    }
                }
                else{
                    won=false;
                    restartGameFromTheFirstLevel();
                }
            break;   
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                stickUpdateSx=false;  
            break;
            case KeyEvent.VK_RIGHT:
                stickUpdateDx=false;
            break;
        }
    }
    
    class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
            // Servono per gestire lo spostamento dello stick in modo più fluido
            if(stickUpdateDx)
                model.stickUpdateDx(STEP);
            if(stickUpdateSx)
                model.stickUpdateSx(STEP);
        
            music.restartMusicIfEnded();
            numOfTimesThatTimeGeneretedAEvent++;
            model.listUpdate(MS);
            manageTheUpgradeOfTheBall();
            
            if(model.checkAndSolveCollisionBetweenBallAndPanel(PANELWIDTH))
                OurSounds.playEffect();
            
            model.checkAndSolveCollsionsBetweenStickAndPanel(PANELWIDTH);
            manageCollisionsLifesBonusOfTheBlocks();
            manageTheLifeCycleOfEveryBonus();
            
           if(model.getCurrentLevel()==NUMOFLEVELS){
                createBonusEveryPeriod();
                int positions[]=model.getTheThreeIndexsOfBossPieces();
                if(model.checkAndSolveTheCollisionOfTheBoss(PANELWIDTH)){
                    OurSounds.playEffect();
                    currentTime=getCurrentTime();
                    model.setBossLifes(model.getBossLifes()-1);
                    view.getString(1).setText("Boss lifes: "+model.getBossLifes());
                    switch (model.getBossLifes()){
                        case 2:
                            model.getElem(positions[0]).setvAlongX(model.getElem(positions[0]).getvAlongX()*FACTOROFMOLTIPLICATIONOFVBOSS);
                            model.getElem(positions[1]).setvAlongX(model.getElem(positions[1]).getvAlongX()*FACTOROFMOLTIPLICATIONOFVBOSS);
                            model.getElem(positions[2]).setvAlongX(model.getElem(positions[2]).getvAlongX()*FACTOROFMOLTIPLICATIONOFVBOSS);
                        break;
                        case 1:
                            model.getElem(positions[0]).setvAlongX(model.getElem(positions[0]).getvAlongX()*FACTOROFMOLTIPLICATIONOFVBOSS);
                            model.getElem(positions[1]).setvAlongX(model.getElem(positions[1]).getvAlongX()*FACTOROFMOLTIPLICATIONOFVBOSS);
                            model.getElem(positions[2]).setvAlongX(model.getElem(positions[2]).getvAlongX()*FACTOROFMOLTIPLICATIONOFVBOSS);
                        break;
                        case 0:
                           manageTheEndOfTheGameIfTheUserWon();
                        break;
                    }
                }
            }
            updateTheViewListWithTheInformationOnTheModelList();
            view.setDrawing(); 
            view.doRepaint();
            
            
            //controlla che tutti i blocchi siano distrutti, e in caso passa al livello successivo
            if(model.getCurrentLevel()!=NUMOFLEVELS){
                loadTheImageOfTheNextLevelIfAllTheBlocksAreDestroyed();
            }
            
            //se non si è vinto ma ancora si hanno più di 0 vite si rinizia il livello = perdita di una vita
            if(!won && model.checkLose(PANELHEIGHT)){
                updateTheStick=true;
                model.setLifes(model.getLifes()-1);
                view.getString(0).setText("Lifes: "+model.getLifes());
                if(model.getLifes()>0){//Condizione di rinizio livello
                   reloadTheCurrentLevel();
                }
            }
            
            //Condizione di Game Over
            if(model.getLifes()==0){ 
                music.stopMusic();
                OurSounds.playGameOver();
                restartGameFromTheFirstLevel();
            }
        }
    }
    
    private int findThePositionForTheScoreOnTheList(int punteggio, LinkedList<String[]> list){//se non deve stare in lista ritorna -1
        int index=0;
        for(int i=0;i<list.size();i++)
            if(Integer.valueOf(list.get(i)[1])>=punteggio)
                index=i+1;
        if(index>=list.size())
            index=-1;
        return index;
    }
    
    private void restartGameFromTheFirstLevel(){
        model.getList().clear();
        view.getList().clear();
        view.getStrings().clear();
        model.setCurrentLevel(1);
        model.setLifes(LIFES);
        view.removeDrawPanel();
        view.addLevelPanel(PANELWIDTH, PANELHEIGHT);
        view.setImage(IO.Utilities.getImageLeveli(model.getCurrentLevel()));
        view.doRepaintImage();
        listenForEnter=true;
        view.stopTimer();
        music.playMusic();
    }
    
    public double getCurrentTime(){
        return MS*numOfTimesThatTimeGeneretedAEvent;
    }
    
    private void createBonusEveryPeriod(){
        int[] positions=model.getTheThreeIndexsOfBossPieces();
        AbstractDrawingModel centerOfBoss=model.getElem(positions[1]);
        switch (model.getBossLifes()){
            case 1:
                if(getCurrentTime()==(currentTime+PERIODOFFALLINGBONUSFROMBOSS)){
                    model.getList().add(new Bonus(centerOfBoss.getX()+centerOfBoss.getDrawingWidth()/2-WIDTHBONUS/2,centerOfBoss.getY()+centerOfBoss.getDrawingHeight(),BONUSVALONGY*2,WIDTHBONUS,HEIGHTBONUS,"bonus4"));
                    view.getList().add(new CircleView(centerOfBoss.getX()+centerOfBoss.getDrawingWidth()/2-WIDTHBONUS/2,centerOfBoss.getY()+centerOfBoss.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                    view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSFOUR));
                    currentTime=getCurrentTime();
                }
            break;
            case 2:
                if(getCurrentTime()==(currentTime+PERIODOFFALLINGBONUSFROMBOSS*FACTOROFMOLTIPLICATIONOFVBOSS)){
                    model.getList().add(new Bonus(centerOfBoss.getX()+centerOfBoss.getDrawingWidth()/2-WIDTHBONUS/2,centerOfBoss.getY()+centerOfBoss.getDrawingHeight(),BONUSVALONGY*2,WIDTHBONUS,HEIGHTBONUS,"bonus4"));
                    view.getList().add(new CircleView(centerOfBoss.getX()+centerOfBoss.getDrawingWidth()/2-WIDTHBONUS/2,centerOfBoss.getY()+centerOfBoss.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                    view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSFOUR));
                    currentTime=getCurrentTime();
                }
            break;       
            case 3:
                if(getCurrentTime()==(currentTime+PERIODOFFALLINGBONUSFROMBOSS*FACTOROFMOLTIPLICATIONOFVBOSS*FACTOROFMOLTIPLICATIONOFVBOSS)){
                    model.getList().add(new Bonus(centerOfBoss.getX()+centerOfBoss.getDrawingWidth()/2-WIDTHBONUS/2,centerOfBoss.getY()+centerOfBoss.getDrawingHeight(),BONUSVALONGY*3,WIDTHBONUS,HEIGHTBONUS,"bonus4"));
                    view.getList().add(new CircleView(centerOfBoss.getX()+centerOfBoss.getDrawingWidth()/2-WIDTHBONUS/2,centerOfBoss.getY()+centerOfBoss.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                    view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSFOUR));
                    currentTime=getCurrentTime();
                }
            break;
        }
    }
    
    private void loadTheImageOfTheNextLevelIfAllTheBlocksAreDestroyed(){
        int conta=0;
        for(AbstractDrawingModel i:model.getList())
            if(i.getID().substring(0,4).equals("Bloc"))
                conta++;
        if(conta==0){
            for(int i=model.getList().size()-1;i>=0;i--)//ci serve per togliere i bonus rimasti in lista ma non presi perché il livello è finito prima
                 if(model.getList().get(i).getID().substring(0,4).equals("bonu")){
                     model.removeElem(i);
                     view.removeElem(i);
                 }
            updateTheStick=true;
            view.stopTimer();
            model.setCurrentLevel(model.getCurrentLevel()+1);
            listenForEnter=true;
            setStickAndBallToDeafault();
            view.removeDrawPanel();
            view.addLevelPanel(PANELWIDTH, PANELHEIGHT);
            view.setImage(IO.Utilities.getImageLeveli(model.getCurrentLevel()));
            view.doRepaintImage();
        }
    }
    
    private void setBossToDefault(){
        int[] positions=model.getTheThreeIndexsOfBossPieces();
        model.getElem(positions[0]).setvAlongX(VALONGXBOSS);
        model.getElem(positions[1]).setvAlongX(VALONGXBOSS);
        model.getElem(positions[2]).setvAlongX(VALONGXBOSS);
        model.getElem(positions[0]).setX(XBOSS);
        model.getElem(positions[1]).setX(XBOSS+model.getElem(positions[0]).getDrawingWidth());
        model.getElem(positions[2]).setX(model.getElem(positions[1]).getX()+model.getElem(positions[1]).getDrawingWidth());
        model.setBossLifes(BOSSLIFES);
        view.getString(1).setText("Boss Lifes: "+model.getBossLifes());
    }
    
    private void updateTheViewListWithTheInformationOnTheModelList(){
        
        for(int i=0;i<model.getList().size();i++){
            if(!model.getList().get(i).getID().substring(0,4).equals("Bloc")) 
                view.getList().get(i).setX(model.getList().get(i).getX());
                view.getList().get(i).setY(model.getList().get(i).getY());
                }
        if(updateTheStick){
                view.getList().get(1).setDrawingWidth(model.getList().get(1).getDrawingWidth());
                view.getList().get(2).setDrawingWidth(model.getList().get(2).getDrawingWidth());
                view.getList().get(3).setDrawingWidth(model.getList().get(3).getDrawingWidth());
                updateTheStick=false;
        }
        
    }
    
    private void setStickAndBallToDeafault(){
        model.getList().get(1).setX(XSTICK);
        model.getList().get(1).setDrawingWidth(WIDTHSTICK);
        model.getList().get(2).setX(XSTICKS);
        model.getList().get(2).setDrawingWidth(WIDTHSTICK);
        model.getList().get(3).setX(XSTICKD);
        model.getList().get(3).setDrawingWidth(WIDTHSTICK);
        model.getList().get(0).setX(XBALL);
        model.getList().get(0).setY(YBALL);
        model.getList().get(0).setvAlongX(VALONGX);
        model.getList().get(0).setvAlongY(VALONGY);
        view.getList().add(0, new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\Ball.gif")),(int)view.getElem(0).getX(),(int)view.getElem(0).getY()));
        view.getList().remove(1);
        view.getElem(0).setColor(IO.Utilities.getColorFromString(BALLCOLOR));
    }
    
    /*controlla le collisioni tra la pallina e i blocchi e se avvengono decrementa la vita dei blocchi. 
    inoltre, se si rompe un blocco che ha un potenziamento, lo istanzia*/
    private void manageCollisionsLifesBonusOfTheBlocks(){ 
        
        AbstractDrawingModel currentBlock=null;
        
        for(int i=0;i<model.getList().size();i++){
            if (model.getList().get(i).getID().substring(0, 4).equals("Bloc"))
                if(model.checkAndSolveCollisionBetweenBallAndARectangle(model.getList().get(0), model.getList().get(i))){
                    OurSounds.playEffect();
                    if(view.getList().get(0).getColor().equals(Color.magenta))
                        model.getList().get(i).setLifes(0);
                    else
                        model.getList().get(i).setLifes(model.getList().get(i).getLifes()-1);
                    switch (model.getList().get(i).getLifes()){
                        case 0:
                            if(model.getList().get(i).getBonusID()>0){//distinguo fra i vari bonus contenuti nel blocco
                                currentBlock=model.getList().get(i);
                                switch (currentBlock.getBonusID()){
                                    case 1:
                                        model.getList().add(new Bonus(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),BONUSVALONGY,WIDTHBONUS,HEIGHTBONUS,"bonus1"));
                                        view.getList().add(new CircleView(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                                        view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSONE));
                                    break;
                                    case 2:
                                        model.getList().add(new Bonus(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),BONUSVALONGY,WIDTHBONUS,HEIGHTBONUS,"bonus2"));
                                        view.getList().add(new CircleView(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                                        view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSTWO));
                                    break;
                                    case 3:
                                        model.getList().add(new Bonus(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),BONUSVALONGY,WIDTHBONUS,HEIGHTBONUS,"bonus3"));
                                        view.getList().add(new CircleView(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                                        view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSTHREE));
                                    break;
                                    case 4:
                                        model.getList().add(new Bonus(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),BONUSVALONGY,WIDTHBONUS,HEIGHTBONUS,"bonus4"));
                                        view.getList().add(new CircleView(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                                        view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSFOUR));
                                    break;
                                    case 5:
                                        model.getList().add(new Bonus(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),BONUSVALONGY,WIDTHBONUS,HEIGHTBONUS,"bonus5"));
                                        view.getList().add(new CircleView(currentBlock.getX()+currentBlock.getDrawingWidth()/2-WIDTHBONUS/2,currentBlock.getY()+currentBlock.getDrawingHeight(),WIDTHBONUS,HEIGHTBONUS));
                                        view.getLastElemList().setColor(IO.Utilities.getColorFromString(COLORBONUSFIVE));
                                    break;
                                }
                            }
                            elemToRemove.add(i);
                        break;
                        case 1:
                            view.getList().get(i).setColor(IO.Utilities.getColorFromString(ONELIFEBLOCKCOLOR));
                        break;
                        case 2:
                            view.getList().get(i).setColor(IO.Utilities.getColorFromString(TWOLIFEBLOCKCOLOR));
                        break;
                    }
                }
            }
            for(int i=elemToRemove.size()-1;i>=0;i--){
               model.removeElem(elemToRemove.get(i));
               view.removeElem(elemToRemove.get(i));
            }
            elemToRemove.clear();
    }
    
    /*controlla se si è acquisito un bonus, distingue i tipi di bonus ottenuti e applica le giuste modifiche al model, oltretutto elimina i bonus che superano
    la parte inferiore dello schermo*/
    
    private void manageTheLifeCycleOfEveryBonus(){
        
        AbstractDrawingModel currentBonus=null;
        
        for(int i=0;i<model.getList().size();i++){
                if (model.getList().get(i).getID().substring(0, 4).equals("bonu")){
                    currentBonus=model.getList().get(i);
                    if(model.checkAndSolveCollisionBetweenBallAndARectangle(currentBonus, model.getList().get(1))||
                       model.checkAndSolveCollisionBetweenBallAndARectangle(currentBonus, model.getList().get(2)) ||
                       model.checkAndSolveCollisionBetweenBallAndARectangle(currentBonus, model.getList().get(3))){
                        
                        elemToRemove.add(i);
                        switch (currentBonus.getID()){
                            case "bonus1":
                                model.getList().get(1).setX(model.getList().get(1).getX()+model.getList().get(1).getDrawingWidth()/4);
                                model.getList().get(1).setDrawingWidth(model.getList().get(1).getDrawingWidth()/2);
                                model.getList().get(2).setX(model.getList().get(1).getX()-model.getList().get(2).getDrawingWidth()/2);
                                model.getList().get(2).setDrawingWidth(model.getList().get(2).getDrawingWidth()/2);
                                model.getList().get(3).setX(model.getList().get(1).getX()+model.getList().get(3).getDrawingWidth()/2);
                                model.getList().get(3).setDrawingWidth(model.getList().get(3).getDrawingWidth()/2);
                                updateTheStick=true;
                            break;
                            case "bonus2":
                                model.getList().get(1).setX(model.getList().get(1).getX()-model.getList().get(1).getDrawingWidth()/2);
                                model.getList().get(1).setDrawingWidth(model.getList().get(1).getDrawingWidth()*2);
                                model.getList().get(2).setX(model.getList().get(1).getX()-model.getList().get(2).getDrawingWidth()*2);
                                model.getList().get(2).setDrawingWidth(model.getList().get(2).getDrawingWidth()*2);
                                model.getList().get(3).setX(model.getList().get(1).getX()+model.getList().get(3).getDrawingWidth()*2);
                                model.getList().get(3).setDrawingWidth(model.getList().get(3).getDrawingWidth()*2);
                                updateTheStick=true;
                            break;
                            case "bonus3":
                                model.setLifes(model.getLifes()+1);
                                view.getString(0).setText("Lifes: "+model.getLifes());
                            break; 
                            case "bonus4":
                                model.setLifes(model.getLifes()-1);
                                view.getString(0).setText("Lifes: "+model.getLifes());
                            break;
                            case "bonus5":
                                view.getList().add(0, new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\MagentaBall.gif")),(int)view.getElem(0).getX(),(int)view.getElem(0).getY()));
                                view.getList().remove(1);
                                view.getElem(0).setColor(Color.MAGENTA);
                                countCollisionsBetweenBallAndStickWhenBallIsMagenta=0;
                            break;
                        }
                    }
                    if(model.ArrivedAtTheBottomOfTheScreen(currentBonus, PANELHEIGHT))
                        elemToRemove.add(i);
                }
        }
        for(int i=elemToRemove.size()-1;i>=0;i--){
            model.removeElem(elemToRemove.get(i));
            view.removeElem(elemToRemove.get(i));
        }
        elemToRemove.clear();
    }

    private void loadTheNewLevel(){
        LinkedList<String[]> rows = null;
        int x=0;
        int y=0;
        int width=0;
        int height=0;
        int lifes=0;
        int bonus=0;
        try{
            rows=IO.Utilities.readLeveli(model.getCurrentLevel(), "UTF-8");
        }catch(IOException ex){}
        for(String[] i:rows){//abbiamo deciso di codificare così il file
            x=Integer.valueOf(i[0]);
            y=Integer.valueOf(i[1]);
            width=Integer.valueOf(i[2]);
            height=Integer.valueOf(i[3]);
            lifes=Integer.valueOf(i[4]);
            bonus=Integer.valueOf(i[5]);
            model.addElem(new Block(x,y,width,height));
            view.addElem(new RectangleView(x,y,width,height,ARCWIDTH,ARCHEIGHT));
            model.getLastElemList().setLifes(lifes);
            model.getLastElemList().setBonusID(bonus);
            switch (lifes){
                case 1:
                    view.getLastElemList().setColor(IO.Utilities.getColorFromString(ONELIFEBLOCKCOLOR));
                break;
                case 2:
                    view.getLastElemList().setColor(IO.Utilities.getColorFromString(TWOLIFEBLOCKCOLOR));
                break;
                case 3:
                    view.getLastElemList().setColor(IO.Utilities.getColorFromString(THREELIFEBLOCKCOLOR));
                break;    
            }
        }
        increaseTheVOfTheBallEveryLevel();
    }
    
    private void  increaseTheVOfTheBallEveryLevel(){
        if(model.getCurrentLevel()!=1){
            if(model.getElem(0).getvAlongX()>0)
                model.getElem(0).setvAlongX(VALONGX+0.05);
            if(model.getElem(0).getvAlongX()<0)
                model.getElem(0).setvAlongX(VALONGX-0.05);
            if(model.getElem(0).getvAlongY()>0)
                model.getElem(0).setvAlongY(VALONGY+0.05);
            if(model.getElem(0).getvAlongY()<0)
                model.getElem(0).setvAlongY(VALONGY-0.05);
        }
            
    }
    
    private void manageTheUpgradeOfTheBall(){
        if(model.checkAndSolveCollisionsBetweenBallAndStick(PANELWIDTH)){//serve per gestire il potenziamento della pallina
                OurSounds.playEffect();
                if(view.getList().get(0).getColor().equals(Color.magenta)){
                    countCollisionsBetweenBallAndStickWhenBallIsMagenta++;
                    if(countCollisionsBetweenBallAndStickWhenBallIsMagenta==3){
                        view.getList().add(0, new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\Ball.gif")),(int)view.getElem(0).getX(),(int)view.getElem(0).getY()));
                        view.getList().remove(1);
                        view.getElem(0).setColor(IO.Utilities.getColorFromString(BALLCOLOR));
                    }
                }
            }
    }
    
    //condizione di fine del gioco dovuto alla vincita: l'utente ha la possibilità di visualizzare il punteggio ed inserire il proprio Nickname
    private void manageTheEndOfTheGameIfTheUserWon(){
         
        music.stopMusic();
        OurSounds.playWon();
        listenForEnter=true;
        view.stopTimer();
        model.getList().clear();
        view.getList().clear();
        view.getStrings().clear();
        view.doRepaint();
        won=true;
        int punteggio=(int)Math.floor(model.getLifes()*FACTOROFMOLTIPLICATIONOFSCORE*NUMOFLEVELS/getCurrentTime());
        LinkedList<String[]> list=null;
        try{list = IO.Utilities.readBestScores("UTF-8");}
        catch(IOException ex){}
        int position=findThePositionForTheScoreOnTheList(punteggio, list);
        String[] elemToAdd=new String[2];
        if(position!=-1){
            elemToAdd[0]=view.getTheNameOfTheUser("Inserisci Nome");
            elemToAdd[1]=String.valueOf(punteggio);
            list.remove(list.size()-1);
            list.add(position, elemToAdd);
        }
        try{IO.Utilities.writeBestScores(list, "UTF-8");}
        catch(IOException ex){}
        for(int i=0;i<list.size();i++){
            view.addString(new TextView(XOFTHEFIRSTSCORE,YOFTHEFIRSTSCORE+DISTANCEBETWEENSCORES*i,list.get(i)[0]+": "+list.get(i)[1],new Font("Cooper Black", Font.PLAIN, FONTOFSCORESSIZE)));
            view.getLastString().setColor(IO.Utilities.getColorFromString(COLOROFTHESCORES));
        }
        view.addString(new TextView(XOFTHEFIRSTSCORE-50,PANELHEIGHT-50,"Il tuo punteggio è: "+punteggio,new Font("Cooper Black", Font.PLAIN, 20)));
        view.getLastString().setColor(IO.Utilities.getColorFromString(COLOROFTHESCORES));
        view.addElem(new ImageView(IO.Utilities.getImageFromPath(IO.Utilities.getAbsolutePathFromRelativePath("images\\ArkanoidLogo.gif")), XOFARKANOIDLOGO, YOFARKANOIDLOGO));
        view.doRepaint();
    }
    
    private void reloadTheCurrentLevel(){
        
         for(int i=model.getList().size()-1; i>=4; i--)
            if(!model.getElem(i).getID().substring(0, 4).equals("boss")){//serve per non cancellare il boss prima di reiniziare il livello
                model.removeElem(i);
                view.removeElem(i);
            }
            setStickAndBallToDeafault();
            loadTheNewLevel();
            if(model.getCurrentLevel()==NUMOFLEVELS)
            setBossToDefault();
            
            view.stopTimer();
            updateTheViewListWithTheInformationOnTheModelList();
    }
}



