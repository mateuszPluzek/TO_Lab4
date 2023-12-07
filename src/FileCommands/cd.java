package FileCommands;

import FileTypes.*;
import Terminal.RunInstance;

public class cd implements Command {

    public cd() {

    }

    public void exec(RunInstance main, String[] args) {
        // check if there was a correct amount of arguments
        if(args.length != 2) {
            System.out.println("Wrong amount of arguments");
            return;
        }
        // check if "." was used as an argument
        if(args[1].equals(".") && main.getCurrFolder() != main.getRoot()) {
            main.setCurrFolder(main.getCurrFolder().getParent());
            return;
        }

        FileSystem tmp = main.getCurrFolder().find(args[1]);

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
