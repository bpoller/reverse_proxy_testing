package org.bpoller.dockerjava.core;

import com.github.dockerjava.api.command.DockerCmdExecFactory;
import org.bpoller.dockerjava.api.command.CreateNetworkCmd;


public interface MyDockerCmdExecFactory extends DockerCmdExecFactory {

    CreateNetworkCmd.Exec createCreateNetworkCmdExec();
}