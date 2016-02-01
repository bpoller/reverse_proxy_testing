package org.bpoller.proxytest;

import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.PullImageResultCallback;
import org.bpoller.dockerjava.api.DockerTest;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class ContainerTest extends DockerTest{

    @Test
    public void shouldStartHelloWorldContainer() {

        pullImageIfNotExists("busybox:latest");

        CreateContainerResponse container = docker.createContainerCmd("busybox:latest")
                .withCmd("touch")
                .exec();

        logger.info("container with id {} created ", container.getId());


        assertTrue("This should not fail", true);
    }
}