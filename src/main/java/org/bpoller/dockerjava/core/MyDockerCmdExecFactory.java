package org.bpoller.dockerjava.core;

import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.command.DockerCmdSyncExec;
import org.bpoller.dockerjava.api.command.ConnectContainerCmd;
import org.bpoller.dockerjava.api.command.CreateNetworkCmd;
import org.bpoller.dockerjava.api.command.RemoveNetworkCmd;


public interface MyDockerCmdExecFactory extends DockerCmdExecFactory {

    CreateNetworkCmd.Exec createCreateNetworkCmdExec();

    RemoveNetworkCmd.Exec createRemoveNetworkCmdExec();

    ConnectContainerCmd.Exec createConnectContainerCmdExec();
}
