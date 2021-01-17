package RC4;

import java.security.SecureRandom;

public class Endpoint {
	private final byte[] seed;
	private SecureRandom random;
	
	public Endpoint(byte[] seed) {
		super();
		this.seed = seed;
		this.random = new SecureRandom(this.seed);
	}

	protected SecureRandom getRandom() {
		return random;
	}
}
