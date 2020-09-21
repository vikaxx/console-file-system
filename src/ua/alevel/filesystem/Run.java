package ua.alevel.filesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Run {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private FileSystem fileSystem = new FileSystem("C:\\Users\\User\\IdeaProjects\\nio");
    private String filePath = fileSystem.getFilePath();

    public void run() {
        try {
            while (true) {
                fileSystem.show();

                System.out.println("\nChoose action, please: ");
                System.out.println("1 - new folder");
                System.out.println("2 - new file");
                System.out.println("3 - go to folder");
                System.out.println("4 - delete folder/file");
                System.out.println("5 - back");
                System.out.println("q - quit\n");

                String input = reader.readLine();
                switch (input) {
                    case "1":
                        newDirectory();
                        break;
                    case "2":
                        newFile();
                        break;
                    case "3":
                        goToFolder();
                        break;
                    case "4":
                        delete();
                        break;
                    case "5":
                        back();
                        break;
                    case "q":
                        System.out.println("Exit!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Input 'q' to quit.\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void newDirectory() {
        String input = "";
        try {
            System.out.print("Input directory name: ");
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileSystem.createDirectory(filePath, input);
    }

    private void newFile() {
        String input = "";
        try {
            System.out.print("Input file name: ");
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileSystem.createFile(filePath, input);
    }

    private void back() {
        fileSystem.back();
    }

    private void goToFolder() {
        String input = "";
        ArrayList<String> available = new ArrayList<>();
        try {
            System.out.println("Available folders are:");
            available = fileSystem.availableDirectories();
            available.forEach(System.out::println);
            System.out.print("\nInput folder name: ");
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (available.contains(input)) {
            File file = new File(filePath, input);
            fileSystem.setFile(file);
            filePath = fileSystem.getFilePath();
        } else {
            System.out.println("No such folder.");
        }
    }

    private void delete() {
        String input = "";
        try {
            System.out.println("Available are:");
            fileSystem.allAvailableFiles().forEach(System.out::println);
            System.out.print("\nInput folder/file name: ");
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileSystem.delete(filePath, input);
    }

}
