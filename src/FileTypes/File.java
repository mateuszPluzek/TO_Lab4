package FileTypes;

public class File implements FileSystem {
    private String name;
    private String content;

    private Folder parent = null;

    public File(String name) {
        this.name = name;
    }

    public File(String name, String data) {
        this.name = name;
        this.content = data;
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

    public void setContent(String data) {
        this.content= data;
    }

    public String getContent() {
        return content;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public Folder getParent() {
        return parent;
    }
}
