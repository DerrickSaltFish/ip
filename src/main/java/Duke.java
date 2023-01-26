import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            System.out.println(line + "     " + input + "\n" + line);
            input = in.nextLine();
        }
        System.out.println(line + "     Bye. Hope to see you again soon!" + "\n" + line);
    }
}
