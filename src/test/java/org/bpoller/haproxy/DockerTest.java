package org.bpoller.haproxy;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.PullImageResultCallback;
import org.junit.BeforeClass;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class DockerTest {

    protected static DockerClient docker;

    @BeforeClass
    public static void init() {

        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                .withApiVersion("1.21")
                .withDockerHost("tcp://127.0.0.1:2375")
                .withDockerTlsVerify("0")
                .build();

        docker = DockerClientBuilder.getInstance(config).build();
    }

    protected boolean existsLocally(String searchString) {
        List<Image> dockerSearch = docker.listImagesCmd().exec();
        return dockerSearch.stream().filter(image -> asList(image.getRepoTags()).contains(searchString))
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