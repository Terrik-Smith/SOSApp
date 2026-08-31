package com.soshelp.service;

import com.soshelp.model.HelpType;
import com.soshelp.model.SOSRequest;
import com.soshelp.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SOSHelpService {
    private static final double EARTH_RADIUS_KM = 6371.0;

    private final List<User> allUsers = new ArrayList<>();
    private final Map<HelpType, ArrayList<User>> usersByType = new HashMap<>();

    public void registerUser(User user) {
        allUsers.add(user);
        for (HelpType type : user.getSupportedTypes()) {
            usersByType.computeIfAbsent(type, ignored -> new ArrayList<>()).add(user);
        }
    }

    public List<UserMatch> findNearbyHelpers(SOSRequest request) {
        ArrayList<User> candidates = usersByType.getOrDefault(request.getHelpType(), new ArrayList<>());
        List<UserMatch> matches = new ArrayList<>();
        for (User user : candidates) {
            double distance = calculateDistanceKm(request.getLatitude(), request.getLongitude(), user.getLatitude(), user.getLongitude());
            if (distance <= request.getRadiusKm()) {
                matches.add(new UserMatch(user, distance));
            }
        }
        matches.sort(Comparator.comparingDouble(UserMatch::distanceKm));
        return matches;
    }

    public static double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public record UserMatch(User user, double distanceKm) {
    }
}
