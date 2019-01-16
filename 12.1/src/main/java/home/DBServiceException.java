package home;

import java.sql.SQLException;

public class DBServiceException extends Exception {

    public DBServiceException(Throwable cause) {
        initCause(cause);
    }

    public DBServiceException() {
    }

    public DBServiceException(String message) {

        super(message);

    }
}
