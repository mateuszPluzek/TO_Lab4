package FileTypes;

public interface FileSystem {
//  Searches for the file with that name in the directory or subdirectiories.
//  Returns that file if found, returns null if can't find it
    public FileSystem find(String name);

    public void setParent(Folder parent);
    public Folder getParent();

    public String getName();

    public void setName(String name);

}
