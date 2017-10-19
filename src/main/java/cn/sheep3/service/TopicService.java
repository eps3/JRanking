package cn.sheep3.service;

import cn.sheep3.entity.Topic;
import cn.sheep3.entity.UserTopic;
import cn.sheep3.exception.KnownSystemException;
import cn.sheep3.model.TopicForm;
import cn.sheep3.repository.TopicRepository;
import cn.sheep3.repository.UserTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sheep3 on 2017/10/17.
 */
@Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserTopicRepository userTopicRepository;

    @Autowired
    private UserService userService;


    public Topic findByName(String name) {
        return topicRepository.findByName(name);
    }

    public void addTopic(TopicForm topicForm) throws KnownSystemException {
        if (findByName(topicForm.getName()) != null) {
            throw new KnownSystemException("该主题名已存在");
        }
        Topic topic = new Topic();
        topic.setName(topicForm.getName());
        topic.setDesc(topicForm.getDesc());
        topic.setUserId(userService.getUser().getId());
        topicRepository.save(topic);
    }

    public Iterable<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    public Iterable<Topic> getAllFollowTopic() {
        return topicRepository.findAllByFollowUserId(userService.getUser().getId());
    }

    public Topic followTopic(Long topic_id) throws KnownSystemException {
        Topic topic = topicRepository.findOne(topic_id);
        if (topic == null) {
            throw new KnownSystemException("该主题名不存在");
        }
        UserTopic userTopic = new UserTopic();
        userTopic.setTopicId(topic_id);
        userTopic.setUserId(userService.getUser().getId());
        if (userTopicRepository.findFirstByUserIdAndTopicId(userTopic.getUserId(), userTopic.getTopicId()) != null) {
            throw new KnownSystemException("你已订阅该主题");
        }
        userTopicRepository.save(userTopic);
        return topic;
    }

}
