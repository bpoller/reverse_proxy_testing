package org.bpoller.dockerjava.api.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateNetworkCmdResponse {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Warning")
    private String warning;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}