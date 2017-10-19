package cn.sheep3.repository;

import cn.sheep3.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sheep3 on 2017/10/17.
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

    public Topic findByName(String name);

    @Query(value = "SELECT * FROM t_topics  where c_id in (select c_topic_id from t_user_topic where c_user_id=:user_id)", nativeQuery = true)
    public Iterable<Topic> findAllByFollowUserId(@Param("user_id") Long user_id);
}
