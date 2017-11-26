package cl.usach.spring.backend.rest;


import cl.usach.spring.backend.database.Neo4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/graph")
public class Neo4jService {

    private Neo4j nj;
    private Map<String, Object> graph;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> showGraph()
    {
        nj = new Neo4j();
        nj.connect("bolt://localhost","neo4j","secret");

        this.graph = nj.getGraph();
        nj.disconnect();
        return this.graph;
    }

}
