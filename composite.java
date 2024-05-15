import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        File f0 = new File(18);
        File f1 = new File(76);
        File f2 = new File(78);

        Directory dir1 = new Directory();
        Directory root = new Directory();

        root.add(dir1);
        root.add(f0);
        root.add(f1);
        dir1.add(f2);

        System.out.println(root.fs());
    }
}

interface FileSystemComponent {
    int getSize();
}

class Directory implements FileSystemComponent {
    private List<FileSystemComponent> children;

    public Directory() {
        this.children = new ArrayList<>();
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

class File implements FileSystemComponent {
    private int size;

    public File(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
