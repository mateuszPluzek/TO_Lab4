package Terminal;

import FileTypes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class RunInstance {

    private Folder root;
    private Folder currFolder;
    private Folder homeFolder;

    public RunInstance() {
        this.root = new Folder("root");
        this.homeFolder = new Folder("admin");
        Folder usr = new Folder("usr");
        usr.add(this.homeFolder);
        this.currFolder = root;

        this.root.add(new Folder("dev"));
        this.root.add(usr);
        this.root.add(new Folder("docs"));

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            this.pwd();
            // Asking user for input
            input = scanner.nextLine();
            // if user types exit or simply "q" he quits
            if(input.trim().equals("exit") || input.trim().equals("q"))
                break;
            // executes the command
            CommandParser.parseCommand(this, input);

        }
    }



    private void pwd() {
        ArrayList<String> path = new ArrayList<>();
        Folder tmp;
        tmp = this.getCurrFolder();
        while(tmp.getParent() != null) {
            path.add(tmp.getName());
            tmp = tmp.getParent();
        }
        path.add("root");

        for(int i = path.size() - 1; i >= 0;i--) {
            System.out.print(path.get(i) + "/");
        }
        System.out.print("> ");
    }

    public Folder getHomeFolder() {
        return homeFolder;
    }

    public void setHomeFolder(Folder homeFolder) {
        this.homeFolder = homeFolder;
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
