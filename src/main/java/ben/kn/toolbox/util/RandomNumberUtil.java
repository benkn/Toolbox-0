package ben.kn.toolbox.util;

import java.util.Random;

public class RandomNumberUtil {
	private Random ranGen = new Random();

	public int generateRandomBetween(int min, int max) {
		int randomNumber = ranGen.nextInt(max - min + 1);
		randomNumber += min;
		return randomNumber;
	}
}