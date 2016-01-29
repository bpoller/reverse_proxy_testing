package org.bpoller.dockerjava.api.command;


import com.github.dockerjava.api.command.DockerCmdSyncExec;
import com.github.dockerjava.api.command.SyncDockerCmd;

public interface CreateNetworkCmd extends SyncDockerCmd<CreateNetworkCmdResponse> {

    interface Exec extends DockerCmdSyncExec<CreateNetworkCmd, CreateNetworkCmdResponse> {
    }

    String getName();

    String getDriver();

    CreateNetworkCmd withName(String name);

    CreateNetworkCmd withDriver(String driver);
}
