import java.io.PrintWriter;

/**
 * An implementation of Caesar cipher, which uses a key, a number, to shift a message when 
 * encrypting and decrypting
 * 
 * The message must only contain lowercase alphabetical characters and no whitespace.
 *
 * @author Wenfei Lin
 */
public class CaesarCipher {
  public static void main (String[] args) throws Exception {
    PrintWriter errorPrinter = new PrintWriter(System.err, true);

    if (args.length != 2) { 
      // When the number of parameters on the command-line is not 2, indicate the error and exit 
      // appropriately
      errorPrinter.println("Incorrect number of parameters"); 
      System.exit(2);
    } else if (!(args[0].equals("encode") || args[0].equals("decode"))) {
      // When the instruction is not "encode" or "decode," indicate the error and exit 
      // appropriately
      errorPrinter.println("Valid options are \"encode\" or \"decode\""); 
      System.exit(1); 
    } 

    String instruction = args[0]; // tells program to encode or decode
    String message = args[1]; // tells program the message to encode or decode

    PrintWriter printer = new PrintWriter(System.out, true);

    if (instruction.equals("encode")) { // when the instruction is to encrypt
      displayAll(printer, message, "encode");
    } else if (instruction.equals("decode")) { // when the instruction is to decrypt
      displayAll(printer, message, "decode");
    }
  } // main(String[])

  /**
   * Creates and returns the encrypted message from the original message. 
   * Pre-conditions: message must only contain lowercase alphabetical characters and no whitespace
   * and key must be a positive integer from 0 to 25
   * Post-conditions: returns the encrypted message (only has lowercase alphabetical characters and
   * no whitespace)
   * The encrypted message can be the same as message if key is 0
   */  
  public static String encode (String message, int key) {
    char[] messageArr = message.toCharArray();
    int asciiValOfa = 97;
    int numOfAlphabets = 26;

    // Loops through every character in the message
    for (int index = 0; index < messageArr.length; index++) {
      int charCode = (int) messageArr[index];

      // Rebase the original character code to 0-25
      int rebasedCharCode = charCode - asciiValOfa;
      // Add the key's character code to the rebased message character code and handle the 
      // "wrap-around" for whenever that sum is greater than 25
      int newCharCode = (rebasedCharCode + key) % numOfAlphabets;
      // Convert back to the ASCII code range for lowercase alphabetic letters (97-122) 
      // and add to message
      messageArr[index] = (char) (newCharCode + asciiValOfa);
    }

    return new String(messageArr);
  } // encode(String, int)

  /**
   * Creates and returns the decrypted message. 
   * Pre-conditions: message must only contain lowercase alphabetical characters and no whitespace
   * and key must be a positive integer from 0 to 25
   * Post-conditions: returns the decrypted message (only has lowercase alphabetical characters 
   * and no whitespace)
   * The decrypted message can be the same as message if key is 0
   */ 
  public static String decode (String message, int key) {
    char[] messageArr = message.toCharArray();
    int asciiValOfa = 97;
    int numOfAlphabets = 26;

    // Loops through every character in the message
    for (int index = 0; index < messageArr.length; index++) {
      int charCode = (int) messageArr[index];

      // Rebase the original character code to 0-25
      int rebasedCharCode = charCode - asciiValOfa;
      // Subtract the key's character code from the rebased message character code 
      int newCharCode = (rebasedCharCode - key);

      // If a "wrap-around" is needed, meaning the new character code is less than 0, 
      if (newCharCode < 0) {
        // then add 26 to the new character code, which will "connect" the 0 end of the range to the
        // 25 end of the range so the chracter code won't be negative
        newCharCode += numOfAlphabets;
      }

      // Convert back to the ASCII code range for lowercase alphabetic letters (97-122)
      messageArr[index] = (char) (newCharCode + asciiValOfa);
    }
    
    return new String(messageArr);
  } // decode(String, int)

  /**
   * Prints all 26 possible encryptions or decryptions of the message.
   * Pre-conditions: message must only contain lowercase alphabetical characters and no whitespace
   * Post-conditions: prints each message's encryption or decryption and key for all 26 keys
   * (Message only has lowercase alphabetical characters and no whitespace)
   */ 
  public static void displayAll (PrintWriter printer, String message, String instruction) {
    int numOfAlphabets = 26;

    if (instruction.equals("encode")) {
      // Loop for all 26 possible lowercase alphabetic keys (a-z)
      for (int key = 0; key < numOfAlphabets; key++) {
        // Compute the encrypted message for the key
        String newMessage = encode(message, key);
        // Print the encrypted message along with its corresponding key
        printer.println("n = " + key + ": " + newMessage); 
      }
    } else {
      // Loop for all 26 possible lowercase alphabetic keys (a-z)
      for (int key = 0; key < numOfAlphabets; key++) {
        // Compute the decrypted message for the key
        String newMessage = decode(message, key);
        // Print the decrypted message along with its corresponding key
        printer.println("n = " + key + ": " + newMessage); 
      }
    }
  } // displayAllEncodes(PrintWriter, String, String)
} // class CaesarCipher