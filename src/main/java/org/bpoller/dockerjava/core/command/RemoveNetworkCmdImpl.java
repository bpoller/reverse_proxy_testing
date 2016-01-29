package org.bpoller.dockerjava.core.command;

import com.github.dockerjava.core.command.AbstrDockerCmd;
import org.bpoller.dockerjava.api.command.RemoveNetworkCmd;

import static com.google.common.base.Preconditions.checkNotNull;

public class RemoveNetworkCmdImpl extends AbstrDockerCmd<RemoveNetworkCmd, Void> implements RemoveNetworkCmd {


    private String networkId;

    public RemoveNetworkCmdImpl(RemoveNetworkCmd.Exec exec, String networkId) {
        super(exec);
        checkNotNull(networkId, "networkId was not specified");
        this.networkId = networkId;
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }
}