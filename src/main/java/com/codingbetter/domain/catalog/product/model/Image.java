package com.codingbetter.domain.catalog.product.model;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.codingbetter.domain.shared.model.ValueObject;

public class Image implements ValueObject<Image> {

    private final URL url;

    public Image(URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("URL is required");
        }
        try {
            this.url = uri.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL: " + uri, e);
        }
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Image image = (Image) other;
        return sameValueAs(image);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }

    @Override
    public boolean sameValueAs(Image other) {
        return url.equals(other.url);
    }
}