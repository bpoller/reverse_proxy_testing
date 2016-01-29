package org.bpoller.dockerjava.api.command;

import com.github.dockerjava.api.command.DockerCmdSyncExec;
import com.github.dockerjava.api.command.SyncDockerCmd;

public interface RemoveNetworkCmd extends SyncDockerCmd<Void> {
    interface Exec extends DockerCmdSyncExec<RemoveNetworkCmd, Void> {
    }

    String getNetworkId();
}