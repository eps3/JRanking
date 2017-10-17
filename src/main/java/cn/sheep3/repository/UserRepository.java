package cn.sheep3.repository;

import cn.sheep3.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sheep3 on 2017/10/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByName(String name);
}
