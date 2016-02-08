package org.bpoller.haproxy;


import com.github.dockerjava.api.model.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HaProxyConfTest extends DockerTest {

    private static String networkName = "myNetwork";
    private static String containerName = "myMongo";
    private static String containerId;


    @Before
    public void setup() {
        stopAndRemoveContainer(containerName);
        removeNetwork(networkName);
        createNetwork(networkName);

        pullImageIfNotExists("mongo:latest");
        containerId = createAndStartContainer("mongo:latest", containerName);
    }

    @Test
    public void shouldConnectContainerToNetwork() {
        addContainerToNetwork(containerName, networkName);

        Network network = docker.inspectNetworkCmd().withNetworkId(networkName).exec();
        assertTrue("The container should be connected to the network but it doesn't seem so.", network.getContainers().keySet().contains(containerId));
    }

    @After
    public void tearDown() {
        stopAndRemoveContainer(containerName);
        removeNetwork(networkName);

    }
}