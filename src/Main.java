import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

import RC4.Sender;
import RC4.Receiver;


public class Main {
	
	public static String ba2str(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	        sb.append(String.format("%02X ", b));
	    }
	    return sb.toString();
	}
	
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
				System.out.println("msg (bytes): " + ba2str(msg.getBytes()));
				// encrypt msg
				ciphertext = sender.encryptMessage(msg.getBytes());
				System.out.println("ciphertext: " + ba2str(ciphertext));
				
				System.out.println("*** decrypt the ciphertext ***");
				
				// decrypt received msg
				plaintext = receiver.decryptMessage(ciphertext);
				System.out.println("plaintext (bytes): " + ba2str(plaintext));
				receivedMsg = new String(plaintext);
				System.out.println("received msg: " + receivedMsg);
			}
		} catch (Exception e) {
			System.err.print(e.toString());
			System.exit(1);
		}
		
		System.out.println("*** goodbye! ***");
		
	}
}
