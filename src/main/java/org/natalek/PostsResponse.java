package org.natalek;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "meta"
})
@Data
public class PostsResponse {

    @JsonProperty("data")
    private List<Datum> data = null;
    @JsonProperty("meta")
    private Meta meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "title",
            "description",
            "content",
            "authorId",
            "mainImage",
            "updatedAt",
            "createdAt",
            "labels",
            "delayPublishTo",
            "draft"
    })
    @Data
    public static class Datum {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("content")
        private String content;
        @JsonProperty("authorId")
        private Integer authorId;
        @JsonProperty("mainImage")
        private MainImage mainImage;
        @JsonProperty("updatedAt")
        private String updatedAt;
        @JsonProperty("createdAt")
        private String createdAt;
        @JsonProperty("labels")
        private List<Object> labels = null;
        @JsonProperty("delayPublishTo")
        private Object delayPublishTo;
        @JsonProperty("draft")
        private Boolean draft;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "cdnUrl"
    })
    @Data
    public static class MainImage {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("cdnUrl")
        private String cdnUrl;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "prevPage",
            "nextPage",
            "count"
    })
    @Data
    public class Meta {
        @JsonProperty("prevPage")
        private Integer prevPage;
        @JsonProperty("nextPage")
        private Integer nextPage;
        @JsonProperty("count")
        private Integer count;
    }
}
