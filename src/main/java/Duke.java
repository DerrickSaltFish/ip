import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        tasks = readFile();
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        String line = "     ____________________________________________________________     \n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        String input = in.nextLine();
        while(!input.equals("bye")) {
            String instruction = input.split("\\s+")[0];
            String content;
            if(instruction.equals(input)) {
                content = "";
            } else {
                content = input.substring(instruction.length() + 1);
            }
            if(instruction.equals("mark")) {
                int number = Integer.parseInt(content) - 1;
                Task modified = tasks.get(number).markTast();
                tasks.remove(number);
                tasks.add(number, modified);
                System.out.println(line + "     Nice! I've marked this task as done:" + "\n" + "       " +modified.toString() + "\n" + line);
                try {
                    writeFile(tasks);
                } catch (IOException e) {
                    System.out.println("     An error occurred.");
                    e.printStackTrace();
                }
            } else if(instruction.equals("unmark")) {
                int number = Integer.parseInt(content) - 1;
                Task modified = tasks.get(number).unmarkTask();
                tasks.remove(number);
                tasks.add(number, modified);
                System.out.println(line +"      OK, I've marked this task as not done yet:" + "\n" + "       "  + modified.toString() + "\n" + line);
                try {
                    writeFile(tasks);
                } catch (IOException e) {
                    System.out.println("     An error occurred.");
                    e.printStackTrace();
                }
            } else if(instruction.equals("list")) {
                System.out.println(line);
                for(int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1).toString());
                }
                System.out.println(line);
            } else if(instruction.equals("todo")){
                if(content.equals("")) {
                    System.out.println(line + "     ☹ OOPS!!! The description of a todo cannot be empty." +"\n" + line);
                } else {
                    Task todoTask = new ToDos(content);
                    tasks.add(todoTask);
                    printAdd(tasks.size(), todoTask);
                    try {
                        writeFile(tasks);
                    } catch (IOException e) {
                        System.out.println("     An error occurred.");
                        e.printStackTrace();
                    }
                }
            } else if(instruction.equals("deadline")) {
                String actualContent = content.split("/")[0];
                String time = content.split("/")[1];
                Task deadlineTask = new DeadLines(actualContent, time);
                tasks.add(deadlineTask);
                printAdd(tasks.size(), deadlineTask);
                try {
                    writeFile(tasks);
                } catch (IOException e) {
                    System.out.println("     An error occurred.");
                    e.printStackTrace();
                }
            } else if(instruction.equals("event")) {
                String actualContent = content.split("/")[0];
                String time = content.split("/")[1] + content.split("/")[2];
                Task eventTask = new Events(actualContent, time);
                tasks.add(eventTask);
                printAdd(tasks.size(), eventTask);
                try {
                    writeFile(tasks);
                } catch (IOException e) {
                    System.out.println("     An error occurred.");
                    e.printStackTrace();
                }
            } else if(instruction.equals("delete")) {
                String strnumber = content;
                int maxnumber = tasks.size();
                if(!strnumber.matches("[0-9]+")) {
                    System.out.println(line + "     ☹ OOPS!!! The number must be an integer between 1 and " + maxnumber
                            + "\n" + line);
                } else {
                    int number = Integer.parseInt(strnumber);
                    if(number >= 1 && number <= maxnumber) {
                        Task deletedTask = tasks.get(number - 1);
                        tasks.remove(number - 1);
                        printDelete(maxnumber - 1, deletedTask);
                        try {
                            writeFile(tasks);
                        } catch (IOException e) {
                            System.out.println("     An error occurred.");
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(line + "     ☹ OOPS!!! The number must be between 1 and " + maxnumber
                                + "\n" + line);
                    }
                }
            }
            else {
                System.out.println(line + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +"\n" + line);
            }

            input = in.nextLine();
        }

        System.out.println(line + "     Bye. Hope to see you again soon!" + "\n" + line);
    }

    public static void printAdd(int size, Task thistask) {
        String line = "     ____________________________________________________________     \n";
        System.out.println(line + "     Got it. I've added the task:" + "\n" + "       " + thistask.toString()
                + "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line);
    }
    public static void printDelete(int size, Task thistask) {
        String line = "     ____________________________________________________________     \n";
        System.out.println(line + "     Got it. I've removed the task:" + "\n" + "       " + thistask.toString()
                + "\n" + "     Now you have " + size + " tasks in the list." + "\n" + line);
    }

    public static ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        String filePath = "data/data.txt";
        String folderPath = "data";
        File folder = new File(folderPath);
        if(folder.mkdir()) {
            System.out.println("     folder data is created.");
        } else {
            System.out.println("     folder data already exists.");
        }

        try {
            File file = new File(filePath);
            if(file.createNewFile()) {
                System.out.println("     file data is created.");
            } else {
                System.out.println("     file data already exists.");
            }
        } catch (IOException e) {
            System.out.println("     An error occurred.");
            e.printStackTrace();
        }

        try {
            List<String> allLines = Files.readAllLines(Paths.get("data.txt"));
            for(String line : allLines) {
                char specifier = line.charAt(8);
                int content_start_number = 14;
                if(specifier == 'T') {
                    String content = line.substring(content_start_number);
                    Task todotask = new ToDos(content);
                    tasks.add(todotask);
                } else if(specifier == 'D') {
                    String content = line.substring(content_start_number).split("\\s\\(")[0];
                    String deadline1 = line.substring(content_start_number).split("\\s\\(")[1];
                    String deadline = deadline1.substring(0, deadline1.length() - 1);
                    Task deadlineTask = new DeadLines(content, deadline);
                    tasks.add(deadlineTask);
                } else if(specifier == 'E') {
                    String content = line.substring(content_start_number).split("\\s\\(")[0];
                    String dates1 = line.substring(content_start_number).split("\\s\\(")[1];
                    String dates = dates1.substring(0, dates1.length() - 1);
                    Task eventTask = new Events(content, dates);
                    tasks.add(eventTask);
                } else {
                    System.out.println("     Unknown task type, this line will be ignored.");
                }
            }
        } catch(IOException e) {
            System.out.println("     An error occurred.");
            e.printStackTrace();
        }
        System.out.println("     File read completed.");
        return tasks;
    }

    public static void writeFile(ArrayList<Task> tasks) throws IOException {
        String filePath = "data/data.txt";
        String folderPath = "data";
        File file = new File(filePath);
        File folder = new File(folderPath);
        if(folder.mkdir()) {
            System.out.println("     folder data is created.");
        } else {
            System.out.println("     folder data already exists.");
        }

        try {
            if(file.createNewFile()) {
                System.out.println("     file data is created.");
            } else {
                System.out.println("     file data already exists.");
            }
        } catch (IOException e) {
            System.out.println("     An error occurred.");
            e.printStackTrace();
        }

        FileWriter fw = new FileWriter("data/data.txt");
        for(Task thistask : tasks) {
            fw.write(thistask.toString());
        }
        fw.close();
        System.out.println("     File written completed.");

    }
}
