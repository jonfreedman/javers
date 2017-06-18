package org.javers.api;

import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pawel szymczyk
 */
@Component
public class JaversService {

    private final Javers javers;

    @Autowired
    public JaversService(Javers javers) {
        this.javers = javers;
    }

    public List<CdoSnapshot> findSnapshots(EntityQueryDto queryDto) {
        JqlQuery jqlQuery = null;
        try {
            jqlQuery = QueryBuilder.byInstanceId(queryDto.getInstanceId(), Class.forName(queryDto.getClassName())).build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return javers.findSnapshots(jqlQuery);
    }
}
