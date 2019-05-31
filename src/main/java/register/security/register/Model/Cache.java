package register.security.register.Model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cache {
    @Id
    private String  Cacheid;
    private int hashcode;
    private User user;



    public Cache() {
    }

    public Cache(int hash_code, User user) {
        this.hashcode = hashcode;
        this.user = user;
    }

    public String getCacheid() {
        return Cacheid;
    }

    public void setCacheid(String cache_id) {
        Cacheid = cache_id;
    }

    public int getHashcode() {
        return hashcode;
    }

    public void setHashcode(int hash_code) {
        this.hashcode = hash_code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
