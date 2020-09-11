package data;

import java.io.File;
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String getData() {
        return fileContents;
    }

    public void readFileContents() {
    }

    public void writeFileContents() {
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
        return fileName.equals(fileData.fileName) &&
                fileContents.equals(fileData.fileContents) &&
                getType() == fileData.getType() &&
                getUserName().equals(fileData.getUserName());
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
