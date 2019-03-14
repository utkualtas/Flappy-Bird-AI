import java.awt.List;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {

	Game game;
	Menu menu;
	int speed = 1;
	
	public static void main(String[] args) {
		PApplet.main("Main");
		
	}

	public void settings(){
		size(1200,800);
    }

    public void setup(){
    	menu = new Menu(this);
    	game = new Game(this, menu);
    	game.setup();
    	frameRate(999);
    }

    public void draw(){
    	background(0);
    	game.display();
    	
    }
    
    
    
    public void keyPressed() {
    	if (key == 't' || key == 'T') {
    		System.out.println("Instant Score: " + game.population.frameCount);
    		 Thread thread = new Thread(){
    			    public void run(){
    			     game.trainMode();
    			    }
    		 }; thread.start();
    		
    		if(game.trainMode) {
    	    	  loop();
    	    	  game.trainMode = false;
    	      } else {
    	    	  noLoop();
    	    	  game.trainMode = true;
    	      }
    	}   	
    }
    
    
	
	
	
	

}
