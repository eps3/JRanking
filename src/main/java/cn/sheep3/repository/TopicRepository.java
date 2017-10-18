package cn.sheep3.repository;

import cn.sheep3.entity.Topic;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sheep3 on 2017/10/17.
 */
public interface TopicRepository extends CrudRepository<Topic, Long> {

    public Topic findByName(String name);
}
