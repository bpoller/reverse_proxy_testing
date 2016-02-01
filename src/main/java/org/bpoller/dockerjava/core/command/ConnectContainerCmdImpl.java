package org.bpoller.dockerjava.core.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dockerjava.api.command.DockerCmdSyncExec;
import com.github.dockerjava.core.command.AbstrDockerCmd;
import org.bpoller.dockerjava.api.command.ConnectContainerCmd;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConnectContainerCmdImpl extends AbstrDockerCmd<ConnectContainerCmd, Void> implements ConnectContainerCmd {

    private String networkId = "";

    @JsonProperty("Container")
    private String containerId = "";

    public ConnectContainerCmdImpl(DockerCmdSyncExec<ConnectContainerCmd, Void> execution, String containerId) {
        super(execution);
        checkNotNull(containerId, "containerId was not specified");
        this.containerId = containerId;
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public String getContainerId() {
        return containerId;
    }

    @Override
    public ConnectContainerCmd toNetwork(String networkId) {
        checkNotNull(networkId, "networkId was not specified");
        this.networkId = networkId;
        return this;
    }
}