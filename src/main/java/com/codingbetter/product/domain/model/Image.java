package com.codingbetter.product.domain.model;

import java.net.URL;

public class Image {

    private final URL url;

    public Image(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("URL is required");
        }
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }
}
