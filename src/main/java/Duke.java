import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
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
            if(input.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < 100; i++) {
                    if(tasks[i] != null) {
                        System.out.println("     " + (i + 1) + "." + tasks[i]);
                    } else{
                        break;
                    }
                }
                System.out.println(line);
            } else {
                for(int i = 0; i < 100; i++) {
                    if(tasks[i] == null) {
                        tasks[i] = input;
                        break;
                    }
                }
                System.out.println(line + "     added: " + input + "\n" + line);
            }
                input = in.nextLine();
        }

        System.out.println(line + "     Bye. Hope to see you again soon!" + "\n" + line);
    }
}
