package io.coresdk;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/** RFC 9457 Problem Detail */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetail {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
    private final Map<String, Object> extensions = new HashMap<>();

    public ProblemDetail() {}

    public ProblemDetail(String type, String title, int status) {
        this.type = type;
        this.title = title;
        this.status = status;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getInstance() { return instance; }
    public void setInstance(String instance) { this.instance = instance; }

    @JsonAnyGetter
    public Map<String, Object> getExtensions() { return extensions; }

    @JsonAnySetter
    public void addExtension(String key, Object value) { extensions.put(key, value); }

    public static ProblemDetail unauthorized(String detail) {
        ProblemDetail pd = new ProblemDetail(
            "https://coresdk.io/errors/unauthorized", "Unauthorized", 401);
        pd.setDetail(detail);
        return pd;
    }

    public static ProblemDetail forbidden(String detail) {
        ProblemDetail pd = new ProblemDetail(
            "https://coresdk.io/errors/forbidden", "Forbidden", 403);
        pd.setDetail(detail);
        return pd;
    }
}
