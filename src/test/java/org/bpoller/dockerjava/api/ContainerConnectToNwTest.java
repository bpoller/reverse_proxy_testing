package org.bpoller.dockerjava.api;


import com.github.dockerjava.api.command.InspectContainerResponse;
import org.junit.After;
import org.junit.Before;

public class ContainerConnectToNwTest extends DockerTest {

    private static String networkId;
    private static String containerId;

    @Before
    public void setup() {
        networkId = docker.createNetworkCmd("myNetwork").withDriver("bridge").exec().getId();

        pullImageIfNotExists("mongo:latest");
        containerId = docker.createContainerCmd("mongo:latest").withName("myMongo").exec().getId();
        docker.startContainerCmd(containerId);
    }


    public void shouldConnectContainerToNetwork() {

        docker.connectContainer(containerId).toNetwork(networkId);

        InspectContainerResponse inspectContainerResponse = docker.inspectContainerCmd(containerId).exec();
        logger.info("Inspecting container. Bridge is {}", inspectContainerResponse.getNetworkSettings().getBridge());
    }

    @After
    public void tearDown() {
        docker.removeContainerCmd(containerId).exec();
        docker.removeNetworkCmd(networkId).exec();
    }
}