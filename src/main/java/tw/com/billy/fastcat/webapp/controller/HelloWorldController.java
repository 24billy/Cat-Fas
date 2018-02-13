package tw.com.billy.fastcat.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Billy
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/hello")  // 作用等同<url-pattern>
    public String hello(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name,
            @RequestParam(value = "tel", required = false, defaultValue = "0987654321") String tel,
            Model model) 
    {
        model.addAttribute("name", name);
        model.addAttribute("tel", tel);

        return "helloworld";    // 頁面導向 /WEB-INF/views/  搜索檔案類型.jsp
    }
    
    @RequestMapping("/helloBilly")
    public String helloBilly(Model model) {
        model.addAttribute("name", "Billy");
        return "helloworld";
    }
    
    @RequestMapping("/helloRita")
    public String helloRita(Model model) {
        model.addAttribute("name", "Rita");
        return "helloworld";
    }
    
    @RequestMapping("/helloLawrence")
    public String helloLawrence(Model model) {
        model.addAttribute("name", "Lawrence");
        return "helloworld";
    }
    
    @RequestMapping("/gohome")  // 作用等同<url-pattern>
    public String login(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "password", required = false) String password,
            Model model) 
    {
        if(userName != null && userName.length() != 0){
            model.addAttribute("userName", userName);
        }
        if(password != null && password.length() != 0){
            model.addAttribute("password", password);
        }

        return "redirect:home";    // 頁面導向 /WEB-INF/views/  搜索檔案類型.jsp
    }

}