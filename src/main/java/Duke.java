import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
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
            } else if(instruction.equals("unmark")) {
                int number = Integer.parseInt(content) - 1;
                Task modified = tasks.get(number).unmarkTask();
                tasks.remove(number);
                tasks.add(number, modified);
                System.out.println(line +"      OK, I've marked this task as not done yet:" + "\n" + "       "  + modified.toString() + "\n" + line);
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
                }
            } else if(instruction.equals("deadline")) {
                String actualContent = content.split("/")[0];
                String time = content.split("/")[1];
                Task deadlineTask = new DeadLines(actualContent, time);
                tasks.add(deadlineTask);
                printAdd(tasks.size(), deadlineTask);
            } else if(instruction.equals("event")) {
                String actualContent = content.split("/")[0];
                String time = content.split("/")[1] + content.split("/")[2];
                Task eventTask = new Events(actualContent, time);
                tasks.add(eventTask);
                printAdd(tasks.size(), eventTask);
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
}
