package org.bpoller.dockerjava.api;

import org.junit.Before;
import org.junit.Test;

public class NetworkRemovalTest extends DockerTest {

    private static String networkId;

    @Before
    public void setup() {
        networkId = docker.createNetworkCmd("myNetwork").withDriver("bridge").exec().getId();
    }

    @Test
    public void shouldRemoveNetwork() {
        docker.removeNetworkCmd(networkId).exec();
    }
}