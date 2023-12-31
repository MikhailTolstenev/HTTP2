package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    private final Object copyright;
    private final String date;
    private final String explanation;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    public Post(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
//      this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public Object getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

//    public String getHdurl() {
//        return hdurl;
//    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Post" +
                "\n copyright=" + copyright +
                "\n date=" + date +
                "\n explanation=" + explanation +
                "\n mediaType=" + mediaType +
                "\n serviceVersion =" + serviceVersion +
                "\n title=" + title +
                "\n url=" + url;
    }
}
