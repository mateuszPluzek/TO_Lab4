package Terminal;

public class AbsolutePathChecker {

    public static boolean isAbsolute(String path) {
        String tmpS = path;
        boolean isAbsolute = false;
        for(int i =0; i < tmpS.length(); i++) {
            if(tmpS.charAt(i) == ('/')) {
                isAbsolute = true;
                break;
            }
        }
        return isAbsolute;
    }
}
