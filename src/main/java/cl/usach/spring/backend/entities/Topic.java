package cl.usach.spring.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
//@NamedQuery(name = "Topic.findAll", query = "SELECT t FROM topics to")
public class Topic {

    @Id
    @Column(name = "topic_id",unique = true,nullable = false)
    private int topicId;

    @Column(name = "topic_name",nullable = false)
    private String topicName;

    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="topics")
    @JsonIgnore
    private List<Statistic> statistics;

    @OneToMany(mappedBy = "topic")
    private List<StatisticTopic> statisticTopics;

    public int getTopicId() {
        return this.topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Statistic> getStatistics() {
        return this.statistics;
    }

    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    public List<StatisticTopic> getStatisticTopics() {
        return statisticTopics;
    }

    public void setStatisticTopics(List<StatisticTopic> statisticTopics) {
        this.statisticTopics = statisticTopics;
    }
}
