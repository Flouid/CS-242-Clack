package data;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is the basic building block for a unit of data in the Clack application.
 *
 * @author Louis Keith
 */

public abstract class ClackData implements Serializable {

    // constant declarations
    public final static int CONSTANT_LISTUSERS = 0;
    public final static int CONSTANT_LOGOUT = 1;
    public final static int CONSTANT_SENDMESSAGE = 2;
    public final static int CONSTANT_SENDFILE = 3;

    // default values
    private final static String DEFAULT_NAME = "Anon";
    private final static int DEFAULT_TYPE = 1;

    // instance variable declarations
    private final String userName;
    private final int type;
    private final Date date;

    /**
     * General purpose constructor for creating a ClackData object.
     *
     * @param userName String representing the name of the user
     * @param type     Integer representing data type
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    /**
     * Anonymous constructor for when no name is available.
     *
     * @param type Integer representing data type
     */
    public ClackData(int type) {
        this(DEFAULT_NAME, type);
    }

    /**
     * Default constructor creates an invalid ClackData object.
     */
    public ClackData() {
        this(DEFAULT_TYPE);
    }

    /**
     * A simple get method for the data type.
     *
     * @return Integer representing data type
     */
    public int getType() {
        return type;
    }

    /**
     * A simple get method for the username.
     *
     * @return String representing the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A simple get method for the date.
     *
     * @return Date object representing the creation date of this instance of ClackData object.
     */
    public Date getDate() {
        return date;
    }

    /**
     * An abstract method to return the data contained in the class.
     *
     * @return String representing message or file contents.
     */
    public abstract String getData();

    /**
     * An abstract method to return the data contained in the class after decrypting it with the given key.
     *
     * @param key String representing encryption key.
     * @return String representing message or file contents.
     */
    public abstract String getData(String key);

    /**
     * A method to encrypt a string.
     *
     * @param inputStringToEncrypt The input string to encrypt.
     * @param key                  The encryption key to use to encrypt the input string.
     * @return The encrypted string.
     * @author Alex Cohen
     */
    protected String encrypt(String inputStringToEncrypt, String key) {
        char[] encryptedString = inputStringToEncrypt.toCharArray();
        char[] alphabet = new char[26];
        char charBuffer;
        int spaceCount = 0;

        key = key.toUpperCase();

        // populates the alphabet variable with each letter in the alphabet
        for (int i = 0; i < alphabet.length; ++i) {
            alphabet[i] = (char) (65 + i);
        }

        for (int i = 0; i < encryptedString.length; ++i) {
            charBuffer = encryptedString[i]; // stores the current character in a buffer variable
            // checks if the current character is a space, counts it if so
            if(charBuffer == ' ') {
                spaceCount++;
            }
            // checks if the current character is a lower case or upper case letter
            if (((int) charBuffer >= 65 && (int) charBuffer <= 90)
                    || ((int) charBuffer >= 97 && (int) charBuffer <= 122)) {
                // iterates through every letter in the alphabet
                for (int j = 0; j < alphabet.length; ++j) {
                    // ???
                    if (key.charAt((i - spaceCount) % key.length()) == alphabet[j]) {
                        encryptedString[i] += j;
                    }
                }

                // if the original letter was uppercase, make sure it wasn't shifted outside the alphabet by wrapping.
                if ((int) charBuffer >= 97 && (int) encryptedString[i] > 122) {
                    encryptedString[i] = (char) (((int) encryptedString[i] % 122) + 96);
                    // if the original letter was lowercase, make sure it wasn't shifted outside the alphabet by wrapping.
                } else if ((int) charBuffer <= 90 && (int) encryptedString[i] > 90) {
                    encryptedString[i] = (char) (((int) encryptedString[i] % 90) + 64);
                }
            }
        }
        return new String(encryptedString);
    }

    /**
     * A method to decrypt an encrypted string.
     *
     * @param inputStringToDecrypt The input string to decrypt.
     * @param key                  The encryption key to use to decrypt the input string.
     * @return The decrypted string.
     * @author Alex Cohen
     */
    protected String decrypt(String inputStringToDecrypt, String key) {
        char[] decryptedString = inputStringToDecrypt.toCharArray();
        char[] alphabet = new char[26];
        char charBuffer;
        int spaceCount = 0;

        key = key.toUpperCase();

        // populates the alphabet variable with each letter in the alphabet
        for (int i = 0; i < alphabet.length; ++i) {
            alphabet[i] = (char) (65 + i);
        }

        for (int i = 0; i < decryptedString.length; ++i) {
            charBuffer = decryptedString[i];
            // checks if the current character is a space, counts it if so
            if (charBuffer == ' ') {
                spaceCount++;
            }
            // checks if the current character is a lower case or upper case letter
            if (((int) charBuffer >= 65 && (int) charBuffer <= 90)
                    || ((int) charBuffer >= 97 && (int) charBuffer <= 122)) {
                // iterates through every letter in the alphabet
                for (int j = 0; j < alphabet.length; ++j) {
                    // ???
                    if (key.charAt((i - spaceCount) % key.length()) == alphabet[j]) {
                        decryptedString[i] -= j;
                    }
                }

                // if the original letter was lowercase, make sure it wasn't shifted outside the alphabet by wrapping.
                if ((int) charBuffer <= 90 && (int) decryptedString[i] < 65) {
                    decryptedString[i] += alphabet.length;
                }
                // if the original letter was uppercase, make sure it wasn't shifted outside the alphabet by wrapping.
                else if ((int) charBuffer >= 97 && (int) decryptedString[i] < 97) {
                    decryptedString[i] += alphabet.length;
                }
            }
        }
        return new String(decryptedString);
    }
}
