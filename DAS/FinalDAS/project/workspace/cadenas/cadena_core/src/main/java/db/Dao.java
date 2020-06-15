package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

    public Bean make(ResultSet result) throws SQLException;
    public void insert(Bean bean) throws SQLException;
    public void insertBatch(List<Bean> beans) throws SQLException;
    public void update(Bean bean) throws SQLException;
    public void delete(Bean bean) throws SQLException;
    public List<Bean> select(Bean bean) throws SQLException;
    public boolean valid(Bean bean) throws SQLException;

}
