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
        FileSystem tmp;
        //check argument if its a special case
        if(args[1].equals(".") || args[1].equals("..") || args[1].equals("~")) {
            tmp = SpecialArg.specialArg(main, args[1]);
        }
//        if it is not then make sure that the path is correctly parsed
        else {
            if(AbsolutePathChecker.isAbsolute(args[1]))
                tmp = AbsolutePath.parsePathToFolder(main, args[1]);
            else
                tmp = main.getCurrFolder().find(args[1]);
        }
//      changes the current directory if the given one exists
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
