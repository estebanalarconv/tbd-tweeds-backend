package cl.usach.spring.backend.database;

import cl.usach.spring.backend.lucene.Search;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Neo4j {

    private Driver driver;
    private Session session;

    public List<Map<String, Object>> nodeList = new ArrayList<>();
    public List<Map<String,Object>> relationNodeList = new ArrayList<>();

    public void connect(String uri, String username, String password)
    {
        this.driver = GraphDatabase.driver(uri,AuthTokens.basic(username,password));
        this.session = driver.session();
    }

    public void disconnect()
    {
        session.close();
        driver.close();
    }

    public void deleteAll()
    {
        this.session.run("MATCH (a)-[r]->(b) delete r");
        this.session.run("MATCH (n) DELETE n");
    }

    public void createCategoryNode(String name)
    {
        String query = "MERGE (c: Category"+"{"+"name:"+"'"+name+"'})";
        session.run(query);
    }

    public void createRelationNodes()
    {
        MongoConection mc = new MongoConection();
        Search luceneSearch = new Search();
        List<String> listIds = luceneSearch.getLegalTweets();
        String name = new String();
        String tweet = new String();
        int weight;
        List<String> legalTweets = luceneSearch.getLegalTweets();
        List<String> medicalTweets = luceneSearch.getMedicalTweets();
        List<String> recreationalTweets = luceneSearch.getRecreativeTweets();
        List<DBObject> tweetsLegal = mc.findManyTweetData(legalTweets);
        List<DBObject> tweetsMedical = mc.findManyTweetData(medicalTweets);
        List<DBObject> tweetsRecreational = mc.findManyTweetData(recreationalTweets);

        deleteAll();
        createCategoryNode("Legalización");
        createCategoryNode("Medicina");
        createCategoryNode("Recreación");


        //LEGALES
        for (int i = 0; i<30; i++)
        {
            if(medicalTweets.contains(legalTweets.get(i)))
            {
                System.out.println("ENTRAMOS A LEGALES Y MEDICOS");
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                                            + "  match (c:Category) where c.name='Legalización' "
                                            + "  merge (u)-[r:Tweet]->(c)");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Medicina' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }

            else if (recreationalTweets.contains(legalTweets.get(i)))
            {
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Legalización' "
                        + "  merge (u)-[r:Tweet]->(c)");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Recreación' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }

            else
            {
                System.out.println("SOLO LEGALES");
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Legalización' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }
        }

        for (int i = 0; i<30; i++)
        {
            if(legalTweets.contains(medicalTweets.get(i)))
            {
                System.out.println("ENTRAMOS A MEDICOS LEGALES");
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Medicina' "
                        + "  merge (u)-[r:Tweet]->(c)");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Legalización' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }

            else if (recreationalTweets.contains(medicalTweets.get(i)))
            {
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Medicina' "
                        + "  merge (u)-[r:Tweet]->(c)");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Recreación' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }

            else
            {
                System.out.println("SOLO MEDICOS");
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Medicina' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }
        }

        for (int i = 0; i<30; i++)
        {
            if(legalTweets.contains(recreationalTweets.get(i)))
            {
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Recreación' "
                        + "  merge (u)-[r:Tweet]->(c)");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Legalización' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }

            else if (medicalTweets.contains(recreationalTweets.get(i)))
            {
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("merge (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Recreación' "
                        + "  merge (u)-[r:Tweet]->(c)");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Medicina' "
                        + "  merge (u)-[r:Tweet]->(c)");
            }

            else
            {
                System.out.println("SOLO RECREATIVOS");
                name = tweetsLegal.get(i).get("userName").toString();
                System.out.println(name);
                tweet = tweetsLegal.get(i).get("text").toString();//.replaceAll("'","\"");
                System.out.println(tweet);
                session.run("MERGE (u: User {name:'"+ name +"',tweet:'"+tweet+"'})");
                session.run("match (u: User) where u.name='"+name+"'"
                        + "  match (c:Category) where c.name='Recreación' "
                        + "  MERGE (u)-[r:Tweet]->(c)");
            }
        }

    }

    private Map<String, Object> mapDouble(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    private Map<String, Object> quadrupleMap(String key1, Object value1, String key2, Object value2,
                                             String key3, Object value3, String key4, Object value4)
    {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        result.put(key4, value4);
        return result;
    }
    public void getNodes()
    {
        int id = 0;
        List<String> added = new ArrayList<String>();

        StatementResult nodes = session.run("MATCH (u:User) -[r:Tweet]-(c:Category) return u.name as userName, u.tweet as tweet");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            if(added.contains(record.get("userName").asString()))
            {

            }
            else
            {
                nodeList.add(quadrupleMap("id",id,
                        "userName",record.get("userName").asString(),
                        "tweet",record.get("tweet").asString(),
                        "peso",4));
                id++;
                added.add(record.get("userName").asString());
            }
        }

        nodes = session.run("match (c:Category) return c.name as nombre");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            nodeList.add(quadrupleMap("id",id,
                    "userName",record.get("nombre").asString(),
                    "tweet","",
                    "peso",9));
            id++;
        }
    }

    public void getRelationNodes()
    {
        int userIndex = -1;
        int categoryIndex = -1;
        StatementResult relation = session.run("MATCH (u:User) -[r:Tweet]-(c:Category) return u.name as userName, u.tweet as tweet, c.name as category");
        while(relation.hasNext())
        {
            Record record = relation.next();
            for (int i = 0; i<nodeList.size();i++)
            {
                if(nodeList.get(i).get("userName").equals(record.get("userName").asString()))
                {
                    userIndex = Integer.parseInt(nodeList.get(i).get("id").toString());

                    for (int j = 0; j<nodeList.size();j++)
                    {
                        if(nodeList.get(j).get("userName").equals(record.get("category").asString()))
                        {
                            categoryIndex = Integer.parseInt(nodeList.get(j).get("id").toString());
                            break;
                        }
                    }
                }
            }
            relationNodeList.add(mapDouble("source",userIndex,
                    "target",categoryIndex));
        }
    }

    public Map<String, Object> getGraph()
    {
        this.getNodes();
        this.getRelationNodes();
        return mapDouble("nodes",nodeList,"links",relationNodeList);
    }
}
