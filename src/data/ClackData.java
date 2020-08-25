package data;

import java.util.Date;

/**
 * @author Louis Keith
 */

public class ClackData {

    // constant declarations
    public final static int CONSTANT_LISTUSERS = 0;
    public static int CONSTANT_LOGOUT = 1;
    public static int CONSTANT_SENDMESSAGE = 2;
    public static int CONSTANT_SENDFILE = 3;

    // instance variable declarations
    String userName;
    int type;
    Date date;

    // constructor to set up userName and type, date is automatically created
    ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    // constructor to create anonymous user
    ClackData(int type) {
        this("Anon", type);
    }

    // default constructor
    ClackData() {
        this("Invalid", 0);     // This might need revisiting
    }

    // returns the type
    public int getType() {
        return type;
    }

    // returns the userName
    public String getUserName() {
        return userName;
    }

    // returns the date
    public Date getDate() {
        return date;
    }

    // getData() should go here, I have no idea what it's asking of me though
}
