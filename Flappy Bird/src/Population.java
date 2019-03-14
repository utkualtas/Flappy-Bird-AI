import java.util.ArrayList;

import processing.core.PApplet;

public class Population {

	PApplet p;
	float bestScore = 0;
	int generation = 0;
	int frameCount = 0;
	
	ArrayList<Pipe> pipes = null;
	ArrayList<Bird> birds = new ArrayList<Bird>();
	ArrayList<Bird> savedBirds = new ArrayList<Bird>();
	
	public Population(PApplet p) {
		this.p = p;
		generatePopulation();
	}
	
	
	
	void display() {
		frameCount++;
		for(int i=0; i<birds.size(); i++) {
	    	birds.get(i).display();
			birds.get(i).update();
				    
			double output[];
			int closestPipe = pickClosestPipe();
			pipes.get(closestPipe).isClosestPipe = true;
			
			output = birds.get(i).makeDecision(pipes.get(closestPipe));
	  
	    	if(output[0] < output[1]) 
	    		birds.get(i).jump();
	    	if(pipes.get(closestPipe).isBirdHit(birds.get(i))) {
	    		birds.get(i).kill();
	    	}
    	}	
	}
	
	void update() {
		if(isAllBirdsDie()) {
			mutatePopulation();
		}	
	}
	
	
	public void setPipes(ArrayList<Pipe> pipes) {
		this.pipes = pipes;
	}
	
	private void generatePopulation() {
    	for(int i=0; i<Constants.POPULATION_SIZE; i++) {
    		birds.add(new Bird(p, Constants.gameHeight));
    	}
	}
    
    
    
	private void mutatePopulation() {
	  
	  generation++;
	  savedBirds = birds;
	  Bird bestBird = pickBestOne();
	  savedBirds.clear();
	  birds.clear();
	  
	  System.out.println("Generation: " + generation);
	  System.out.println("Score: " + bestScore);
	  frameCount = 0;
	  
	  for(int i=0; i<Constants.POPULATION_SIZE*0.8; i++) {
		  Bird child = new Bird(p, Constants.gameHeight/2);
		  child.brain = bestBird.brain.copy();
		  child.brain.mutate(0.1);
		  birds.add(child);		  
	  }
	  
	  
	  
	  for(int i=0; i<Constants.POPULATION_SIZE*0.2; i++) {
		  Bird best = new Bird(p, Constants.gameHeight/2);
		  best.brain = bestBird.brain.copy();
		  best.makeBest();
		  birds.add(best);	  
	  }
	  
  }
    
    private Bird pickBestOne() {
    	int bestScore = frameCount;
    	float distance = 1000000; //We can give any big number
    	Bird best = savedBirds.get(0);
		for (Bird bird : savedBirds) {
			if(bird.score >= bestScore) {
				if(bird.distancePivotPoint <= distance) {
					distance = bird.distancePivotPoint;
	    			best = bird;
				}
    		}
		}
		this.bestScore = bestScore;
    	return best;
    }
    
    private int pickClosestPipe() {
    	float close = 1000000000;
    	int pipeIndex = 0;
    	for (int i=0; i<pipes.size(); i++) {
    		
			if(Math.abs(pipes.get(i).x - Constants.BIRD_STAR_X) < close && pipes.get(i).x >= Constants.BIRD_STAR_X - Constants.PIPE_WIDTH - 5) {
				close = Math.abs(pipes.get(i).x - Constants.BIRD_STAR_X);
				pipeIndex = i;
			}
		}
    	return pipeIndex;
	}
    
    
    public boolean isAllBirdsDie() {
    	for (Bird bird : birds) {
			if(bird.isAlive)
				return false;
		}
		return true;
	}
    
    public int getGeneration() {
    	return generation;
    }
    
    public int getScore() {
    	return frameCount;
    }
   
	
	
	
	
	
	
	
	
}
