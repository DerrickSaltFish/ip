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
            if(instruction == input) {
                content = input;
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
            } else {
                tasks.add(new Task(input));
                System.out.println(line + "     added: " + input + "\n" + line);
            }

            input = in.nextLine();
        }

        System.out.println(line + "     Bye. Hope to see you again soon!" + "\n" + line);
    }
}
