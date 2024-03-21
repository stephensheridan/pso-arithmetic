public class TryRandom{
	static final int EPOCHS = 100000;

	public static boolean calculateFitness(int a, int b, int c, int d, int target)
	{
		// Use the absolute difference between current result and target
		int fitness = Math.abs((a + b + c + d) - target);
		return fitness == 0;
	}
	public static void main(String[] args) {
		for(int i = 0; i < EPOCHS; i++){
			int a = (int)(Math.random() * 201);
			int b = (int)(Math.random() * 201);
			int c = (int)(Math.random() * 201);
			int d = (int)(Math.random() * 201);
			if (calculateFitness(a,b,c,d,50)){
				System.out.printf("%d + %d + %d + %d = %d\n", a,b,c,d,(a+b+c+d));
				break;
			}

		}
	}
}