package ben.kn.toolbox.services;

public class MathService {
	/**
	 * Find the least common multiplier for the given A and B
	 * 
	 * @param a int
	 * @param b int
	 * @return int
	 */
	public int getLCM(int a, int b) {
		int max = Math.max(a, b);
		int counter = 2;
		int x = max;
		// check if x is divisible evenly by a and b
		while (!(x % a == 0 && x % b == 0)) {
			// if this wasn't the case, then increase x by itself, since x is
			// the largest between a and b
			x = max * counter;
			counter++;
		}

		return x;
	}
}
