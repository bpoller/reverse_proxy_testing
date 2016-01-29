package org.bpoller.dockerjava.api.command;


import com.github.dockerjava.api.command.DockerCmdSyncExec;
import com.github.dockerjava.api.command.SyncDockerCmd;

public interface CreateNetworkCmd extends SyncDockerCmd<CreateNetworkResponse> {

    interface Exec extends DockerCmdSyncExec<CreateNetworkCmd, CreateNetworkResponse> {
    }

    String getName();

    String getDriver();

    CreateNetworkCmd withName(String name);

    CreateNetworkCmd withDriver(String driver);
}
