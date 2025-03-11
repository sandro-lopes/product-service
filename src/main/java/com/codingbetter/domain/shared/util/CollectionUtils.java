package com.codingbetter.domain.shared.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
    private CollectionUtils() {
    }

    public static <T> List<T> initializeList(List<T> list) {
        return list == null ? new ArrayList<>() : list;
    }
} 