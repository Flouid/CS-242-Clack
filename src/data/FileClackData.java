package data;

public class FileClackData extends ClackData{

    private static String fileName; // String representing name of file
    private static String fileContents; // string representing contents of file

    FileClackData(String username, String fileName, String type){
        super();
        username = null;
        fileName = null;
        type = null;
    }

    FileClackData(){
        super();
    }

    private static void setFileName(String name){
        fileName = name;
    }

    private static String getFileName(){
        return fileName;
    }

    private static String getData(){
        return fileContents;
    }

    private static void readFileContents(){}

    private static void writeFileContents(){}

    public int hashCode(){
        return 0;
    }

    public void equals(){
    }

    public String toString(){
        return "";
    }
}
