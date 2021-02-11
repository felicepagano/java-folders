package it.fpagano;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Directory {
    public static final Directory ROOT = new Root("/");
    private final String name;
    private final Set<Directory> subFolders = new HashSet<>();
    private final Directory parentFolder;

    public Directory(String name, Directory parentFolder) {
        this.name = name;
        this.parentFolder = parentFolder;
    }

    public String getName() {
        return name;
    }

    public Set<Directory> getSubFolders() {
        return subFolders;
    }

    public Directory getParentFolder() {
        return parentFolder;
    }

    private static class Root extends Directory {
        public Root(String name) {
            super(name, null);
        }
    }

    public Directory create(String name) {
        var newDir = new Directory(name, this);
        boolean add = this.subFolders.add(newDir);
        if(!add) {
            //throw new RuntimeException("directory alredy exists");
        }
        return newDir;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(this.name + "\n");
        for (Directory subFolder : subFolders) {
            s.append("\t".repeat(Math.max(0, nested(0, subFolder))));
            s.append(subFolder.toString());
        }
        return s.toString();
    }

    public int nested(int init, Directory parentFolder) {
        if(parentFolder != null) {
            return nested(init, parentFolder.parentFolder) + 1;
        }
        return init;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return name.equals(directory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
