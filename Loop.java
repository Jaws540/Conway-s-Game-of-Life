package game;

public class Loop extends Thread {
	
	private int ticks = 0;
	private int frames = 0;
	
	public void run(){
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		
		while(true){
			double nsPerTick = 1000000000D / Game.getTicksPerSecond();
			
			Game.render();
			frames++;
			
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / nsPerTick;
			lastTime = currentTime;
			
			while(delta >= 1){
				if(Game.isRunning()){
					Game.tick();
				}
				ticks++;
				delta -= 1;
			}
			
			if(RES.PERSECOND && System.currentTimeMillis() - timer >= 1000){
				if(Game.isRunning()){
					System.out.println("Ticks: " + ticks + ", Frames: " + frames + ", Population: " + Game.getPopulation() + ", Generation: " + Game.getGeneration());
				}else{
					System.out.println("Game not running, Frames: " + frames + ", Population: " + Game.getPopulation() + ", Generation: " + Game.getGeneration());
				}
				timer += 1000;
				ticks = 0;
				frames = 0;
			}
		}
	}

}
