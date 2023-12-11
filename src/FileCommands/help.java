package FileCommands;

import Terminal.RunInstance;

public class help implements Command {
    public help() {}

    @Override
    public void exec(RunInstance main, String[] args) {
        if(args.length != 1) {
            System.out.println("Wrong amount of arguments");
        }
        else {
            System.out.println("cd\nls\nmkdir\nmore\nmv\nrm\ntouch\ncp\nwim");
        }
    }
}
