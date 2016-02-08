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

    protected void createNetwork(String networkName) {
        docker.createNetworkCmd().withName(networkName).withDriver("bridge").exec();
    }

    protected void removeNetwork(String networkName) {
        docker.listNetworksCmd().exec().
                stream()
                .filter(network -> network.getName().equals(networkName))
                .forEach(network -> docker.removeNetworkCmd(network.getId()).exec());
    }

    protected void addContainerToNetwork(String containerName, String networkName) {
        docker.connectToNetworkCmd().withContainerId(containerName).withNetworkId(networkName).exec();
    }

    protected void stopAndRemoveContainer(String containerName) {
        docker.listContainersCmd().withShowAll(true).exec()
                .stream()
                .filter(container -> asList(container.getNames()).contains("/" + containerName))
                .forEach(container -> {

                    if (!container.getStatus().contains("Exit")) {
                        docker.stopContainerCmd(containerName).exec();
                    }
                    docker.removeContainerCmd(containerName).exec();
                });
    }

    protected String createAndStartContainer(String image, String containerName) {
        String id = docker.createContainerCmd(image).withName(containerName).exec().getId();
        docker.startContainerCmd(containerName).exec();
        return id;
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