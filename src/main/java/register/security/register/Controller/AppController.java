package register.security.register.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import register.security.register.Model.Cache;
import register.security.register.Model.Token;
import register.security.register.Model.User;

import register.security.register.Service.AppService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/confirm")
public class AppController {



    @Autowired
    AppService appService;

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public List<User>userList(){
return appService.userList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/token")
    public List<Token>tokenList(){
        return appService.tokenList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cache")
    public List<Cache>cacheList(){
        return appService.cacheList();
    }

    //ModelandView -> Hold both model and view

    @RequestMapping(method = RequestMethod.POST,value = "/new")
public void newpost(@RequestBody User user) throws Exception {
        appService.newuser(user);
    }



    @RequestMapping(value="/verify", method= RequestMethod.GET)
    public  void confirmUserAccount(@RequestParam("token") String confirmationToken)
    {
       appService.confirm(confirmationToken);
    }

//@RequestMapping(value = "/test/{email}",method = RequestMethod.GET)
//    public void confirmtest(@PathVariable("email") String Email){
//        appService.confirmtest(Email);
//}






}
