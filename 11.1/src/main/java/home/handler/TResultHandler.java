package home.handler;

import home.DBServiceException;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface TResultHandler<T> {
    T handle(ResultSet resultSet) throws DBServiceException;
}
