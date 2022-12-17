package org.natalek;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class AbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String token;
    private static String baseUrl;
    private static String baseUrlLogin;
    private static String username;
    private static String password;
    protected static RequestSpecification requestSpecification;
    protected static RequestSpecification requestSpecificationLogin;
    protected static ResponseSpecification responseSpecification;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        token = prop.getProperty("token");
        baseUrl = prop.getProperty("baseUrl");
        baseUrlLogin = prop.getProperty("baseUrlLogin");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        requestSpecification = new RequestSpecBuilder()
                .addHeader("X-Auth-Token", token)
                .addQueryParam("sort", "createdAt")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        requestSpecificationLogin = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }

    public static Properties getProp() {
        return prop;
    }

    public static InputStream getConfigFile() {
        return configFile;
    }

    public static String getToken() {
        return token;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getBaseUrlLogin() {
        return baseUrlLogin;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public static RequestSpecification getRequestSpecificationLogin() {
        return requestSpecificationLogin;
    }

    public static ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }
}
