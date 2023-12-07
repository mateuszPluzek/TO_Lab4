package FileCommands;

import FileTypes.File;
import FileTypes.FileSystem;
import FileTypes.Folder;
import Terminal.AbsolutePath;
import Terminal.AbsolutePathChecker;
import Terminal.RunInstance;

public class more implements Command{

    public more() {}

    @Override
    public void exec(RunInstance main, String[] args) {
//        more can have only one argument and it's a file name
        if(args.length != 2) {
            System.out.println("Wrong amount of arguments");
            return;
        }
//        tmp value
        FileSystem tmp;
//        check if the argument is an absolute path or just file name, and then if it exists show it's content
        if(AbsolutePathChecker.isAbsolute(args[1])) {
            tmp = AbsolutePath.parsePathToFile(main, args[1]);
        }
        else {
            tmp = main.getCurrFolder().find(args[1]);
        }
//        Printing the result
        if(tmp instanceof File)
            System.out.println(((File) tmp).getContent());
        else if(tmp instanceof Folder)
            System.out.println("Not a file");
        else
            System.out.println("File not found");


    }
}
