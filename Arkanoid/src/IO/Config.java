package IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {

    //STATIC FIELDS
    private static Config config = null;

    //INSTANCE FIELDS
    private Properties properties;

    private Config(String fileName){
        try{
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ISO-8859-1"));
            this.properties = new Properties();
            this.properties.load(buffRead);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //STATIC METHODS
    public static Config getInstance(String fileName){
        if (config == null){
            try{
                config = new Config(fileName);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return config;
    }
    
    public int getDistanceBetweenScores(){
        return Integer.valueOf(this.properties.getProperty("DISTANCEBETWEENSCORES"));
    }
    
    public int getXOfArkanoidLogo(){
        return Integer.valueOf(this.properties.getProperty("XOFARKANOIDLOGO"));
    }
    
    public int getFontOfScoreSize(){
        return Integer.valueOf(this.properties.getProperty("FONTOFSCORESIZE"));
    }

    public String getOneLifeBlockColor() {
        return this.properties.getProperty("ONELIFEBLOCKCOLOR");
    }
    
    public int getLifes(){
        return Integer.valueOf(this.properties.getProperty("LIFES"));
    }
    
    public int getMS(){
        return Integer.valueOf(this.properties.getProperty("MS"));
    }
    
    public int getPanelWidth(){
        return Integer.valueOf(this.properties.getProperty("PANELWIDTH"));
    }
    
    public int getPanelHeight(){
        return Integer.valueOf(this.properties.getProperty("PANELHEIGHT"));
    }
    
    public int getStep(){
        return Integer.valueOf(this.properties.getProperty("STEP"));
    }
    
    public int getXBall(){
        return Integer.valueOf(this.properties.getProperty("XBALL"));
    }
    
    public int getYBall(){
        return Integer.valueOf(this.properties.getProperty("YBALL"));
    }
    
    public int getXStick(){
        return Integer.valueOf(this.properties.getProperty("XSTICK"));
    }
   
    public int getYStick(){
        return Integer.valueOf(this.properties.getProperty("YSTICK"));
    }

    public int getXStickS(){
        return Integer.valueOf(this.properties.getProperty("XSTICKS"));
    }
    
    public int getYStickS(){
        return Integer.valueOf(this.properties.getProperty("YSTICKS"));
    }
    
    public int getYStickD(){
        return Integer.valueOf(this.properties.getProperty("YSTICKD"));
    }

    public int getXStickD(){
        return Integer.valueOf(this.properties.getProperty("XSTICKD"));
    }
    
    public int getXBoss(){
        return Integer.valueOf(this.properties.getProperty("XBOSS"));
    }

    public double getVAlongX(){
        return Double.valueOf(this.properties.getProperty("VALONGX"));
    }
    
    public double getVAlongY(){
        return Double.valueOf(this.properties.getProperty("VALONGY"));
    }

    public double getVAlongXBoss(){
        return Double.valueOf(this.properties.getProperty("VALONGXBOSS"));
    }
    
    public int getWidthBall(){
        return Integer.valueOf(this.properties.getProperty("WIDTHBALL"));
    }
    
    public int getHeightBall(){
        return Integer.valueOf(this.properties.getProperty("HEIGHTBALL"));
    }
    
    public int getWidthStick(){
        return Integer.valueOf(this.properties.getProperty("WIDTHSTICK"));
    }
    
    public int getHeightStick(){
        return Integer.valueOf(this.properties.getProperty("HEIGHTSTICK"));
    }
    
    public int getArcWidth(){
        return Integer.valueOf(this.properties.getProperty("ARCWIDTH"));
    }
    
    public int getArcHeight(){
        return Integer.valueOf(this.properties.getProperty("ARCHEIGHT"));
    }
    
    public int getNumOfLevels(){
        return Integer.valueOf(this.properties.getProperty("NUMOFLEVELS"));
    }
    
    public String getBallColor(){
        return this.properties.getProperty("BALLCOLOR");
    }
    
    public String getStickColor(){
        return this.properties.getProperty("STICKCOLOR");
    }
    
    public String getDrawingPanelColor(){
        return this.properties.getProperty("DRAWINGPANELCOLOR");
    }

    public String getTwoLifeBlockColor(){
        return this.properties.getProperty("TWOLIFEBLOCKCOLOR");
    }

    public String getThreeLifeBlockColor(){
        return this.properties.getProperty("THREELIFEBLOCKCOLOR");
    }
    
    public String getScoreColor(){
        return this.properties.getProperty("SCORECOLOR");
    }
    
    public String getBossLifesColor(){
        return this.properties.getProperty("BOSSLIFESCOLOR");
    }
    
    public int getNumOfPropertiesForIdentifyingEachBlock(){
        return Integer.valueOf(this.properties.getProperty("NUMOFPROPERTIESFORIDENTIFYINGEACHBLOCK"));
    }
    
    public int getWidthBonus(){
        return Integer.valueOf(this.properties.getProperty("WIDTHBONUS"));
    }
    
    public int getHeightBonus(){
        return Integer.valueOf(this.properties.getProperty("HEIGHTBONUS"));
    }
    
    public int getScoreHeight(){
        return Integer.valueOf(this.properties.getProperty("SCOREHEIGHT"));
    }
    
    public double getBonusVAlongY(){
        return Double.valueOf(this.properties.getProperty("BONUSVALONGY"));
    }
    
    public String getColorBonusOne(){
        return this.properties.getProperty("COLORBONUSONE");
    }
    
    public String getColorBonusTwo(){
        return this.properties.getProperty("COLORBONUSTWO");
    }
    
    public String getColorBonusThree(){
        return this.properties.getProperty("COLORBONUSTHREE");
    }
    
    public String getColorBonusFour(){
        return this.properties.getProperty("COLORBONUSFOUR");
    }
    
    public String getColorBonusFive(){
        return this.properties.getProperty("COLORBONUSFIVE");
    }
    
    public String getBossColor(){
        return this.properties.getProperty("BOSSCOLOR");
    }
    
    public String getColorOfTheScores(){
        return this.properties.getProperty("COLOROFTHESCORES");
    }
    
    public int getBossLifes(){
        return Integer.valueOf(this.properties.getProperty("BOSSLIFES"));
    }
    
    public int getLifesY(){
        return Integer.valueOf(this.properties.getProperty("LIFESY"));
    }
    
    public int getBossLifesX(){
        return Integer.valueOf(this.properties.getProperty("BOSSLIFESX"));
    }
    
    public int getFactorOfMoltiplicationOfVBoss(){
        return Integer.valueOf(this.properties.getProperty("FACTOROFMOLTIPLICATIONOFVBOSS"));
    }
    
    public int getPeriodOfFallingBonusFromBoss(){
        return Integer.valueOf(this.properties.getProperty("PERIODOFFALLINGBONUSFROMBOSS"));
    }
    
    public int getFactorOfMoltiplicationOfTheScore(){
        return Integer.valueOf(this.properties.getProperty("FACTOROFMOLTIPLICATIONOFSCORE"));
    }
    
    public int getYOfTheFirstScore(){
        return Integer.valueOf(this.properties.getProperty("YOFTHEFIRSTSCORE"));
    }
    
    public int getPeriodOfIncreasingTheVOfTheBall(){
        return Integer.valueOf(this.properties.getProperty("PERIODOFINCREASINGTHEVOFTHEBALL"));
    }
    
    public double getFactorOfMoltiplicationVBall(){
        return Double.valueOf(this.properties.getProperty("FACTOROFMOLTIPLICATIONVBALL"));
    }
} // end class

