Changes made to CaesarCipher.java:
- combined encryptCharCode together with encode and combined decryptCharCode together with decode (the lines are so little the combination of them shouldn't matter too much)
- rewrote displayAllDecodes and displayAllEncodes as one method, displayAll to reduce repeated code
- defined magic numbers in variables instead
- made some other smaller edits to account for the above changes made

Changes made to VignereCipher.java:
- rewrote createDecodedMsg and createEncodedMsg as one method, createNewMsg
- rewrote displayEncodedMsg and displayDecodedMsg as one method, displayNewMsg
- defined magic numbers in variables instead
- made some other smaller edits to account for the above changes made