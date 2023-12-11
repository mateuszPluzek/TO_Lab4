package Terminal;

import FileTypes.FileSystem;
import FileTypes.Folder;

public class NameChecker {
    public static boolean doesNameExist(Folder directory, String name) {
        if(directory.getName().equals(name) || name.equals(".") || name.equals("..") || name.equals("~")) {
            return false;
        }

        for(FileSystem f : directory.getChildren()) {
            if(f.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }
}
