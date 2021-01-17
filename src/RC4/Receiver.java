package RC4;

import java.security.SecureRandom;

public class Receiver extends Endpoint {
	private final int keylength;
	
	public Receiver(byte[] seed, int keylength) {
		super(seed);
		this.keylength = keylength;
	}
	
	public Receiver(byte[] seed) {
		super(seed);
		this.keylength = 40;
	}
	
	public byte[] decryptMessage(byte[] ciphertext) {
		SecureRandom random = this.getRandom();
		byte[] key = new byte[keylength];
		random.nextBytes(key);
		RC4 rc4 = new RC4(key);
		byte[] plaintext = rc4.decrypt(ciphertext);
		return plaintext;
	}
}
