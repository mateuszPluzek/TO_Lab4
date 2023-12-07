package FileCommands;

import FileTypes.FileSystem;
import FileTypes.Folder;
import Terminal.AbsolutePath;
import Terminal.AbsolutePathChecker;
import Terminal.RunInstance;
import Terminal.SpecialArg;

public class ls implements Command{

    public ls(){}

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length == 1) {
            this.showDirectory(main.getCurrFolder());
            return;
        }
        else if(args.length != 2) {
            System.out.println("Wrong amount of arguments");
            return;
        }
        // set values
        FileSystem tmp;

        if(args[1].equals(".") || args[1].equals("..") || args[1].equals("~")) {
            tmp = SpecialArg.specialArg(main, args[1]);
        }
        else {
            if(AbsolutePathChecker.isAbsolute(args[1]))
                tmp = AbsolutePath.parsePathToFolder(main, args[1]);
            else
                tmp = main.getCurrFolder().find(args[1]);
        }

        if(tmp == null) {
            System.out.println("There's no such folder");
        }
        else if(tmp instanceof Folder) {
            this.showDirectory((Folder)tmp);
        }
        else {
            System.out.println("That's not a folder");
        }

    }

    private void showDirectory(Folder base) {
        for(FileSystem f : base.getChildren()) {
            System.out.print(f.getName() + "   ");
        }
        System.out.println();
    }
}

