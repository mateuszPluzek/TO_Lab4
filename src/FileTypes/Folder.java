package FileTypes;

import java.util.ArrayList;

public class Folder implements FileSystem {
    private ArrayList<FileSystem> children;
    private String name;

    private Folder parent = null;

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    public Folder(String name, ArrayList<FileSystem> children) {
        this.name = name;
        this.children = children;
    }

    public Folder(Folder origin) {
        this.name = origin.getName();
        this.parent = origin.getParent();
        this.children = new ArrayList<>();

        if(origin.getChildren() != null) {
            for (FileSystem f : origin.getChildren()) {
                if (f instanceof File) {
                    this.add(new File((File) f));
                } else
                    this.add(new Folder((Folder) f));
            }
        }
    }

    public void add(FileSystem file) {
        this.children.add(file);
        file.setParent(this);
    }

    /*TODO add functionality to this 3 functions*/
    public void remove(String name) {
        for(int i = 0; i < this.children.size(); i++) {
            if(this.get(i).getName().equals(name)) {
                this.children.remove(i);
//
                break;
            }
        }
    }

    public FileSystem get(int id) {
        return this.children.get(id);
    }

    public void modify(int id) {
        ;
    }

    public FileSystem find(String name) {
        FileSystem tmp = null;
        for (FileSystem f : this.children) {
            if (f instanceof File) {
                if ((tmp = f.find(name)) != null)
                    break;
            } else {
                if ((f.getName().equals(name))) {
                    tmp = f;
                    break;
                }
            }
        }
        return tmp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public Folder getParent() {
        return parent;
    }

    public ArrayList<FileSystem> getChildren() {
        return children;
    }
}
// Old find where you can get the file in subdirectory
//    public FileSystem find(String name) {
//        FileSystem tmp = null;
//        for(FileSystem f : this.children) {
//            if(f instanceof File) {
//                if ((tmp = f.find(name)) != null)
//                    break;
//            }
//            else {
//                if (((Folder) f).getName().equals(name))
//                    return this;
//            }
//        }
////      Search subdirectories for that file
//        if(tmp == null) {
//            for(FileSystem f : this.children) {
//                if(f instanceof Folder) {
//                    tmp = f.find(name);
//                    if(tmp != null) {
//                        break;
//                    }
//                }
//            }
//        }
//        return tmp;
//    }