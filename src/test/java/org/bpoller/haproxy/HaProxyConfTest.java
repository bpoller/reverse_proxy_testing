package org.bpoller.haproxy;


import com.github.dockerjava.api.model.Network;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class HaProxyConfTest extends DockerTest {

    private static String networkName = "myNetwork";
    private static String containerName = "myMongo";
    private static String containerId;


    @Before
    public void setup() {

        docker.listContainersCmd().exec()
                .stream()
                .filter(container -> asList(container.getNames()).contains("/" + containerName))
                .forEach(container -> {
                    docker.stopContainerCmd(containerName).exec();
                    docker.removeContainerCmd(containerName).exec();
                });

        docker.listNetworksCmd().exec().
                stream()
                .filter(network -> network.getName().equals(networkName))
                .forEach(network -> docker.removeNetworkCmd(network.getId()).exec());


        docker.createNetworkCmd().withName(networkName).withDriver("bridge").exec();
        pullImageIfNotExists("mongo:latest");
        containerId = docker.createContainerCmd("mongo:latest").withName(containerName).exec().getId();

        docker.startContainerCmd(containerName).exec();
    }


    @Test
    public void shouldConnectContainerToNetwork() {
        docker.connectToNetworkCmd().withContainerId(containerName).withNetworkId(networkName).exec();
        Network network = docker.inspectNetworkCmd().withNetworkId(networkName).exec();

        assertTrue("The container should be connected to the network but it doesn't seem so.", network.getContainers().keySet().contains(containerId));
    }
}