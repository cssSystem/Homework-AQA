import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class TestPostmanEchoRequestMethods {

    static Map<String, String> headersMap = new HashMap<>();
    static String baseProtocol = "https";
    static String hostData = "postman-echo.com";
    static RequestSpecification reguest = null;
    static ResponseSpecification success = null;

    @BeforeAll
    public static void setUp() {
        headersMap.put("Cache-Control", "no-cache");
        headersMap.put("Cookie", "cookie=value");
        headersMap.put("Postman-Token", "asterix-Abelix");
        headersMap.put("Host", hostData);
        headersMap.put("User-Agent", "miAgent");
        headersMap.put("Accept", "*/*");
        headersMap.put("Accept-Encoding", "gzip, deflate, br");
        headersMap.put("Connection", "keep-alive");

        reguest = new RequestSpecBuilder()
                .setBaseUri(baseProtocol + "://" + hostData)
                .addHeaders(headersMap)
                .build();
        success = expect()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "headers.host", equalTo(headersMap.get("Host")),
                        "headers.postman-token", equalTo(headersMap.get("Postman-Token")),
                        "headers.accept-encoding", equalTo("gzip, br"),
                        "headers.accept", equalTo(headersMap.get("Accept")),
                        "headers.x-forwarded-proto", equalTo(baseProtocol),
                        "headers.cache-control", equalTo(headersMap.get("Cache-Control")),
                        "headers.user-agent", equalTo(headersMap.get("User-Agent")),
                        "headers.cookie", equalTo(headersMap.get("Cookie"))
                );
    }

    @Test
    public void GETRequest() {
        String basePathData = "/get";
        Map<String, String> quetyParamsMap = new HashMap<>();
        quetyParamsMap.put("foo1", "bar1");
        quetyParamsMap.put("foo2", "bar2");

        given()
                .spec(reguest)
                .queryParams(quetyParamsMap)
                .when()
                .get(basePathData)
                .then()
                .spec(success)
                .and()
                .body("args.foo1", equalTo(quetyParamsMap.get("foo1")),
                        "args.foo2", equalTo(quetyParamsMap.get("foo2")),
                        "url", equalTo(baseProtocol + "://" +
                                headersMap.get("Host") + basePathData +
                                "?foo1=" + quetyParamsMap.get("foo1") + "&foo2=" + quetyParamsMap.get("foo2"))
                );
    }

    @Test
    public void POSTFormData() {
        String basePathData = "/post";
        String contentType = "application/x-www-form-urlencoded; charset=UTF-8";
        Map<String, String> formParamsMap = new HashMap<>();
        formParamsMap.put("foo1", "bar1");
        formParamsMap.put("foo2", "bar2");

        given()
                .spec(reguest)
                .formParams(formParamsMap)
                .header("Content-Type", contentType)
                .when()
                .post(basePathData)
                .then()
                .spec(success)
                .and()
                .body(
                        "args", equalTo(Collections.emptyMap()),
                        "files", equalTo(Collections.emptyMap()),
                        "form.foo1", equalTo(formParamsMap.get("foo1")),
                        "form.foo2", equalTo(formParamsMap.get("foo2")),
                        "json.foo1", equalTo(formParamsMap.get("foo1")),
                        "json.foo2", equalTo(formParamsMap.get("foo2")),
                        "data", equalTo(""),
                        "url", equalTo(baseProtocol + "://" +
                                headersMap.get("Host") + basePathData)
                        , "headers.content-type", equalTo(contentType)
                        , "headers.content-length", equalTo(String.valueOf(getFormBodyLengthBytes(formParamsMap)))
                );
    }

    @Test
    public void POSTRawText() {
        String basePathData = "post";
        String bodyTextRaw = "This is expected to be sent back as part of response body.";

        testRequest(basePathData, bodyTextRaw);
    }

    @Test
    public void PUTRequest() {
        String basePathData = "put";
        String bodyTextRaw = "This is expected to be sent back as part of response body.";

        testRequest(basePathData, bodyTextRaw);
    }

    @Test
    public void PATCHRequest() {
        String basePathData = "patch";
        String bodyTextRaw = "This is expected to be sent back as part of response body.";

        testRequest(basePathData, bodyTextRaw);
    }

    @Test
    public void DELETERequest() {
        String basePathData = "delete";
        String bodyTextRaw = "This is expected to be sent back as part of response body.";

        testRequest(basePathData, bodyTextRaw);
    }

    public static int getFormBodyLengthBytes(Map<String, String> params) {
        String body = params.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return body.getBytes(StandardCharsets.UTF_8).length;
    }

    public void testRequest(String basePath, String bodyTextRaw) {
        String basePathData = "/" + basePath;
        String contentType = "text/plain; charset=UTF-8";

        given()
                .spec(reguest)
                .header("Content-Type", contentType)
                .body(bodyTextRaw)
                .when()
                .request(basePath, basePathData)
                .then()
                .spec(success)
                .and()
                .body(
                        "args", equalTo(Collections.emptyMap()),
                        "files", equalTo(Collections.emptyMap()),
                        "form", equalTo(Collections.emptyMap()),
                        "json", nullValue(),
                        "data", equalTo(bodyTextRaw),
                        "url", equalTo(baseProtocol + "://" +
                                headersMap.get("Host") + basePathData)
                        , "headers.content-type", equalTo(contentType)
                        , "headers.content-length", equalTo(String.valueOf(bodyTextRaw.length()))
                );
    }
}
