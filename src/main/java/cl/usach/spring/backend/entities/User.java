package cl.usach.spring.backend.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
//@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private int userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "username")
    private String username;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name="statistic_id", referencedColumnName = "statistic_id")
    private Statistic statistic;



    @OneToMany(mappedBy = "tweets")
    private List<Tweet> tweets;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Statistic getStatistic() {
        return this.statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public List<Tweet> getTweets() {
        return this.tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
