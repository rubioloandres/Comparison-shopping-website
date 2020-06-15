package db;

import java.io.IOException;
import java.sql.SQLException;

public class DaoFactory {

    private DaoFactory() {}

    public static Dao getDao(String daoName) throws SQLException {
        try {
        	DaoImpl dao = DaoImpl.class.cast(Class.forName(DaoFactory.getDaoClassName(daoName, "")).newInstance());
            return dao;
        }
        catch(InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public static Dao getDao(String daoName, String daoPackage) throws SQLException {
        try {
            DaoImpl dao = DaoImpl.class.cast(Class.forName(DaoFactory.getDaoClassName(daoName, daoPackage)).newInstance());
            return dao;
        }
        catch(InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private static String getDaoClassName(String daoName, String daoPackage) throws SQLException {
        try {
            return daoPackage + "db.daos." + DaoProp.getProperty("CurrentDaoFactory") + daoName + "Dao";
        }
        catch(IOException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
