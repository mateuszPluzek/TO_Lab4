package Terminal;
import FileTypes.*;

public class AbsolutePath {

    public static Folder parsePathToFolder(RunInstance main, String path) {
        String[] folders = path.split("/");
        if(folders.length == 0)
            return null;

        Folder current;
        int i = 1;

        if(folders[0].equals(".") || folders[0].equals("..") || folders[0].equals("~")){
            current = SpecialArg.specialArg(main, folders[0]);
        }
        else if(folders[0].equals(main.getRoot().getName())) {
            current = main.getRoot();
        }
        else {
            current = main.getCurrFolder();
            i = 0;
        }

        FileSystem tmp;
        for(;i < folders.length;i++) {
            if(folders[i].equals(".") ||folders[i].equals(".."))
                tmp = SpecialArg.specialArg(main, folders[i]);
            else
                tmp = current.find(folders[i]);

            if(tmp == null || tmp instanceof File) {
                return null;
            }
            current = (Folder) tmp;
        }
        return current;
    }

    public static File parsePathToFile(RunInstance main, String path) {
        String[] folders = path.split("/");
        Folder current;
        int i = 1;

        if(folders[0].equals(".") || folders[0].equals("..") || folders[0].equals("~")){
            current = SpecialArg.specialArg(main, folders[0]);
        }
        else if(folders[0].equals(main.getRoot().getName())) {
            current = main.getRoot();
        }
        else {
            current = main.getCurrFolder();
            i = 0;
        }

        FileSystem tmp;
        for(;i < folders.length-1;i++) {
            if(folders[i].equals(".") ||folders[i].equals(".."))
                tmp = SpecialArg.specialArg(main, folders[i]);
            else
                tmp = current.find(folders[i]);

            if(tmp instanceof File || tmp == null) {
                return null;
            }
            current = (Folder)tmp;
        }

        tmp = current.find(folders[folders.length-1]);
        if(tmp instanceof Folder || tmp == null) {
            return null;
        }
        return (File) tmp;

    }

}
