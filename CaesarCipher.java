public class CaesarCipher {
    public static void main (String[] args) throws Exception {
        String instruction = args[0];
        String message = args[1];

        java.io.PrintWriter printer;
        printer = new java.io.PrintWriter(System.out, true);

        if (instruction.equals("encode")) {
            displayAllEncodes(printer, message);
        } else if (instruction.equals("decode")) {
            displayAllDecodes(printer, message);
        }
    } // main(String[])

    public static String encode (String message, int key) {
        char[] messageArr = message.toCharArray();

        for (int index = 0; index < messageArr.length; index++) {
            int charCode = (int) messageArr[index];

            char encryptedChar = (char) encryptedCharCode(charCode, key);
            messageArr[index] = encryptedChar;
        }
        return new String(messageArr);
    } // encode(String, int)

    public static int encryptedCharCode (int charCode, int key) {
        int rebasedCharCode = charCode - 97;
        int newCharCode = (rebasedCharCode + key) % 26;
        return newCharCode + 97;
    } // encryptedCharCode(int, int)

    public static void displayAllEncodes (java.io.PrintWriter printer, String message) {
        int numOfAlphabets = 26;
        for(int key = 0; key < numOfAlphabets; key++) {
            String newMessage = encode(message, key);
            printer.println("n = " + key + ": " + newMessage); //could also use printf
        }
    } // displayAllEncodes(java.io.PrintWriter, String)

    public static String decode (String message, int key) {
        char[] messageArr = message.toCharArray();

        for (int index = 0; index < messageArr.length; index++) {
            int charCode = (int) messageArr[index];

            char decryptedChar = (char) decryptedCharCode(charCode, key);
            messageArr[index] = decryptedChar;
        }
        return new String(messageArr);
    } // decode(String, int)

    public static int decryptedCharCode (int charCode, int key) {
        int rebasedCharCode = charCode - 97;
        int newCharCode = (rebasedCharCode - key);
        if (newCharCode < 0) {
             newCharCode += 26;
        }
        return newCharCode + 97;
    }

    public static void displayAllDecodes (java.io.PrintWriter printer, String message) {
        int numOfAlphabets = 26;
        for(int key = 0; key < numOfAlphabets; key++) {
            String newMessage = decode(message, key);
            printer.println("n = " + key + ": " + newMessage); //could also use printf
        }
    }
} // class CaesarCipher