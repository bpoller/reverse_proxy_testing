package org.bpoller.dockerjava.api.command;

import com.github.dockerjava.api.command.DockerCmdSyncExec;
import com.github.dockerjava.api.command.SyncDockerCmd;

public interface ConnectContainerCmd extends SyncDockerCmd<Void> {


    interface Exec extends DockerCmdSyncExec<ConnectContainerCmd, Void> {
    }

    String getNetworkId();

    String getContainerId();

    ConnectContainerCmd toNetwork(String networkId);
}
