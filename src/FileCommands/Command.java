package FileCommands;


import Terminal.RunInstance;

import java.util.ArrayList;

public interface Command {
    public void exec(RunInstance main, String[] args);
}
