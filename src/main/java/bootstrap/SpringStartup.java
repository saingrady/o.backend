package bootstrap;

import org.springframework.boot.*;
// one specific controller
// import controller.HelloController;
import org.springframework.context.annotation.Import;


@Import(value = {WebConfigAdapter.class})
// @ImportResource("spring-context1.xml", "spring-context2.xml")
public class SpringStartup {

    public static void main(String[] args) throws Exception {
        // one specific controller
        // SpringApplication.run(HelloController.class, args);

        // all controllers in one
        SpringApplication.run(SpringStartup.class, args);


    }
}
