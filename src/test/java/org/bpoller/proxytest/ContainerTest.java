package org.bpoller.proxytest;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.github.dockerjava.jaxrs.DockerCmdExecFactoryImpl;
import org.bpoller.dockerjava.api.command.CreateNetworkResponse;
import org.bpoller.dockerjava.core.MyDockerClientImpl;
import org.bpoller.dockerjava.core.MyDockerCmdExecFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainerTest {

    private static final Logger logger = LoggerFactory.getLogger(ContainerTest.class);

    private static MyDockerClientImpl docker;

    @BeforeClass
    public static void init() {
        MyDockerClientImpl dockerClient = MyDockerClientImpl.getInstance("unix:///var/run/docker.sock");
        dockerClient.withDockerCmdExecFactory(new MyDockerCmdExecFactoryImpl());
        docker = dockerClient;
    }

    @Test
    public void shouldCreateContainer(){
        CreateNetworkResponse response = docker.createNetworkCmd("myNetwork").withDriver("bridge").exec();
        assertFalse("There should be no warning", response.getWarning().isEmpty());
        assertTrue("There should be a network id", ! response.getId().isEmpty());
    }

    @Test
    public void shouldStartHelloWorldContainer() {

        pullImageIfNotExists("busybox:latest");

        CreateContainerResponse container = docker.createContainerCmd("busybox:latest")
                .withCmd("touch")
                .exec();

        logger.info("container with id {} created ", container.getId());


        assertTrue("This should not fail", true);
    }

    private boolean existsLocally(String searchString) {
        List<Image> dockerSearch = docker.listImagesCmd().exec();
        return dockerSearch.stream().filter(image -> asList(image.getRepoTags()).stream()
                .filter(tag -> tag.contains(searchString)).count() > 0)
                .count() > 0;
    }

    private void pullImageIfNotExists(String imageName) {
        if (!existsLocally(imageName)) {
            PullImageResultCallback imagePulled = new PullImageResultCallback();
            docker.pullImageCmd(imageName).exec(imagePulled);
            imagePulled.awaitSuccess();
        }
    }
}