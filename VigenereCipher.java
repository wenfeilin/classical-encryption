import java.io.PrintWriter;

/**
 * An implementation of Vigenere cipher, which uses a keyword to shift a message when encrypting
 * and decrypting.
 * 
 * The message and the keyword must only contain lowercase alphabetical characters and no 
 * whitespace.
 *
 * @author Wenfei Lin
 */
public class VigenereCipher {
  public static void main (String[] args) throws Exception {
    PrintWriter errorPrinter = new PrintWriter(System.err, true);

    if (args.length != 3 && args.length != 2) {
      // When the number of command-line arguments is not 3 (assuming the keyword
      // is not an empty string) nor is it 2 (assuming the keyword is an empty string), 
      // then indicate the error and exit appropriately
      errorPrinter.println("Incorrect number of parameters"); 
      System.exit(2);
    } else if (!(args[0].equals("encode") || args[0].equals("decode"))) {
      // When the instruction is not "encode" or "decode," indicate the error and exit 
      // appropriately
      errorPrinter.println("Valid options are \"encode\" or \"decode\""); 
      System.exit(1);
    } 
    
    PrintWriter printer = new PrintWriter(System.out,  true);

    String instruction = args[0]; // tells program to encode or decode
    String message = args[1]; // tells program the message to encode or decode
    String keyword; // tells program the keyword to encode or decode
    
    // To cover the edge case where the keyword is an empty string
    if (args.length == 2) { 
      // Makes sure to set the keyword to an empty string because "" in the command-line is not 
      // considered an argument
      keyword = ""; 
    } else {
      // Proceed as usual if the keyword is not an empty string
      keyword = args[2];
    }

    if (instruction.equals("encode")) { // when the instruction is to encrypt
      displayEncodedMsg(printer, message, keyword);
    } else if (instruction.equals("decode")) { // when the instruction is to decrypt
      displayDecodedMsg(printer, message, keyword);
    }
  } // main(String[])

  /**
   * Prints the encrypted message.
   * Pre-conditions: message and keyword must only contain lowercase alphabetical characters 
   * and no whitespace (only keyword can be empty)
   * Post-conditions: prints encrypted message (only has lowercase alphabetical characters and
   * no whitespace)
   * The encrypted message can be the same as message if keyword was empty
   */   
  public static void displayEncodedMsg (PrintWriter printer, String message, String keyword) {
    if (keyword.equals("")) { // when the keyword is an empty string
      // Because the keyword is an empty string, the message will not change so print the original
      // message
      printer.println(message);
    } else { //when the keyword is not an empty string
      // Make a new keyword that repeats the keyword until the length of the new keyword matches 
      // the length of the message
      char[] newKeyword = createNewKeyword(message, keyword);
      // Create the encrypted message by adding the keyword and the original message together
      String encryptedMsg = createEncodedMsg(message, newKeyword);

      printer.println(encryptedMsg);
    }
  } // encode(PrintWriter, String, String)

  /**
   * Creates and returns the encrypted message from the original message. 
   * Pre-conditions: message and newKeyword must only contain lowercase alphabetical characters and
   * no whitespace (newKeyword cannot be empty)
   * Post-conditions: returns the encrypted message (only has lowercase alphabetical characters and
   * no whitespace)
   */ 
  public static String createEncodedMsg (String message, char[] newKeyword) {
    // Encrypted message should be the same length as the original message
    char[] encryptedMsg = new char[message.length()];

    // Loop for every character in the original message
    for (int index = 0; index < message.length(); index++) {
      // Convert the character in the message to its integer character code
      int msgCharCode = (int) message.charAt(index);
      // Convert the character in the new keyword at the same index to its integer character code
      int newKeyCharCode = (int) newKeyword[index];

      // Converts the encrypted character code to the correpsonding character and saves the new 
      // encrypted character in the encrypted message
      encryptedMsg[index] = (char) encryptCharCode(msgCharCode, newKeyCharCode);
    }

    return new String(encryptedMsg);
  } // createEncodedMsg(String, char[])

  /**
   * Computes and returns the character code of the encrypted character.
   * Pre-conditions: charCode and key must have (positive) ASCII codes between 97-122
   * Post-conditions: returns the encrypted character code (positive and between 97-122)
   */  
  public static int encryptCharCode (int charCode, int key) {
    // Rebase the original character code to 0-25
    int rebasedMsgCharCode = charCode - 97;
    // Rebase the keyword character code to 0-25
    int rebasedKeyCharCode = key - 97;
    // Add the key's character code to the rebased message character code and handle the 
    // "wrap-around" for whenever that sum is greater than 25
    int newCharCode = (rebasedMsgCharCode + rebasedKeyCharCode) % 26;

    return newCharCode + 97;
  } // encryptCharCode(int, int)

  /**
   * Creates and returns the new keyword, which is the keyword repeated over the length of the 
   * original message.
   * Pre-conditions: message and newKeyword must only contain lowercase alphabetical characters and
   * no whitespace (newKeyword cannot be empty)
   * Post-conditions: returns the newKeyword (only has the lowercase alphabetic keyword repeated
   * in it and no whitespace)
   */ 
  public static char[] createNewKeyword (String message, String keyword) {
    // Create the new keyword to hold the length of message characters
    char[] newKeyword = new char[message.length()]; 
    int keywordLength = keyword.length();
    int keywordIndex = 0;

    // Loop through the new keyword's characters for as long as the length of message
    for (int index = 0; index < message.length(); index++) {
      // If it comes to the point where the keyword starts repeating
      if (keywordIndex >= keywordLength) {
        // go back to the first character of the original keyword, then make that character the 
        // next character in the new keyword
        keywordIndex = 0;
      }

      // Save the character from the original keyword in the new keyword
      newKeyword[index] = keyword.charAt(keywordIndex);
      // Go to the next character in the new keyword
      keywordIndex++;
    } 

    return newKeyword;
  } // createNewKeyword(String, String)

  /**
   * Prints the decrypted message. 
   * Pre-conditions: message and keyword must only contain lowercase alphabetical characters 
   * and no whitespace (only keyword can be empty)
   * Post-conditions: prints decrypted message (only has lowercase alphabetical characters and
   * no whitespace)
   * The decrypted message can be the same as message if keyword was empty
   */ 
  public static void displayDecodedMsg (PrintWriter printer, String message, String keyword) {
    if (keyword.equals("")) { // when the keyword is an empty string
      // Because the keyword is an empty string, the message will not change so print the original
      // message
      printer.println(message);
    } else { // when the keyword is not an empty string
      // Make a new keyword that repeats the keyword until the length of the new keyword matches 
      // the length of the message
      char[] newKeyword = createNewKeyword(message, keyword);
      // Create the encrypted message by adding the keyword and the original message together
      String decryptedMsg = createDecodedMsg(message, newKeyword);

      printer.println(decryptedMsg);
    }
  } // decode (PrintWriter, String, String)

  /**
   * Creates the decrypted message from the original message. 
   * Pre-conditions: message and newKeyword must only contain lowercase alphabetical characters and
   * no whitespace (newKeyword cannot be empty)
   * Post-conditions: returns the decrypted message (only has lowercase alphabetical characters and
   * no whitespace)
   */ 
  public static String createDecodedMsg (String message, char[] newKeyword) {
    // Decrypted message should be the same length as the original message
    char[] decryptedMsg = new char[message.length()];

    // Loop for every character in the original message
    for (int index = 0; index < message.length(); index++) {
      // Convert the character in the message to its integer character code
      int msgCharCode = (int) message.charAt(index);
      // Convert the character in the new keyword at the same index to its integer character code
      int newKeyCharCode = (int) newKeyword[index];

      // Converts the decrypted character code to the correpsonding character and saves the new 
      // decrypted character in the decrypted message
      decryptedMsg[index] = (char) decryptCharCode(msgCharCode, newKeyCharCode);
    }
    return new String(decryptedMsg);
  } // createDecodedMsg (String, char[])

  /**
   * Computes and returns the character code of the decrypted character. 
   * Pre-conditions: charCode and key must have (positive) ASCII codes between 97-122
   * Post-conditions: returns the decrypted character code (positive and between 97-122)
   */  
  public static int decryptCharCode (int charCode, int key) {
    // Rebase the original character code to 0-25
    int rebasedMsgCharCode = charCode - 97;
    // Rebase the keyword character code to 0-25
    int rebasedKeyCharCode = key - 97;
    // Subtract the key's character code to the rebased message character code
    int newCharCode = (rebasedMsgCharCode - rebasedKeyCharCode);

    // If a "wrap-around" is needed, meaning the new character code is less than 0, 
    if (newCharCode < 0) {
      // then add 26 to the new character code, which will "connect" the 0 end of the range to the
      // 25 end of the range so the chracter code won't be negative
      newCharCode += 26;
    }

    // Convert back to the ASCII code range for lowercase alphabetic letters (97-122)
    return newCharCode + 97;
  } // decryptCharCode(int, int)
} // class VigenereCipher