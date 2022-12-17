package org.natalek;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class OtherPeoplesPostsTest extends AbstractTest {
    //GET https://test-stand.gb.ru/api/posts?owner=notMe&sort=createdAt&order=ASC&page=1

    @Test
    @DisplayName("Текущая страница не существует. Предыдущая страница = null.")
    void previousPageTest () {
        PostsResponse response = given().spec(getRequestSpecification())
                .queryParam("owner", "notMe")
                .queryParam("order", "ASC")
                .queryParam("page",10000)
                .when()
                .get(getBaseUrl())
                .then()
                .extract()
                .response()
                .body()
                .as(PostsResponse.class);

        assertThat(response.getMeta().getPrevPage().toString(), containsString("null"));
    }

    @Test
    @DisplayName("При запросе несуществующей страницы возвращается пустой массив data.")
    void nextPageTest () {
        PostsResponse response = given().spec(getRequestSpecification())
                .queryParam("owner", "notMe")
                .queryParam("order", "ASC")
                .queryParam("page",10000)
                .when()
                .get(getBaseUrl())
                .then()
                .extract()
                .response()
                .body()
                .as(PostsResponse.class);

        assertThat(response.getData().size(), equalTo(0));
    }

    @Test
    @DisplayName("Кол-во постов на странице = 4.")
    void PostsPerPage10Test () {
        PostsResponse response = given().spec(getRequestSpecification())
                .queryParam("owner", "notMe")
                .queryParam("order", "ASC")
                .queryParam("page",1)
                .when()
                .get(getBaseUrl())
                .then()
                .extract()
                .response()
                .body()
                .as(PostsResponse.class);

        assertThat(response.getData().size(), equalTo(4));
    }

    @Test
    @DisplayName("Проверка сортировки в порядке возрастания (ASC).")
    void ASC_sortTest () {
        PostsResponse response = given().spec(getRequestSpecification())
                .queryParam("owner", "notMe")
                .queryParam("order", "ASC")
                .queryParam("page",1)
                .when()
                .get(getBaseUrl())
                .then()
                .extract()
                .response()
                .body()
                .as(PostsResponse.class);

        for (int i = response.getData().size()-1; i > 0; i--) {
            assertThat(response.getData().get(i).getId(), greaterThan(response.getData().get(i-1).getId()));
        };
    }

    @Test
    @DisplayName("Проверка сортировки в порядке убывания (DESC).")
    void DESC_sortTest () {
        PostsResponse response = given().spec(getRequestSpecification())
                .queryParam("owner", "notMe")
                .queryParam("order", "DESC")
                .queryParam("page",1)
                .when()
                .get(getBaseUrl())
                .then()
                .extract()
                .response()
                .body()
                .as(PostsResponse.class);

        for (int i = response.getData().size()-1; i > 0; i--) {
            assertThat(response.getData().get(i-1).getId(), greaterThan(response.getData().get(i).getId()));
        };
    }

    @Test
    @DisplayName("Вывод всех постов в рандомном порядке (ALL).")
    void ALL_sortTest () {
        given().spec(getRequestSpecification())
                .queryParam("owner", "notMe")
                .queryParam("order", "ALL")
                .queryParam("page",1)
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .spec(getResponseSpecification());
    }
}
