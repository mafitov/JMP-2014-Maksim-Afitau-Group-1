
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class Main {
    public  static void main(String[] args) {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();

        System.out.println("You started this application with following parameters:");
        for (String arg : arguments) {
            System.out.println(arg);
        }
    }
}
