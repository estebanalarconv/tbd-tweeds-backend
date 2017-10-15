package cl.usach.spring.backend.entities;


import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name="keywords")
//@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword {

    @Id
    @Column(name="keyword_id", unique=true, nullable=false)
    private int keywordId;

    @Column(name="keyword_name", nullable=false, length=45)
    private String keywordName;

    @Column(name= "value", nullable=false)
    private int value;

    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="keywords")
    @JoinTable(name = "keyword_topic",
            joinColumns=@JoinColumn(name="keyword_id", referencedColumnName="keyword_id"),
            inverseJoinColumns=@JoinColumn(name="topic_id", referencedColumnName="topic_id"))
    private List<Topic> topics;

    public Keyword(){
    }

    public int getKeywordId(){return this.keywordId;}

    public void setKeywordId(int keywordId){this.keywordId=keywordId;}

    public String getKeywordName() {return this.keywordName;}

    public void setKeywordName(String keywordName) {this.keywordName = keywordName;}

    public int getValue() {return this.value;}

    public void setValue(int value) {this.value = value;}

    public List<Topic> getTopics() {return this.topics;}

    public void setTopics(List<Topic> topics) {this.topics = topics;}
}
