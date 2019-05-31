package register.security.register.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import register.security.register.Model.Token;

@Repository
public interface TokenRepository extends MongoRepository<Token,String> {
       Token findByConfirmationToken(String confirmation_token);
       Token findByEmail(String Email);
}
