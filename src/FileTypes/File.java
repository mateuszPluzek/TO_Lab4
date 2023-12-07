package FileTypes;

public class File implements FileSystem {
    private String name;
    private String data;

    private Folder parent = null;

    public File(String name) {
        this.name = name;
    }

    public File(String name, String data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public FileSystem find(String name) {
        if(name.equals(this.name))
            return this;
        else
            return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public Folder getParent() {
        return parent;
    }
}
