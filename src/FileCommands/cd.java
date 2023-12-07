package FileCommands;

import FileTypes.*;
import Terminal.*;

public class cd implements Command {

    public cd() {}

    public void exec(RunInstance main, String[] args) {
        // check if there was a correct amount of arguments
        if(args.length == 1) {
            main.setCurrFolder(main.getHomeFolder());
            return;
        }
        else if(args.length != 2) {
            System.out.println("Wrong amount of arguments");
            return;
        }
        // set values
        boolean isAbsolute = false;
        FileSystem tmp;
        String tmpS = args[1];
        for(int i =0; i < tmpS.length(); i++) {
            if(tmpS.charAt(i) == ('/')) {
                isAbsolute = true;
                break;
            }
        }
        //check argument for correct action
        if(args[1].equals(".") || args[1].equals("..") || args[1].equals("~")) {
            tmp = SpecialArg.specialArg(main, args[1]);
        }
        else {
            if(isAbsolute)
                tmp = AbsolutePath.parsePathToFolder(main, args[1]);
            else
                tmp = main.getCurrFolder().find(args[1]);
        }

        if(tmp == null) {
            System.out.println("There's no such folder");
        }
        else if(tmp instanceof Folder) {
            main.setCurrFolder((Folder) tmp);
        }
        else {
            System.out.println("That's not a folder");
        }

    }
}
