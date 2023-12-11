package FileCommands;

import FileTypes.File;
import FileTypes.FileSystem;
import Terminal.AbsolutePath;
import Terminal.AbsolutePathChecker;
import Terminal.RunInstance;

import java.util.Scanner;

public class wim implements Command{

    public wim() {};

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length != 2) {
            System.out.println("Wrong amount of the arguments");
            return;
        }

        FileSystem tmp;
        if(AbsolutePathChecker.isAbsolute(args[1])) {
            tmp = AbsolutePath.parsePathToFile(main, args[1]);
        }
        else {
            tmp = main.getCurrFolder().find(args[1]);
        }
//        when the file exists and is a File
        if(tmp == null) {
            System.out.println("File doesn't exist");
        }
        else if(tmp instanceof File) {
            Scanner scanner = new Scanner(System.in);
            String input;
            while(true) {
                System.out.print("input > ");
                input = scanner.nextLine();

                if (input.equals(":s"))
                    break;
                else if(input.equals(":d")) {
                    ((File) tmp).setContent("");
                    break;
                }
                else
                    ((File) tmp).addContent(input + " ");
            }

        }
        else {
            System.out.println("Not a file");
        }

    }
}
