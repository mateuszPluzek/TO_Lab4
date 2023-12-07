package FileCommands;

import FileTypes.File;
import FileTypes.Folder;
import Terminal.AbsolutePath;
import Terminal.AbsolutePathChecker;
import Terminal.NameChecker;
import Terminal.RunInstance;

public class touch implements Command {

    public touch() {}

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length < 2) {
            System.out.println("Wrong number of arguments");
            return;
        }
        // if one argument provided it can be either name for a folder
        // or it can be a absolute path with the folder name at the end
        if(args.length == 2) {
            if(AbsolutePathChecker.isAbsolute(args[1])) {
                int index = args[1].lastIndexOf("/");
                Folder tmp = AbsolutePath.parsePathToFolder(main, args[1].substring(0, index));
                this.createFile(tmp, args[1].substring(index + 1));
            }
            else {
                this.createFile(main.getCurrFolder(), args[1]);
            }
        }
        // when there is more arguments each one is a name of a new folder in the current directory
        else {
            for(int i = 1; i < args.length; i++) {
                this.createFile(main.getCurrFolder(), args[i]);
            }
        }

    }

    private void createFile(Folder dir, String name) {
        if(NameChecker.doesNameExist(dir, name)) {
            dir.add(new File(name));
        }
        else {
            System.out.println("Name already exists");
        }
    }
}
