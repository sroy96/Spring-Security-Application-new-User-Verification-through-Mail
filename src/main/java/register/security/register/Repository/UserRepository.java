package register.security.register.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import register.security.register.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByMail(String mail);
}
