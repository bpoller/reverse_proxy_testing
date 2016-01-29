package org.bpoller.dockerjava.core.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dockerjava.core.command.AbstrDockerCmd;
import org.bpoller.dockerjava.api.command.CreateNetworkCmd;
import org.bpoller.dockerjava.api.command.CreateNetworkResponse;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateNetworkCmdImpl extends AbstrDockerCmd<CreateNetworkCmd, CreateNetworkResponse> implements CreateNetworkCmd {

    @JsonProperty("Name")
    private String name = "";

    @JsonProperty("Driver")
    private String driver = "";

    @Override
    public String getName() {
        return name;
    }

    public CreateNetworkCmdImpl(CreateNetworkCmd.Exec exec, String name) {
        super(exec);
        withName(name);
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public CreateNetworkCmd withName(String name) {
        checkNotNull(name, "name was not specified");
        this.name = name;
        return this;
    }

    @Override
    public CreateNetworkCmd withDriver(String driver) {
        checkNotNull(driver, "name was not specified");
        this.driver = driver;
        return this;
    }

    @Override
    public CreateNetworkResponse exec() {
        return super.exec();
    }
}