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

    @Column(name = "value",nullable = false)
    private int value;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @OneToMany(mappedBy = "users")
    private List<User> users;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "statistic_topic",
            joinColumns=@JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="actor_id"))
    private List<Topic> topics;

    public int getStatisticId() { return this.statisticId;}

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
}
