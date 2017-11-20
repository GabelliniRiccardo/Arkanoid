
package IO;

import maryb.player.Player;

public class MusicPlayer {
    
    static Player p;
    
    
    public MusicPlayer(){
        p = new Player();
        p.setCurrentVolume(0.7f); // Non Funziona il settaggio del volume
        p.setSourceLocation(IO.Utilities.getAbsolutePathFromRelativePath("sound\\Music.mp3"));
    }
    
    public void playMusic(){
        p.play();
    }
    
    public void pauseMusic(){
        p.pause();
    }
    
    public void stopMusic(){
        p.stop();
    }
    
    public void restartMusicIfEnded(){
        if(p.isEndOfMediaReached())
            p.play();
    }
    
}