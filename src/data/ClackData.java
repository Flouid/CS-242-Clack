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
    private String userName;
    private int type;
    private Date date;

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
        // This might need revisiting, right now it creates an invalid object
        this(DEFAULT_NAME, DEFAULT_TYPE);
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

        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) (65 + i);
        }

        for (int i = 0; i < encryptedString.length; i++) {
            charBuffer = encryptedString[i];
            if (encryptedString[i] == ' ') {
                spaceCount++;
            }
            if (((int) encryptedString[i] >= 65 && (int) encryptedString[i] <= 90)
                    || ((int) encryptedString[i] >= 97 && (int) encryptedString[i] <= 122)) {
                for (int j = 0; j < alphabet.length; j++)
                    if (key.charAt(((i - spaceCount) % key.length())) == alphabet[j]) {
                        encryptedString[i] += j;
                    }

                if ((int) charBuffer >= 97 && (int) charBuffer <= 122 && (int) encryptedString[i] > 122) {
                    encryptedString[i] = (char) (((int) encryptedString[i] % 122) + 96);
                } else if ((int) charBuffer >= 65 && (int) charBuffer <= 90 && (int) encryptedString[i] > 90) {
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

        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) (65 + i);
        }

        for (int i = 0; i < decryptedString.length; i++) {
            charBuffer = decryptedString[i];
            if (decryptedString[i] == ' ') {
                spaceCount++;
            }
            if (((int) decryptedString[i] >= 65 && (int) decryptedString[i] <= 90)
                    || ((int) decryptedString[i] >= 97 && (int) decryptedString[i] <= 122)) {
                for (int j = 0; j < alphabet.length; j++)
                    if (key.charAt((i - spaceCount) % key.length()) == alphabet[j]) {
                        decryptedString[i] -= j;
                    }

                if ((int) charBuffer >= 65 && (int) charBuffer <= 90 && (int) decryptedString[i] < 65) {
                    decryptedString[i] += alphabet.length;
                } else if ((int) charBuffer >= 97 && (int) charBuffer <= 122 && (int) decryptedString[i] < 97) {
                    decryptedString[i] += alphabet.length;
                }
            }
        }
        return new String(decryptedString);
    }
}
