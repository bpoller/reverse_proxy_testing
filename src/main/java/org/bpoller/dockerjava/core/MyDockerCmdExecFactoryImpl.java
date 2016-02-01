package org.bpoller.dockerjava.core;

import com.github.dockerjava.jaxrs.DockerCmdExecFactoryImpl;
import org.bpoller.dockerjava.api.command.ConnectContainerCmd;
import org.bpoller.dockerjava.api.command.CreateNetworkCmd;
import org.bpoller.dockerjava.api.command.RemoveNetworkCmd;

public class MyDockerCmdExecFactoryImpl extends DockerCmdExecFactoryImpl implements MyDockerCmdExecFactory {

    @Override
    public CreateNetworkCmd.Exec createCreateNetworkCmdExec() {
        return new CreateNetworkCmdExec(getBaseResource(), getDockerClientConfig());
    }

    @Override
    public RemoveNetworkCmd.Exec createRemoveNetworkCmdExec() {
        return new RemoveNetworkCmdExec(getBaseResource(), getDockerClientConfig());
    }

    @Override
    public ConnectContainerCmd.Exec createConnectContainerCmdExec() {
        return new ConnectContainerCmdExec(getBaseResource(), getDockerClientConfig());
    }
}
