package register.security.register.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.stereotype.Service;
import register.security.register.MailFactory.Generate;
import register.security.register.Model.Cache;
import register.security.register.Model.Token;
import register.security.register.Model.User;
import register.security.register.Repository.CacheRepository;
import register.security.register.Repository.TokenRepository;
import register.security.register.Repository.UserRepository;

import java.util.Calendar;

import java.util.List;
import java.util.UUID;

@ServletComponentScan
@Service
@EnableMongoAuditing
public class AppService {
    @Autowired
    Generate generate;

@Autowired
    UserRepository userRepository;

@Autowired
    TokenRepository tokenRepository;

@Autowired
    CacheRepository cacheRepository;


    public List<User> userList() {
        return userRepository.findAll();
    }

    public List<Token>tokenList(){
        return tokenRepository.findAll();
    }

    public List<Cache>cacheList(){
        return cacheRepository.findAll();
    }

        public void newuser(User user) throws Exception {

            String userMail=user.getMail();
            //...Not Saving the user to the user DB
            if(userRepository.findByMail(userMail)!=null){
                System.out.println("Already There");
            }
            else {

                Token token = new Token();
                token.setconfirmationToken(UUID.randomUUID().toString()); //set the confirmation token in token DB
                token.setEmail(userMail);
                Calendar cal = Calendar.getInstance();  //Get Local Time
                token.setDate(cal.getTime()); // set time to token
                tokenRepository.save(token);
                //............***..........token done
                System.out.println("...loading to the token=" + token.getconfirmationToken());
                System.out.println("...loading to the Cache");

                Cache cache = new Cache();

                cache.setHashcode(token.getconfirmationToken().hashCode()); //Create a HashCode for security of the user
                cache.setUser(user);
                cacheRepository.save(cache);

                generate.run(userMail,"To confirm your account, please click here : "
                        +"http://localhost:8080/confirm/verify?token="+ token.getconfirmationToken());

            }

        }



    public String  confirm(String confirmationToken) {

        Token newtoken=tokenRepository.findByConfirmationToken(confirmationToken);

        Calendar cal = Calendar.getInstance();
        if(newtoken.getconfirmationToken()!=null && (cal.getTime().getTime()-newtoken.getDate().getTime())>=0){
            String random = newtoken.getconfirmationToken();
            int newHash= random.hashCode();
            System.out.println("HashCode: "+newHash);
            Cache newCache= cacheRepository.findByHashcode(newHash);
            userRepository.save(newCache.getUser());
            System.out.print("Sucessfully added to the user");
            tokenRepository.delete(newtoken);
            cacheRepository.delete(newCache);
        }
        else{
            System.out.println("error in adding user");
            System.out.print("link expired"+(newtoken.getDate().getTime()-cal.getTime().getTime()));
        }

            return "Account Verified";
    }
}
