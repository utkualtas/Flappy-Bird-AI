
import java.awt.List;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Game {

	PApplet p;
	Pipes pipes;
	Population population;
	boolean trainMode = false;
	Menu menu;
	
	public Game(PApplet p, Menu menu) {
		this.p = p;
		this.menu = menu;
		
		
	}

    public void setup(){
    	pipes = new Pipes(p);
    	population = new Population(p);

    }

    public void display(){
    	p.background(0);
    	
		population.setPipes(pipes.getPipes());
    	pipes.display();
    	population.display();
    	
    	if(population.isAllBirdsDie()) {
    		pipes.reset();
    	}
    	population.update();	
    	
    	menu.display();
    	menu.setGeneration(population.getGeneration());
    	menu.setScore(population.getScore());
    	
    	
    }
    
    public void trainMode() {
    	while(trainMode) {
    		p.background(0);
        	population.setPipes(pipes.getPipes());
        	pipes.display();
        	population.display();
        	
        	if(population.isAllBirdsDie()) {
        		pipes.reset();
        	}
        	population.update();	
    	}
    }
    
 
    
	
	
	
	

}
