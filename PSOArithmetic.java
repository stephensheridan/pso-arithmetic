public class PSOArithmetic{
	
	// Set the TARGET for our arithmetic problem A + B + C + D = TARGET
	private static final int TARGET = 50;
	// Set the maximum number of generations for main loop
	private static final int MAX_GENERATIONS = 100;
	// Set the number of particles in our swarm
	private static final int NUM_PARTICLES = 20;
        
    // The particles will be initialized with data randomly chosen within the range
    // of these starting min and max values: 
    private static final int START_RANGE_MIN = 140;
    private static final int START_RANGE_MAX = 190;

	// Create a particle array
    private static Particle[] p;
	
    // Store global best and its index
	private static int gBest;
	private static int gBestIndex;
	
	public static void main(String[] args)
	{
		// Create our Particle Swarm and initialise each particle
		p = new Particle[NUM_PARTICLES];
		for (int i = 0; i < NUM_PARTICLES; i++){
			p[i] = new Particle(TARGET, START_RANGE_MIN, START_RANGE_MAX);
		}
				
		int generation = 0; 			// Count the number of generations for loop
		gBest = START_RANGE_MAX * 4; 	// Pick a worst case to start with
		gBestIndex = 0; 				// First particle to start with
		boolean converged = false; 		// Not converged to begin with
		
		// Lets Swarm...
		// NOTE: multiple inner loops used to make stages of algorithm easier to understand
		while (generation < MAX_GENERATIONS && !converged){
			
			// Calculate the fitness for each particle and check for convergence
			for(int i = 0; i < NUM_PARTICLES; i++){
				p[i].calculateFitness();
				converged = converged || (p[i].fitness == 0);
			}
			
			// Do some system output
			System.out.println("Generation " + generation);
			for(int i = 0; i < NUM_PARTICLES; i++){
				p[i].display();
			}
			
			// Keep track of the global best
			for(int i = 0; i < NUM_PARTICLES; i++){
				if (p[i].pBest < gBest){
					gBest = p[i].pBest;
					gBestIndex = i;
				}
			}
			
			// Update each particle based on Eberhart's equation
			for(int i = 0; i < NUM_PARTICLES; i++){
				// Don't change the global best
				if (i != gBestIndex)
					p[i].update(p[gBestIndex]);
			}
			generation++;
		}
	}
}