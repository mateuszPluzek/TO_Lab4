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
//        ls takes either no arguments or only 2
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
//      check if the given argument is a special case
        if(args[1].equals(".") || args[1].equals("..") || args[1].equals("~")) {
            tmp = SpecialArg.specialArg(main, args[1]);
        }
//        if it's not check if it's normal directory name or is it an absolute path that needs to be parsed
        else {
            if(AbsolutePathChecker.isAbsolute(args[1]))
                tmp = AbsolutePath.parsePathToFolder(main, args[1]);
            else
                tmp = main.getCurrFolder().find(args[1]);
        }
//          prints the directory contents if that directory exists
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

