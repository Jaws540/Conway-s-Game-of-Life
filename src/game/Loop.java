package game;

public class Loop extends Thread {
	
	private int ticks = 0;
	private int frames = 0;
	
	public void run(){
		while(true){
			long lastTime = System.nanoTime();
			long lastTimer = System.currentTimeMillis();
			double ns = 1000000000D / Game.getTicksPerSecond();
			double delta = 0;
			
			while(Game.isRunning()){
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				
				while(delta >= 1){
					Game.tick();
					ticks++;
					delta -= 1;
				}
				
				Game.render();
				frames++;
				
				if(System.currentTimeMillis() - lastTimer >= 1000){
					System.out.println("Ticks: " + ticks + " Frames: " + frames);
					lastTimer += 1000;
					ticks = 0;
					frames = 0;
				}
			}
		}
	}

}
