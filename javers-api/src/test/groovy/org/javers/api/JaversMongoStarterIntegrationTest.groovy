package org.javers.api

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.javers.core.Javers
import org.javers.core.metamodel.type.EntityType
import org.javers.repository.jql.QueryBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
@RunWith(SpringJUnit4ClassRunner)
@SpringBootTest(classes = [TestApplication, JaversApiAutoConfiguration])
@ActiveProfiles("test")
public class JaversMongoStarterIntegrationTest {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Autowired
    DummyEntityRepository dummyEntityRepository

    @Test
    void "should build default javers instance with auto-audit aspect"() {
        //given
        def dummyEntity = new DummyEntity(1)
        OkHttpClient client = new OkHttpClient();

        Gson gson = new Gson()
        EntityQueryDto entityQueryDto = new EntityQueryDto("1", DummyEntity.class.name)
        RequestBody body = RequestBody.create(JSON, gson.toJson(entityQueryDto));
        Request request = new Request.Builder()
                .url("http://localhost:8080/v1/snapshots")
                .post(body)
                .build();

        //when
        dummyEntityRepository.save(dummyEntity)
        Response response = client.newCall(request).execute();

        //then
        assert response.code() == 200
        def snapshots = javers.findSnapshots(QueryBuilder.byClass(DummyEntity).build())
        assert snapshots.size() == 1
        assert snapshots[0].commitMetadata.properties["key"] == "ok"
        assert snapshots[0].commitMetadata.author == "unauthenticated"
    }

    @Test
    void "should scan given packages for classes with @TypeName"() {
        //expect
        assert javers.getTypeMapping("AnotherEntity") instanceof EntityType
    }
}

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
