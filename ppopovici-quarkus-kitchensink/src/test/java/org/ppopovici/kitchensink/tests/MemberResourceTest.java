package org.ppopovici.kitchensink.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.ppopovici.kitchensink.models.Member;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberResourceTest {

    @Order(1)
    @Test
    void shouldListMembers() {
        given().accept(ContentType.JSON)
                .when().get("/members/list")
                .then().statusCode(200)
                .and().body("id", contains(1, 2))
                .and().body("name", contains("John Smith", "George Williams"));
    }

    @Order(2)
    @Test
    void shouldGetMember() {
        given().accept(ContentType.JSON)
                .when().get("/members/1")
                .then().statusCode(200)
                .and().body("id", is(equalTo(1)))
                .and().body("email", is(equalTo("john.smith@mailinator.com")));
    }

    @Order(3)
    @Test
    void shouldCreateNewMember() {
        Member newPerson = new Member();
        
        newPerson.setId(3L);
        newPerson.setName("Jerald MacDonell");
        newPerson.setEmail("jerald.macdonell@mailinator.com");
        newPerson.setPhoneNumber("2125551214");
        
        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newPerson)
                .when().post("/members/register")
                .then()
                .statusCode(200);
    }
}
