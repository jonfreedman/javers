package org.javers.api;

/**
 * @author pawel szymczyk
 */
public class EntityQueryDto {
    private String instanceId;
    private String className;

    public EntityQueryDto(String instanceId, String className) {
        this.instanceId = instanceId;
        this.className = className;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getClassName() {
        return className;
    }
}
