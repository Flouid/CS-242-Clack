package data;

import java.io.*;
import java.util.Objects;

/**
 * This class is the basic building block for a file in Clack.
 *
 * @author Alex Cohen
 */

public class FileClackData extends ClackData {

    // default values
    private final static String DEFAULT_FILE_NAME = "";
    private final static String DEFAULT_FILE_CONTENTS = "";

    // instance variable declarations
    private String fileName;
    private String fileContents;

    /**
     * General purpose constructor to set up username, message, and type
     *
     * @param userName String representing the user name of the sender
     * @param fileName String representing name of file
     * @param type     Integer representing the type of message being sent
     */
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        fileContents = null;
    }

    /**
     * Default constructor initializes with a blank file.
     */
    public FileClackData() {
        super();
        fileName = DEFAULT_FILE_NAME;
        fileContents = DEFAULT_FILE_CONTENTS;
    }

    /**
     * A method to set the name of a file
     *
     * @param fileName String representing the new name for the file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * A method to return the name of a file
     *
     * @return a string representing the name of a file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * A method to return the contents of a file
     *
     * @return A string representing the contents of a file
     */
    @Override
    public String getData() {
        return fileContents;
    }

    /**
     * A method to decrypt and return the contents of a file using a key
     *
     * @param key String representing the encryption key
     * @return A string representing the contents of a file
     * @author Louis Keith
     */
    public String getData(String key) {
        return decrypt(fileContents, key);
    }

    /**
     * A private helper method containing all of the code to read
     * the contents of a file and return it as a single String
     *
     * @return String representing the contents of a file
     * @throws IOException when an I/O error occurs
     * @author Louis Keith
     */
    private String readFileContentsHelper() throws IOException {
        String fileContents = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String nextLine;

            // read each line and append it to the string buffer followed by a line separator
            while ((nextLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(nextLine);
                stringBuilder.append(System.getProperty("line.separator"));
            }

            // remove the extra line separator at the end of the string.
            // this will never throw an IndexOutOfBoundsException as the index is by definition less than the length
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            bufferedReader.close();

            fileContents = stringBuilder.toString();
        } catch (FileNotFoundException nfe) {
            System.err.println("System could not find the specified file: " + fileName);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return fileContents;
    }

    /**
     * A method to read the contents of a non-secure file
     *
     * @throws IOException when an I/O error occurs
     * @author Louis Keith
     */
    public void readFileContents() throws IOException {
        fileContents = readFileContentsHelper();
    }

    /**
     * An overloaded method to perform secure file reads
     * Reads the file contents and then encrypts them
     *
     * @param key String representing the key to use to encrypt the file contents
     * @throws IOException when an I/O error occurs
     * @author Louis Keith
     */
    public void readFileContents(String key) throws IOException {
        fileContents = encrypt(readFileContentsHelper(), key);
    }

    /**
     * A private helper method containing of the code to write the contents of a file
     *
     * @param fileContents String representing the contents that should be written to the file
     * @author Louis Keith
     */
    private void writeFileContentsHelper(String fileContents) {
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            fileWriter.write(fileContents);
            fileWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * A method to write to a file in a non-secure manner
     *
     * @author Louis Keith
     */
    public void writeFileContents() {
        writeFileContentsHelper(fileContents);
    }

    /**
     * A method to write to a file in a secure manner
     * Decrypts the file contents and then writes them
     *
     * @param key String representing the key to use to decrypt file contents
     * @author Louis Keith
     */
    public void writeFileContents(String key) {
        writeFileContentsHelper(decrypt(fileContents, key));
    }

    /**
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getType(), getUserName(), fileContents, fileName);
    }

    /**
     * A method to determine if two files are equal.
     *
     * @param other FileClackData object representing another file
     * @return boolean representing if the other parameter equals this instance of FileClackData
     */

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        FileClackData fileData = (FileClackData) other;
        return Objects.equals(fileName, fileData.fileName) &&
                Objects.equals(fileContents, fileData.fileContents);
    }

    /**
     * A method to return the entire file object as a string.
     *
     * @return A string with all of the instance data for the file
     */
    @Override
    public String toString() {
        return getUserName() + " sent a file: " + fileName + "\nwith contents: " + fileContents + " with type: " + getType();
    }
}
