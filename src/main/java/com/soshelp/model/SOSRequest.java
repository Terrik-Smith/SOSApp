package com.soshelp.model;

public class SOSRequest {
    private final String requester;
    private final HelpType helpType;
    private final double latitude;
    private final double longitude;
    private final double radiusKm;

    public SOSRequest(String requester, HelpType helpType, double latitude, double longitude, double radiusKm) {
        this.requester = requester;
        this.helpType = helpType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radiusKm = radiusKm;
    }

    public String getRequester() {
        return requester;
    }

    public HelpType getHelpType() {
        return helpType;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRadiusKm() {
        return radiusKm;
    }
}
