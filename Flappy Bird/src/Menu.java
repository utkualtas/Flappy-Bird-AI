import processing.core.PApplet;

public class Menu {
	
	PApplet p;
	int generation = 0;
	int score = 0;
	public Menu(PApplet p) {
		this.p = p;
	}
	
	
	
	
	public void display() {
		p.push();
		p.fill(255);
		p.translate(0, Constants.gameHeight);
		p.rect(0, 0, p.width, p.height - Constants.gameHeight);
		p.fill(0);
		p.text("Generation: " + generation, 10, 20);
		p.text("Score: " + score, 10, 40);
		
		p.text("Press T for train with no draw. (You can toggle it)", 200, 20);
		p.pop();
	}
	
	public void setGeneration(int generation) {
		this.generation= generation;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
