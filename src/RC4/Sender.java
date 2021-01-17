package RC4;

import java.security.SecureRandom;

public class Sender extends Endpoint {
	private final int keylength;
	
	public Sender(byte[] seed, int keylength) {
		super(seed);
		this.keylength = keylength;
	}
	
	public Sender(byte[] seed) {
		super(seed);
		this.keylength = 40;
	}

	public byte[] encryptMessage(byte[] plaintext) {
		SecureRandom random = this.getRandom();
		byte[] key = new byte[keylength];
		random.nextBytes(key);
		RC4 rc4 = new RC4(key);
		byte[] ciphertext = rc4.encrypt(plaintext);
		return ciphertext;
	}
}
