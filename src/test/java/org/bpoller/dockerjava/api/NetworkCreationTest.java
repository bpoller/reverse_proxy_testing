package org.bpoller.dockerjava.api;

import org.bpoller.dockerjava.api.command.CreateNetworkResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NetworkCreationTest extends DockerTest {

    private static CreateNetworkResponse response;

    @Test
    public void shouldCreateNetwork() {
        response = docker.createNetworkCmd("myNetwork").withDriver("bridge").exec();

        logger.info(response.toString());
        assertTrue("There should be no warning", response.getWarning().isEmpty());
        assertFalse("There should be a network id", response.getId().isEmpty());
    }

    @After
    public void tearDown() {
        docker.removeNetworkCmd(response.getId()).exec();
    }
}
