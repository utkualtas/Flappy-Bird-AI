import processing.core.PApplet;


public class Pipe {
	
	PApplet p;
	float height = 800;
	float width = Constants.PIPE_WIDTH;
	float space = 100;
	float x;
	float y;
	float openTop;
	float openBottom;
	float pivotSpace = 50;
	boolean isClosestPipe = false;
	
	
	
	
	
	public Pipe(PApplet parent, float x) {
		this.p = parent;
		this.x = x;
		openTop = p.random(space, Constants.gameHeight - (pivotSpace +space)) ;
		openBottom = openTop + space;		
	}
	
	
	void display() {
		if(isClosestPipe) {
			p.push();
			p.fill(0,200, 0);
			p.rect(x, 0, width, openTop);
			p.rect(x, openBottom, width, Constants.gameHeight - (openTop+space));
			p.pop();
			isClosestPipe = false;
		} else {
			p.rect(x, 0, width, openTop);
			p.rect(x, openBottom, width, Constants.gameHeight - (openTop+space));
		}
			
		
		
	}
	
	
	void update() {
		x --;	
	}
	
	
	public boolean isOffScreen() {
		if((x + width) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBirdHit(Bird bird) {
		
		if(bird.x  >= this.x && bird.x < this.x + width) {
			
			if(bird.y - bird.r < openTop || bird.y + bird.r > openBottom) {
				return true;
			}
			
		}
		
		return false;
	}
	
	

}
