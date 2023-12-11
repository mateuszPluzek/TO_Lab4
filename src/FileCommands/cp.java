package FileCommands;

import FileTypes.File;
import FileTypes.FileSystem;
import FileTypes.Folder;
import Terminal.*;

public class cp implements Command {

    public cp() {}

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
                if(args[2].equals(".") || args[2].equals("..") || args[2].equals("~"))
                    destination = SpecialArg.specialArg(main, args[2]);
                else
                    destination = source.getParent().find(args[2]);
            }
//            the destination doesn't exists
            if(destination == null) {
                System.out.println("There is no destination with that name");
            }
//            if it exists and is a folder
            else if(destination instanceof Folder) {
                if(source instanceof File) {
                    if(NameChecker.doesNameExist((Folder)destination, source.getName()))
                        ((Folder) destination).add(new File((File)source));
                    else
                        System.out.println("File exists in the destination");
                }
                else {
                    if(NameChecker.doesNameExist((Folder)destination, source.getName()))
                        ((Folder) destination).add(new Folder((Folder)source));
                    else
                        System.out.println("Folder exists in the destination");
                }
            }
            else {
                System.out.println("Name already exists");
            }
        }
    }
}
