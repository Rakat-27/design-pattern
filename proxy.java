public class Client {
    public static void main(String[] args) {

        Terminal terminal = new ProxyPowershell();
        terminal.run("mkdir design pattern", "user");
        terminal.run("rm -rf", "user");
        terminal.run("rm -rf", "admin");

    }
}

public class Powershell implements Terminal {

    @Override
    public void run(String command, String user) {
        System.out.println(command + " run as " + user + " successfully");
    }
}

import java.util.ArrayList;
import java.util.List;

public class ProxyShell implements Terminal {
    private Powershell powershell;
    private List<String> vulnerableCommand;

    public ProxyShell() {
        powershell = new Powershell();
        vulnerableCommand = new ArrayList<>();
        vulnerableCommand.add("rm -rf");
    }

    @Override
    public void run(String command, String user) {
        if (vulnerableCommand.contains(command) && !"admin".equals(user)) {
            System.out.println("Can't run command: " + command);
        } else {
            powershell.run(command, user);
        }
    }
}

public class ProxyPowershell implements Terminal {
    private Terminal powershell;

    public ProxyPowershell() {
        this.powershell = new ProxyShell();
    }

    @Override
    public void run(String command, String user) {
        if (user.equals("admin")) {
            powershell.run(command, user);
        } else {
            System.out.println("Access denied for user " + user);
        }
    }
}

public interface Terminal {
    void run(String command, String user);
}
