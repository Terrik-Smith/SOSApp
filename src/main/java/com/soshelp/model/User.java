package com.soshelp.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final double latitude;
    private final double longitude;
    private final Set<HelpType> supportedTypes;

    public User(String name, double latitude, double longitude, Set<HelpType> supportedTypes) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.supportedTypes = new HashSet<>(supportedTypes);
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Set<HelpType> getSupportedTypes() {
        return Collections.unmodifiableSet(supportedTypes);
    }

    public boolean supports(HelpType type) {
        return supportedTypes.contains(type);
    }
}
