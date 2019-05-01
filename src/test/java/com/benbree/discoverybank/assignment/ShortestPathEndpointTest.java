package com.benbree.discoverybank.assignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.benbree.discoverybank.assignment.config.DatasourceBean;
import com.benbree.discoverybank.assignment.config.PersistenceBean;
import com.benbree.discoverybank.assignment.config.WebServiceBean;
import com.benbree.discoverybank.assignment.dao.EdgeDao;
import com.benbree.discoverybank.assignment.dao.TrafficDao;
import com.benbree.discoverybank.assignment.dao.VertexDao;
import com.benbree.discoverybank.assignment.schema.GetShortestPathRequest;
import com.benbree.discoverybank.assignment.schema.GetShortestPathResponse;
import com.benbree.discoverybank.assignment.service.EntityManagerService;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;


/**
 * Created by Sivaraman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatasourceBean.class, PersistenceBean.class, WebServiceBean.class,
        ShortestPathEndpoint.class, ShortestPathRepository.class, EntityManagerService.class, EdgeDao.class, VertexDao.class,
        TrafficDao.class},
        loader = AnnotationConfigContextLoader.class)
public class ShortestPathEndpointTest {

    @Autowired
    private ShortestPathEndpoint shortestPathEndpoint;

    @Test
    public void verifyThatShortestPathSOAPEndPointIsCorrect() throws Exception {
        // Set Up Fixture
        GetShortestPathRequest shortestPathRequest = new GetShortestPathRequest();
        shortestPathRequest.setName("Moon");

        StringBuilder path = new StringBuilder();
        path.append("Earth (A)\tMoon (B)\t");

        GetShortestPathResponse expectedResponse = new GetShortestPathResponse();
        expectedResponse.setPath(path.toString());

        //Test
        GetShortestPathResponse actualResponse = shortestPathEndpoint.getShortestPath(shortestPathRequest);

        // Verify
        assertThat(actualResponse, sameBeanAs(expectedResponse));
        assertThat(actualResponse.getPath(), sameBeanAs("Earth (A)\tMoon (B)\t"));
    }

}