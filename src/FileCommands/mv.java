package FileCommands;

import FileTypes.FileSystem;
import FileTypes.Folder;
import Terminal.AbsolutePath;
import Terminal.AbsolutePathChecker;
import Terminal.RunInstance;

public class mv implements Command{

    public mv() {}

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length != 3) {
            System.out.println("Wrong amount of arguments");
            return;
        }

        FileSystem source, destination;
//        find if source file exists
        if(AbsolutePathChecker.isAbsolute(args[1])) {
            source = AbsolutePath.parsePathToFolder(main, args[1]);
            if(source == null)
                source = AbsolutePath.parsePathToFile(main, args[1]);
        }
        else {
            source = main.getCurrFolder().find(args[1]);
        }
//        if it does exists then check if the destination exists, if not then either move it to the folder or change the name
        if(source == null) {
            System.out.println("There is no source with that name");
            return;
        }
        else {
            if(AbsolutePathChecker.isAbsolute(args[2])) {
                destination = AbsolutePath.parsePathToFolder(main, args[2]);
//                you can't name a file using absolute path
                if(destination == null) {
                    System.out.println("There is no such destination");
                    return;
                }
            }
            else {
                destination = source.find(args[2]);
            }
//            the destination doesn't exists
            if(destination == null) {
                source.setName(args[2]);
            }
//            if it exists and is a folder
            else if(destination instanceof Folder) {
                source.getParent().remove(source.getName());
                source.setParent((Folder)destination);
                ((Folder) destination).add(source);
            }
            else {
                System.out.println("Name already exists");
            }
        }
    }
}
