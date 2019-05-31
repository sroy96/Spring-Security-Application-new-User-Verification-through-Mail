package register.security.register.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document
public class Token {
    @Id
    private String Token_id;

    private String confirmationToken;
    private Date date;
    private String email;



    public Token() {
    }

    public Token(String confirmationToken, Date date, String email) {
        this.confirmationToken = confirmationToken;
        this.date = date;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken_id() {
        return Token_id;
    }

    public void setToken_id(String token_id) {
        Token_id = token_id;
    }

    public String getconfirmationToken() {
        return confirmationToken;
    }

    public void setconfirmationToken(String confirmation_token) {
        this.confirmationToken = confirmation_token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
