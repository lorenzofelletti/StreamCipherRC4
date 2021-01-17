package RC4;

public class RC4 {
	private final byte[] S = new byte[256];
	private final byte[] T = new byte[256];
	private final int keylen;
	
	public RC4(final byte[] key) {
		if (key.length < 1 || key.length > 256) {
			throw new IllegalArgumentException("Key mus be between 1 and 256 bytes long.");
		}
		keylen = key.length;
		for (int i = 0; i < 256; i++) {
			S[i] = (byte) i;
			T[i] = key[i % keylen];
		}
		int j = 0;
		for (int i = 0; i < 256; i++) {
			j = (j + S[i] + T[i]) & 0xFF;
			S[i] ^= S[j];
			S[j] ^= S[i];
			S[i] ^= S[j];
		}
	}
	
	public byte[] encrypt(final byte[] plaintext) {
		final byte[] ciphertext = new byte[plaintext.length];
		int i = 0, j = 0, k, t;
		for (int ctr = 0; ctr < plaintext.length; ctr++) {
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			S[i] ^= S[j];
			S[j] ^= S[i];
			S[i] ^= S[j];
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			ciphertext[ctr] = (byte) (plaintext[ctr] ^ k);
		}
		return ciphertext;
	}
	
	public byte[] decrypt(final byte[] ciphertext) {
		return encrypt(ciphertext);
	}
}
