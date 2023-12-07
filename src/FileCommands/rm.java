package FileCommands;

import FileTypes.File;
import FileTypes.FileSystem;
import FileTypes.Folder;
import Terminal.AbsolutePath;
import Terminal.AbsolutePathChecker;
import Terminal.RunInstance;

public class rm implements Command{

    public rm() {}

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length < 2) {
            System.out.println("Wrong number of arguments");
        }
//        When there are 2 arguments its a rm file
        FileSystem tmp;
        if(args.length == 2) {
//            parse absolute path to file
            if(AbsolutePathChecker.isAbsolute(args[1]))
                tmp = AbsolutePath.parsePathToFile(main, args[1]);
            else
                tmp = main.getCurrFolder().find(args[1]);
//            remove it accordingly
            if(tmp instanceof File) {
                tmp.getParent().remove(tmp.getName());
            }
            else if(tmp instanceof Folder) {
                System.out.println("Use -r for removing folders");
            }
            else {
                System.out.println("There's no such file");
            }
        }
//        when there are more arguments it can be either -r folder or multiple files
        else {
//            if it has -r argument it can only be a single folder removal command
            boolean hasArgument = args[1].equals("-r");
            if(hasArgument) {
//                parse absolute path if its given as one
                if(AbsolutePathChecker.isAbsolute(args[2]))
                    tmp = AbsolutePath.parsePathToFolder(main, args[2]);
                else
                    tmp = main.getCurrFolder().find(args[2]);
//                remove the directory if it exists
                if(tmp instanceof Folder) {
                    tmp.getParent().remove(tmp.getName());
                }
                else if(tmp instanceof File) {
                    System.out.println("Use -r for removing folders not files");
                }
                else {
                    System.out.println("There's no such folder");
                }

            }
//            else it is a multiple files, where you can only pass them as filenames not paths
            else {
                for(int i = 1; i < args.length;i++) {
                    tmp = main.getCurrFolder().find(args[i]);
                    if(tmp instanceof File) {
                        tmp.getParent().remove(args[i]);
                    }
                    else if(tmp instanceof Folder) {
                        System.out.println("Use -r for removing folders");
                    }
                    else {
                        System.out.println("There's no such file");
                    }
                }
            }
        }

    }
}
