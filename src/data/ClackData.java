package data;

import java.util.Date;

/**
 * @author Louis Keith
 */

public class ClackData {

    // constant declarations
    static int CONSTANT_LISTUSERS = 1;
    static int CONSTANT_LOGOUT = 2;
    static int CONSTANT_SENDMESSAGE = 3;
    static int CONSTANT_SENDFILE = 4;

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
}
