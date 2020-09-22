package ua.alevel.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FileSystem {
    private File file;

    public String getFilePath() {
        return this.file.getAbsolutePath();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileSystem(File file) {
        this.file = file;
    }

    public FileSystem() {
    }

    public FileSystem(String filePath) {
        this.file = new File(filePath);
    }

    private void show(int level) {
        for (File currentFile : Objects.requireNonNull(file.listFiles())) {
            FileSystem fileSystem = new FileSystem(currentFile);

            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }

            if (fileSystem.getFile().isDirectory()) {
                System.out.println(" [directory] " + fileSystem.getFile().getName());
                level++;
                try {
                    fileSystem.show(level);
                } catch (NullPointerException e) {
                }
                level--;
            } else if (fileSystem.getFile().isFile()) {
                System.out.println(" [file] " + fileSystem.getFile().getName());
            }
        }
    }

    public void show() {
        System.out.println("==================================================================");
        if (file.isDirectory()) {
            System.out.println("\nnow you are in folder \"" + file.getName() + "\"\n");
        } else {
            System.out.println("\nnow you are in folder \"" + file.getParentFile().getName() + "\"\n");
        }
        show(0);
        System.out.println("\n==================================================================");
    }

    public static void createDirectory(String dir, String directoryName) {
        File newDirectory = new File(dir, directoryName);
        newDirectory.mkdir();
    }

    public static void createFile(String dir, String fileName) {
        File newFile = new File(dir, fileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() {
        this.file = file.getParentFile();
    }

    public ArrayList<String> availableDirectories() {
        ArrayList<String> dirs = new ArrayList<>();
        for (File current : Objects.requireNonNull(file.listFiles())) {
            if (current.isDirectory()) dirs.add(current.getName());
        }
        return dirs;
    }

    public ArrayList<String> allAvailableFiles() {
        ArrayList<String> allFiles = new ArrayList<>();
        for (File current : Objects.requireNonNull(file.listFiles())) {
            allFiles.add(current.getName());
        }
        return allFiles;
    }

    public static void delete(String dir, String fileName) {
            new File(dir, fileName).delete();
    }
}
