package testExamples;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ReqResExamplesTests {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getAllUsers_check_ResponseCode_ContentType() {
        given().
                when().
                get("users").
                then().
                log().all().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    @Test
    public void getAllUsers_check_Length_Values() {
        given().
                when().
                get("users").
                then().
                log().ifValidationFails().
                assertThat()
                .body("data", hasSize(6))
                .body("data[1].email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void getAllUsers_check_Properties_Items() {

        given().
                when().
                get("users").
                then().
                log().ifValidationFails().
                assertThat()
                .body("data.email", hasItem("janet.weaver@reqres.in")) //Check if a value is present in the array
                .body("data", everyItem(hasKey("first_name"))) //Every item of the array has the property
                .body("data", everyItem(not(hasKey("age"))))//There is no "age" property in any item
                .body("data.avatar", everyItem(notNullValue())); //Not null value
    }

    @Test
    public void getAllUsers_check_Cookie_Header() {
        given().
                when().
                get("users").
                then().
                log().ifValidationFails().
                assertThat()
                .cookie("__cfduid")
                .header("Connection", "keep-alive");
    }

    @Test
    public void getAllUsers_ReuseChecks() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectContentType(ContentType.JSON);
        builder.expectCookie("__cfduid");
        ResponseSpecification responseSpec = builder.build();

        given().
                when().
                get("users").
                then().
                log().ifValidationFails()
                .spec(responseSpec);
    }

    @Test
    public void getAllUsers_QueryParameters() {
        given().
                when().
                param("page", 2). //Query params
                get("users").
                then().
                log().ifValidationFails().
                assertThat().
                body("page", is(2));
    }

    @Test
    public void getAllUsers_PathParameters() {
        given().
                when().
                pathParam("userId", 2). //Query params
                get("users/{userId}").
                then().
                log().ifValidationFails().
                assertThat().
                body("data.id", is(2));
    }

    @Test
    public void postUser_MapToJSon() {
        String name = "John";
        String job = "QA Automation";

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", name);
        jsonAsMap.put("job", job);

        given().
                contentType(ContentType.JSON)
                .body(jsonAsMap).
                when().
                post("users").
                then().
                log().all().
                assertThat().
                statusCode(201) .body("name", is(name))
                .body("job", is(job))
                .body("id", notNullValue());
    }

    @Test
    public void postUser_ObjectToJSon() {
        Person person = new Person("John", "QA Automation");

        given().
                contentType(ContentType.JSON)
                .body(person).
                when().
                post("users").
                then().
                log().all().
                assertThat().
                statusCode(201)
                .body("name", is(person.name))
                .body("job", is(person.job))
                .body("id", notNullValue());
    }
}
