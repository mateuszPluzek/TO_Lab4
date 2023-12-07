package FileCommands;

import FileTypes.FileSystem;
import Terminal.RunInstance;

public class ls implements Command{

    public ls(){}

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length == 1) {
            main.setCurrFolder(main.getHomeFolder());
            return;
        }
        else if(args.length != 2) {
            System.out.println("Wrong amount of arguments");
            return;
        }
        // set values
        boolean isAbsolute = false;
        FileSystem tmp;
        String tmpS = args[1];
        for(int i =0; i < tmpS.length(); i++) {
            if(tmpS.charAt(i) == ('/')) {
                isAbsolute = true;
                break;
            }
        }



    }
}
