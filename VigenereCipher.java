public class VigenereCipher {
    public static void main (String[] args) throws Exception {
        java.io.PrintWriter errorPrinter;
        errorPrinter = new java.io.PrintWriter(System.err, true);

        if (args.length != 3 && args.length != 2) {
            errorPrinter.println("Incorrect number of parameters"); 
            System.exit(2);
        } else if (!(args[0].equals("encode") || args[0].equals("decode"))) {
            errorPrinter.println("Valid options are \"encode\" or \"decode\""); 
            System.exit(1);
        } else if (args.length == 2) { // to cover edge case: keyword is an empty string
            errorPrinter.println(args[1]);
            System.exit(3);
        }
        
        //refactor everything later
        
        java.io.PrintWriter printer;
        printer = new java.io.PrintWriter(System.out,  true);

        String instruction = args[0];
        String message = args[1];
        String keyword = args[2];

        if (instruction.equals("encode")) {
            encode(printer, message, keyword);
        } else if (instruction.equals("decode")) {
            decode(printer, message, keyword);
        }
    } // main(String[])

    public static void encode (java.io.PrintWriter printer, String message, String keyword) {
        char[] newKeyword = createNewKeyword(message, keyword);
        //could make it so that it adds the char values directly 
             //instead of making a new keyword that is as long as  
             //the msg string

        //lines abt adding new key to msg:
        char[] encryptedMsg = createEncodedMsg(message, newKeyword);

        printer.println(encryptedMsg);
    } // encode(String, String)

    public static int encryptedCharCode (int charCode, int key) {
        int rebasedMsgCharCode = charCode - 97;
        int rebasedKeyCharCode = key - 97;
        int newCharCode = (rebasedMsgCharCode + rebasedKeyCharCode) % 26;
        return newCharCode + 97;
    } // encryptedCharCode(int, int)

    public static char[] createEncodedMsg (String message, char[] newKeyword) {
        char[] encryptedMsg = new char[message.length()];
        for (int index = 0; index < message.length(); index++) {
            int msgCharCode = (int) message.charAt(index);
            int newKeyCharCode = (int)newKeyword[index];
            encryptedMsg[index] = (char) encryptedCharCode(msgCharCode, newKeyCharCode);
        }
        return encryptedMsg;
    } // createEncodedMsg(String, char[])

    public static char[] createNewKeyword (String message, String keyword) {
        char[] newKeyword = new char[message.length()]; 
        int keywordLength = keyword.length();
        int keywordIndex = 0;

        for (int index = 0; index < message.length(); index++) {
            if (keywordIndex >= keywordLength) {
                keywordIndex = 0;
            }
            newKeyword[index] = keyword.charAt(keywordIndex);
            keywordIndex++;
        } 
        return newKeyword;
    } // createNewKeyword(String, String)

    public static void decode (java.io.PrintWriter printer, String message, String keyword) {
        char[] newKeyword = createNewKeyword(message, keyword);
        
        char[] decryptedMsg = createDecodedMsg(message, newKeyword);

        printer.println(decryptedMsg);
    } // decode (java.io.PrintWriter, String, String)

    public static int decryptedCharCode (int charCode, int key) {
        int rebasedMsgCharCode = charCode - 97;
        int rebasedKeyCharCode = key - 97;
        int newCharCode = (rebasedMsgCharCode - rebasedKeyCharCode);
        if (newCharCode < 0) {
             newCharCode += 26;
        }
        return newCharCode + 97;
    } // decryptedCharCode(int, int)

    public static char[] createDecodedMsg (String message, char[] newKeyword) {
        char[] decryptedMsg = new char[message.length()];
        for (int index = 0; index < message.length(); index++) {
            int msgCharCode = (int) message.charAt(index);
            int newKeyCharCode = (int)newKeyword[index];
            decryptedMsg[index] = (char) decryptedCharCode(msgCharCode, newKeyCharCode);
        }
        return decryptedMsg;
    } // createDecodedMsg (String, char[])
} // class VigenereCipher
