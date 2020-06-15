package db;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

public class DaoProp {

    private static Properties propFile = new Properties();
    private static boolean    loadProp = false;

    private DaoProp() { }

    public static String getProperty(String key) throws IOException {
        if(!DaoProp.loadProp) {
            InputStream file = DaoFactory.class.getResourceAsStream("../DaoFactory.properties");
            DaoProp.propFile.load(file);
            file.close();

            DaoProp.loadProp = true;
        }
        return DaoProp.propFile.getProperty(key);
    }

}
