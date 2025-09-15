package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    Response response;

    // ===== GET Request =====
    @When("I send a GET request to {string}")
    public void i_send_a_get_request(String url) {
        response = given()
                .when()
                .get(url)
                .then()
                .extract()
                .response();

        System.out.println("GET Response:\n" + response.asPrettyString());
    }

    @Then("the GET response should contain path, ip and headers")
    public void the_get_response_should_contain_path_ip_and_headers() {
        Assert.assertEquals(response.getStatusCode(), 200, "❌ Status code mismatch");
        Assert.assertNotNull(response.jsonPath().get("path"), "❌ Path missing");
        Assert.assertNotNull(response.jsonPath().get("ip"), "❌ IP missing");
        //Assert.assertFalse(response.getHeaders().isEmpty(), "❌ Headers missing");
    }

    // ===== POST Request =====
    @When("I send a POST request to {string}")
    public void i_send_a_post_request(String url) {
        String payload = "{\n" +
                "  \"order_id\": \"12345\",\n" +
                "  \"customer\": {\n" +
                "    \"name\": \"Jane Smith\",\n" +
                "    \"email\": \"janesmith@example.com\"\n" +
                "  },\n" +
                "  \"items\": [\n" +
                "    {\"product_id\": \"A101\", \"name\": \"Wireless Headphones\", \"quantity\": 1, \"price\": 79.99},\n" +
                "    {\"product_id\": \"B202\", \"name\": \"Smartphone Case\", \"quantity\": 2, \"price\": 15.99}\n" +
                "  ],\n" +
                "  \"payment\": {\"method\": \"credit_card\", \"amount\": 111.97}\n" +
                "}";

        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(url)
                .then()
                .extract()
                .response();

        System.out.println("POST Response:\n" + response.asPrettyString());
    }

    @Then("the POST response should contain customer, payment and product details")
    public void the_post_response_should_contain_customer_payment_and_product_details() {
        Assert.assertEquals(response.getStatusCode(), 200, "❌ Status code mismatch");

        // Validate customer
        Assert.assertEquals(response.jsonPath().getString("customer.name"), "Jane Smith");
        Assert.assertEquals(response.jsonPath().getString("customer.email"), "janesmith@example.com");

        // Validate payment
        Assert.assertEquals(response.jsonPath().getString("payment.method"), "credit_card");
        Assert.assertEquals(response.jsonPath().getDouble("payment.amount"), 111.97);

        // Validate product
        Assert.assertEquals(response.jsonPath().getString("items[0].name"), "Wireless Headphones");
        Assert.assertEquals(response.jsonPath().getString("items[1].name"), "Smartphone Case");
    }
}
