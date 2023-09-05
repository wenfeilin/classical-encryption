public class VigenereCipher {
    public static void main (String[] args) throws Exception {
        java.io.PrintWriter printer;
        printer = new java.io.PrintWriter(System.out, true);

        String instruction = args[0];
        String message = args[1];
        String keyword = args[2];

        if (instruction.equals("encode")) {
            printer.println(encode(message, keyword));
        } /*else if (instruction.equals("decode")) {
            decode(printer, message);
        }*/

    } // main(String[])

    public static String encode (String message, String keyword) {
        char[] newKeyword = createNewKeyword(message, keyword);
        //could make it so that it adds the char values directly 
             //instead of making a new keyword that is as long as  
             //the msg string

        //lines abt adding new key to msg:
        char[] encryptedMsg = createEncodedMsg(message, newKeyword);

        return new String(encryptedMsg);
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
    }

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
    }
} // class VigenereCipher
