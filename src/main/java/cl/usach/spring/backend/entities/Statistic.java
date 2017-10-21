package cl.usach.spring.backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="statistics")
//@NamedQuery(name="Statistics.findAll", query="SELECT s FROM Statistics s")
public class Statistic {

    @Id
    @Column(name = "statistic_id", unique = true, nullable = false)
    private int statisticId;

    @Column(name = "statistic_name",nullable = false)
    private String statisticName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "statistic")
    private List<User> users;

    @JsonIgnore
    @JoinTable(name = "statistic_topic",
            joinColumns=@JoinColumn(name="statistic_id", referencedColumnName="statistic_id"),
            inverseJoinColumns=@JoinColumn(name="topic_id", referencedColumnName="topic_id"))
    @ManyToMany
    private List<Topic> topics;



    @OneToMany(mappedBy = "statistic")
    private List<StatisticTopic> statisticTopics;

    public int getStatisticId() { return this.statisticId;}

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Topic> getTopics() {
        return this.topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getStatisticName() {
        return this.statisticName;
    }

    public void setStatisticName(String name) {
        this.statisticName = name;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StatisticTopic> getStatisticTopics() {
        return statisticTopics;
    }

    public void setStatisticTopics(List<StatisticTopic> statisticTopics) {
        this.statisticTopics = statisticTopics;
    }
}
