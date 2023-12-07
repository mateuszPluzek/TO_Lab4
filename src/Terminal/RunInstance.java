package Terminal;

import FileTypes.*;

import java.util.Scanner;

public class RunInstance {

    private Folder root;
    private Folder currFolder;

    public RunInstance() {
        this.root = new Folder("root");
        this.currFolder = root;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        root.add(new Folder("Data"));

        while(true) {

            // Asking user for input
            input = scanner.nextLine();
            // if user types exit or simply "q" he quits
            if(input.trim().equals("exit") || input.trim().equals("q"))
                break;
            else if(input.equals("?")) {
                System.out.println(this.getCurrFolder().getName());
            }
            // executes the command
            CommandParser.parseCommand(this, input);

        }
    }

    public Folder getCurrFolder() {
        return currFolder;
    }

    public void setCurrFolder(Folder currFolder) {
        this.currFolder = currFolder;
    }

    public Folder getRoot() {
        return root;
    }
}
