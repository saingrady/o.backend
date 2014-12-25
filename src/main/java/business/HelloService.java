package business;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String home() {
        return "home";
    }

    public String hello() {
        return "hello";
    }

}
