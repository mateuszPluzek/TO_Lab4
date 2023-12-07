package Terminal;

import FileTypes.Folder;

public class SpecialArg {

    public static Folder specialArg(RunInstance main, String arg) {
        if(arg.equals("."))
            return main.getCurrFolder();
        else if(arg.equals(".."))
            return main.getCurrFolder().getParent();
        else if(arg.equals("~"))
            return main.getHomeFolder();
        else
            return null;
    }
}
