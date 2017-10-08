package hw.spring.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@CrossOrigin
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/docs")
    public String getDocs() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/var/local/hw-spring/swagger.json"));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            return "file not found!";
        } catch (IOException e) {
            return "IO problem!";
        }
    }
    
}
