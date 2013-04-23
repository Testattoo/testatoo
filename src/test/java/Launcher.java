import com.ovea.tajin.server.ContainerConfiguration;
import com.ovea.tajin.server.Server;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Launcher {

    public static void main(String... arg) {

        System.setProperty("domain", "localhost:8080");

        ContainerConfiguration.create()
                .webappRoot("src/test/webapp")
                .buildContainer(Server.JETTY9)
                .start();
    }

}
