import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

import RC4.Sender;
import RC4.Receiver;


public class Main {
	public static void main(String[] args) {
		// generate the seed shared by the sender and the receiver
		// they will use this seed to sync on the key,
		// automatically changed after each message.
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);
		
		Sender sender = new Sender(seed);
		Receiver receiver = new Receiver(seed);
		
		System.out.println("*** RC4 ***");
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String msg, receivedMsg;
			byte[] ciphertext, plaintext;
			
			while(true) {
				// msg to send
				System.out.println();
				System.out.println("Enter msg (empty to stop):");
				msg = reader.readLine();
				// exit if msg is empty
				if(msg.length() == 0)
					break;
				
				// msg string in bytes
				System.out.println("msg (bytes): " + msg);
				// encrypt msg
				ciphertext = sender.encryptMessage(msg.getBytes());
				System.out.println("ciphertext: " + ciphertext);
				
				System.out.println("*** decrypt the ciphertext ***");
				
				// decrypt received msg
				plaintext = receiver.decryptMessage(ciphertext);
				System.out.println("plaintext (bytes): " + plaintext);
				receivedMsg = new String(plaintext, StandardCharsets.UTF_8);
				System.out.println("received msg: " + receivedMsg);
			}
		} catch (Exception e) {
			System.err.print(e.toString());
			System.exit(1);
		}
		
		System.out.println("*** goodbye! ***");
		
	}
}
