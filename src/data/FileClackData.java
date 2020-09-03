package data;

import java.io.File;

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
    FileClackData() {
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
        return super.hashCode();
    }

    /**
     * A method to determine if two files are equal.
     *
     * @param other FileClackData object representing another file
     * @return boolean representing if the other parameter equals this instance of FileClackData
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof FileClackData)) return false;

        FileClackData otherFile = (FileClackData) other;
        return fileContents.equals(otherFile.getData()) &&
                getUserName().equals(otherFile.getUserName()) &&
                getType() == otherFile.getType() &&
                getDate() == otherFile.getDate();
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
