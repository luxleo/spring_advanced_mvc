package hello.thymeleafbasic.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBaisc(Model model) {
        model.addAttribute("data", "hello dragon thymeleaf");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String txtUnEscaped(Model model) {
        model.addAttribute("data", "<b>Hello Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("variable")
    public String variable(Model model) {
        User userA = new User("userA", 20);
        User userB = new User("userB", 30);

        List<User> users = new ArrayList<>();
        users.add(userA);
        users.add(userB);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", users);
        model.addAttribute("userMap", userMap);

        return "basic/variable";
    }

    @GetMapping("basic-objects")
    public String basicObj(HttpSession session) {
        session.setAttribute("sessionData", "Hello my Session");
        return "basic/basic-obj";
    }

    @GetMapping("date")
    public String getDate(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("literal")
    public String literal(Model model) {
        model.addAttribute("data", "Dragon");
        return "basic/literal";
    }

    @GetMapping("operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Dragon Data!");
        return "basic/operation";
    }

    @GetMapping("attribute")
    public String attr(){
        return "basic/attribute";
    }

    @GetMapping("each")
    public String each(Model model) {
        addUser(model);
        return "basic/each";
    }

    @GetMapping("condition")
    public String cond(Model model) {
        addUser(model);
        return "basic/cond";
    }

    @GetMapping("javascript")
    public String js(Model model) {
        model.addAttribute("user", new User("userA", 100));
        addUser(model);
        return "basic/js";
    }

    private void addUser(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User("user1", 10));
        users.add(new User("user2", 20));
        users.add(new User("user3", 30));
        model.addAttribute("users", users);
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
