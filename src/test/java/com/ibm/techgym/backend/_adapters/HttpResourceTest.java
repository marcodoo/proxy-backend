package com.ibm.techgym.backend._adapters;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import com.ibm.techgym.backend._adapters.primary.HttpResource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(HttpResource.class)
public class HttpResourceTest {
    
    @Test
    public void checkApi() {
        when().get("version")
        .then()
          .statusCode(200)
          .body(is("V1.0"));
    }
}
