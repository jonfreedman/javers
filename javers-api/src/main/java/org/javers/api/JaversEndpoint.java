package org.javers.api;

import org.javers.core.metamodel.object.CdoSnapshot;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pawel szymczyk
 */
@Controller
public class JaversEndpoint {

    private final JaversService javersService;

    public JaversEndpoint(JaversService javersService) {
        this.javersService = javersService;
    }

    @PostMapping(path = "v1/snapshots")
    @ResponseBody
    public List<CdoSnapshotDto> snapshots(EntityQueryDto queryDto) {
        List<CdoSnapshot> snapshots = javersService.findSnapshots(queryDto);

        return snapshots.stream()
                .map((s) -> CdoSnapshotDto.fromCdoSnapshot(s))
                .collect(Collectors.toList());
    }
}
