package org.javers.api;

import org.javers.core.metamodel.object.CdoSnapshot;

/**
 * @author pawel szymczyk
 */
public class CdoSnapshotDto {

    private final String snapshot;

    public static CdoSnapshotDto fromCdoSnapshot(CdoSnapshot s) {
        return new CdoSnapshotDto(s.toString());
    }

    public CdoSnapshotDto(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getSnapshot() {
        return snapshot;
    }
}
