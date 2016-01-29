package org.bpoller.dockerjava.core;

import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.AbstrSyncDockerCmdExec;
import org.bpoller.dockerjava.api.command.RemoveNetworkCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


public class RemoveNetworkCmdExec extends AbstrSyncDockerCmdExec<RemoveNetworkCmd, Void> implements RemoveNetworkCmd.Exec {


    private static final Logger LOGGER = LoggerFactory.getLogger(CreateNetworkCmdExec.class);

    public RemoveNetworkCmdExec(WebTarget baseResource, DockerClientConfig dockerClientConfig) {
        super(baseResource, dockerClientConfig);
    }

    @Override
    protected Void execute(RemoveNetworkCmd command) {
        WebTarget webResource = getBaseResource().path("/networks/" + command.getNetworkId());

        LOGGER.trace("DELETE: {} ", webResource);
        webResource.request().accept(MediaType.APPLICATION_JSON).delete().close();
        return null;
    }
}
