package org.bpoller.dockerjava.core;

import com.github.dockerjava.jaxrs.DockerCmdExecFactoryImpl;
import org.bpoller.dockerjava.api.command.CreateNetworkCmd;

public class MyDockerCmdExecFactoryImpl extends DockerCmdExecFactoryImpl implements MyDockerCmdExecFactory {

    @Override
    public CreateNetworkCmd.Exec createCreateNetworkCmdExec() {
        return new CreateNetworkCmdExec(getBaseResource(), getDockerClientConfig());
    }
}
