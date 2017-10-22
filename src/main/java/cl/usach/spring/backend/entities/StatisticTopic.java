package cl.usach.spring.backend.entities;



import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statistic_topic")
public class StatisticTopic {



    @Id
    @Column(name="statistictopic_id", unique = true, nullable = false)
    private int statisticTopicId;

    @ManyToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column
    private int value;

    @Column
    private Timestamp timeStamp;

    public int getStatisticTopicId() {
        return this.statisticTopicId;
    }

    public void setStatisticTopicId(int statisticTopicId) {
        this.statisticTopicId = statisticTopicId;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
