import java.util.ArrayList;

import processing.core.PApplet;

public class Pipes {
	PApplet p;
	
	ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	
	
	public Pipes(PApplet p) {
		this.p = p;
		generatePipes();
	}
	
	void display() {
		for(Pipe pipe : pipes) {
    		pipe.display();
        	pipe.update();
    	}
		
		//Check if the first pipe is off the screen then add a new pipe 
    	if(pipes.get(0).isOffScreen()) {
    		pipes.add(new Pipe(p, pipes.get(pipes.size()-1).x + Constants.EACH_PIPE_SPACE));
    		pipes.remove(0);
    	}
	}
	
	public void reset() {
		pipes.clear();
		generatePipes();
	}
	
	
	public ArrayList<Pipe> getPipes(){
		return this.pipes;		
	}
	
	public void generatePipes() {
    	float sum = Constants.FIRST_PIPE_X;
    	for(int i=0; i<calculateToNeededPipeCount(); i++) {
    		pipes.add(new Pipe(p, sum));
    		sum += Constants.EACH_PIPE_SPACE;
    	}
    }
    
    private int calculateToNeededPipeCount() {
    	return (int) ( (p.width + Constants.EACH_PIPE_SPACE)  / (Constants.EACH_PIPE_SPACE) ) +1 ;
    }
	
}
