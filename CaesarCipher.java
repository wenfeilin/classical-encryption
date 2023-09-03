public class CaesarCipher {
    public static void main (String[] args) throws Exception {
        //String instruction = args[0];
        String message = args[1];

        String newMessage = encode(message, 2);

        java.io.PrintWriter printer;
        printer = new java.io.PrintWriter(System.out, true);
        printer.println("key: 2 " + newMessage);
        
    } // main(String[])

    public static String encode (String message, int key) {
        char[] messageArr= message.toCharArray();

        for (int index = 0; index < messageArr.length; index++) {
            int charCode = (int) messageArr[index];

            char encryptedChar = (char) encryptedCharCode(charCode, key);
            messageArr[index] = encryptedChar;
        }
        return new String(messageArr);
    }

    public static int encryptedCharCode (int charCode, int key) {
        int rebasedCharCode = charCode - 97;
        int newCharCode = (rebasedCharCode + key) % 26;
        return newCharCode + 97;
    }

} // class CaesarCipher