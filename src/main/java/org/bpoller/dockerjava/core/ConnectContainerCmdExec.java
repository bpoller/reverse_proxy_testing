package org.bpoller.dockerjava.core;

import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.AbstrSyncDockerCmdExec;
import org.bpoller.dockerjava.api.command.ConnectContainerCmd;
import org.bpoller.dockerjava.api.command.CreateNetworkResponse;
import org.bpoller.dockerjava.api.command.RemoveNetworkCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.client.Entity.entity;


public class ConnectContainerCmdExec extends AbstrSyncDockerCmdExec<ConnectContainerCmd, Void> implements ConnectContainerCmd.Exec {


    private static final Logger LOGGER = LoggerFactory.getLogger(CreateNetworkCmdExec.class);

    public ConnectContainerCmdExec(WebTarget baseResource, DockerClientConfig dockerClientConfig) {
        super(baseResource, dockerClientConfig);
    }

    @Override
    protected Void execute(ConnectContainerCmd command) {
        WebTarget webResource = getBaseResource().path("/networks/" + command.getNetworkId() + "/connect");
        LOGGER.trace("POST: {} ", webResource);
        webResource.request().accept(MediaType.APPLICATION_JSON)
                .post(entity(command, MediaType.APPLICATION_JSON));
        return null;
    }
}