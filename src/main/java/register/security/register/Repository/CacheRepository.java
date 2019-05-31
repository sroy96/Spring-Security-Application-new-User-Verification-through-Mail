package register.security.register.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import register.security.register.Model.Cache;

@Repository
public interface CacheRepository extends MongoRepository<Cache,String> {
    Cache findByHashcode(int hashcode);
}
