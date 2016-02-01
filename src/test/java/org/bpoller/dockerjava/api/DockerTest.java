package org.bpoller.dockerjava.api;

import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.PullImageResultCallback;
import org.bpoller.dockerjava.core.MyDockerClientImpl;
import org.bpoller.dockerjava.core.MyDockerCmdExecFactoryImpl;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class DockerTest {

    protected static final Logger logger = LoggerFactory.getLogger(DockerTest.class);

    protected static MyDockerClientImpl docker;

    @BeforeClass
    public static void init() {
        MyDockerClientImpl dockerClient = MyDockerClientImpl.getInstance("unix:///var/run/docker.sock");
        dockerClient.withDockerCmdExecFactory(new MyDockerCmdExecFactoryImpl());
        docker = dockerClient;
    }

    protected boolean existsLocally(String searchString) {
        List<Image> dockerSearch = docker.listImagesCmd().exec();
        return dockerSearch.stream().filter(image -> asList(image.getRepoTags()).stream()
                .filter(tag -> tag.contains(searchString)).count() > 0)
                .count() > 0;
    }

    protected void pullImageIfNotExists(String imageName) {
        if (!existsLocally(imageName)) {
            PullImageResultCallback imagePulled = new PullImageResultCallback();
            docker.pullImageCmd(imageName).exec(imagePulled);
            imagePulled.awaitSuccess();
        }
    }
}