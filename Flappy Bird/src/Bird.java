import java.util.ArrayList;

import basicneuralnetwork.NeuralNetwork;
import processing.core.PApplet;
public class Bird {
	
	PApplet p;
	float x = 280;
	float y = 0;
	float r = 12;
	float distancePivotPoint = 0;
	float velocity = 0;
	float gravity = (float) 0.08;
	float acceleration = (float) -3;
	NeuralNetwork brain = null;
	boolean isAlive = true;
	int score = 0;
	Pipe closesetPipe = null;
	boolean isBest = false;
	
	
	public Bird(PApplet p, float y) {
		this.p = p;
		this.y = y;
		
		if(brain == null)
		brain = new NeuralNetwork(7,14,2);
		
		
	}

	
	public double[] makeDecision(Pipe closestPipe) {
		this.closesetPipe = closestPipe;
		
		double[] inputs = new double[7];
		//Distance bird between closest pipe
		inputs[0] = closestPipe.x - (this.x +this.r);
		//Closest pipe top of open
		inputs[1] = closestPipe.openTop;
		//Closest pipe bottom of open
		inputs[2] = closestPipe.openBottom ;
		//Y coordinate of bird
		inputs[3] = this.y;
		//Velocity
		inputs[4] = this.velocity;
		//Distance to pivot piont of closest pipe
		inputs[5] = distancePivotPoint;
		//End of closest pipe
		inputs[6] = closestPipe.y + closestPipe.width;
		
		//We will get two output
		double output[] = brain.guess(inputs);
		return output;	
	}
	
	
	
	
	void display() {
		if(isAlive) {
			score++;
			if(isBest) {
				p.push();
				p.fill(0,150, 0);
				p.ellipse(x, y, 2*r, 2*r);
				p.pop();
			} else {
				p.ellipse(x, y, 2*r, 2*r);					
			}
				
			
			
		}
			
		
	}
	
	
	void update() {
		velocity += gravity;
		this.y += velocity;
		calculateDistanceToPivot();
		isBirdHitTopOrBottomOfScreen();
	}
	
	
	public void jump() {
		this.velocity = 0;
		this.velocity += this.acceleration;
	}
	
	public void kill() {
		this.isAlive = false;
	}
	
	public void makeBest() {
		this.isBest = true;
	}
	
	private void calculateDistanceToPivot() {
		if(this.closesetPipe != null) {
			float distance = (float) (Math.pow((closesetPipe.x - this.x),2) + Math.pow(Math.abs((closesetPipe.openTop + closesetPipe.space/2) - this.y), 2));
			distance = (float) Math.sqrt(distance);
			this.distancePivotPoint = distance;
		}
		
	}
	
	
	private void isBirdHitTopOrBottomOfScreen() {
		if(this.y - this.r / 2  < 0) 
			kill();
		if(this.y + this.r / 2 > Constants.gameHeight){
			kill();
		}
	}
	
	
	
	
	
	
	
}
