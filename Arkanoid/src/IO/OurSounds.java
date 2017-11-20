package IO;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class OurSounds {
    
    static File fileGameOver=new File(IO.Utilities.getAbsolutePathFromRelativePath("sound\\Game Over.wav"));
    static File fileWon=new File(IO.Utilities.getAbsolutePathFromRelativePath("sound\\Game Win.wav"));
    static File fileEffect=new File(IO.Utilities.getAbsolutePathFromRelativePath("sound\\effect.wav"));
    protected static Clip soundEffect = null;
    private static AudioInputStream audioInputStream;
    private static String relativePath;
    
    public static void playEffect(){
        try{
            initClip("sound\\effect.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
        playSound();
    }
    
    public static void playGameOver(){
        try{
            initClip("sound\\Game Over.wav");
            playSound();
            Thread.sleep(soundEffect.getMicrosecondLength()/1000);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e){
            e.printStackTrace();
        }
        
    }
    
    public static void playWon(){
        try{
            initClip("sound\\Game Win.wav");
            playSound();
            Thread.sleep(soundEffect.getMicrosecondLength()/1000);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e){
            e.printStackTrace();
        }
    }
	
	
	
    private static Clip soundEffect(String str) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //audioInputStream = AudioSystem.getAudioInputStream(new File(str).getAbsoluteFile());  //prende il formato audio e la lunghezza del file
        switch (str){
            case "sound\\Game Win.wav":
                audioInputStream=AudioSystem.getAudioInputStream(fileWon);
            break;
            case "sound\\Game Over.wav":
                audioInputStream=AudioSystem.getAudioInputStream(fileGameOver);
            break;
            case "sound\\effect.wav":
                audioInputStream=AudioSystem.getAudioInputStream(fileEffect);
            break;
        }
        soundEffect = AudioSystem.getClip(); //crea una clip che verrà caricata e riprodotta a necessità. non riproduzione/caricamento in runtime
        soundEffect.open(audioInputStream);  //apre la clip con quelle caratteristiche 
        return soundEffect;
    }


    private static void initClip(String str) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        relativePath=str;
        soundEffect = soundEffect(str);
    }

    private static void playSound(){
        (new Thread(playSoundEffect)).start();
    }
    
    final static Runnable playSoundEffect = new Runnable(){
        public synchronized void run() {
            if (soundEffect==null){
                try {
                    soundEffect = soundEffect(relativePath);
                }
                catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
            soundEffect.setFramePosition(0);
            soundEffect.start();
            }//run
        };//runnable
}//fine classe

