package cn.sheep3.repository;

import cn.sheep3.entity.UserTopic;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sheep3 on 2017/10/17.
 */
public interface UserTopicRepository extends CrudRepository<UserTopic, Long> {

    public UserTopic findFirstByUserIdAndTopicId(Long userId, Long topicId);

}
