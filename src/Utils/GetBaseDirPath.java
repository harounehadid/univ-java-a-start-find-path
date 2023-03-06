package Utils;

public abstract class GetBaseDirPath {
    public static String root() {
        return System.getProperty("user.dir");
    }
}
