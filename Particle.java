public class Particle{
	private static final int V_MAX = 10;             // Maximum velocity change allowed.
	int A, B, C, D;
	int TARGET;
	int pBest;
	int fitness;
	float velocity;
	
	Particle(int TARGET, int START_RANGE_MIN, int START_RANGE_MAX){
		this.TARGET = TARGET;
		initialise(START_RANGE_MIN, START_RANGE_MAX);
	}
	public void initialise(int low, int high){
		// Randomise each variable with low-high range
		A = getRandomNumber(low, high);
		B = getRandomNumber(low, high);
		C = getRandomNumber(low, high);
		D = getRandomNumber(low, high);
		// Set pBest for this particle to current state
		pBest = Math.abs((A + B + C + D) - TARGET);		
	}
	
	public void calculateFitness()
	{
		// Use the absolute difference between current result and target
		fitness = Math.abs((A + B + C + D) - TARGET);
		// Check if current fitness is better then pBest and store
		if (fitness < pBest){
			pBest = fitness;
		}
	}
	public void update(Particle gBest){
		// Get the current answer for this particle
		int testResult = A + B + C + D;
		// Get the global best
		int bestResult = gBest.pBest;
		// Calculate a change in velocity based on Eberhart's equation
		velocity = (float)(velocity + 2 * Math.random() * (pBest - testResult) + 2 * Math.random() * (bestResult - testResult));
		
		// Keep velocity between -V_MAX and +V_MAX
		if(velocity > V_MAX)
        	velocity = V_MAX;
        else if(velocity < -V_MAX)
            velocity = -V_MAX;
        
		// Update each variable with new velocity
		A = A + (int)velocity;
		B = B + (int)velocity;
		C = C + (int)velocity;
		D = D + (int)velocity;
	}
	// Display this particles variables and fitness
	public void display(){
		System.out.println(A + "+" + B + "+" + C + "+" + D + "=" + (A+B+C+D) + " Fitness = " + fitness);
	}
	// Used internally to generate some random numbers between low and high range
	private static int getRandomNumber(int low, int high){
        return (int)((high - low) * Math.random() + low);
    }
}