package cl.usach.spring.backend.entities;


import javax.persistence.*;

@Entity
@Table(name="tweets")
//@NamedQuery(name="Tweet.findAll", query="SELECT t FROM Tweet t")
public class Tweet {

    @Id
    @Column(name = "tweet_id", unique = true, nullable = false)
    private int tweetId;

    @Column(name = "retweets", nullable = false)
    private int retweets;

    @Column(name = "likes", nullable = false)
    private int likes;

    @Column(name = "post", nullable = false)
    private String post;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    public int getTweetId() {
        return this.tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public int getRetweets() {
        return this.retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
