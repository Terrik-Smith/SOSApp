package com.soshelp.service;

import com.soshelp.model.HelpType;
import com.soshelp.model.SOSRequest;
import com.soshelp.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SOSHelpServiceTest {

    @Test
    void findsOnlyNearbyHelpersForRequestedTypeSortedByDistance() {
        SOSHelpService service = new SOSHelpService();

        User closeMedical = new User("CloseMedical", 40.7130, -74.0060, Set.of(HelpType.MEDICAL));
        User farMedical = new User("FarMedical", 40.8000, -74.2000, Set.of(HelpType.MEDICAL));
        User closeFood = new User("CloseFood", 40.7130, -74.0060, Set.of(HelpType.FOOD));

        service.registerUser(farMedical);
        service.registerUser(closeFood);
        service.registerUser(closeMedical);

        SOSRequest request = new SOSRequest("Requester", HelpType.MEDICAL, 40.7128, -74.0060, 10.0);
        List<SOSHelpService.UserMatch> matches = service.findNearbyHelpers(request);

        assertEquals(1, matches.size());
        assertEquals("CloseMedical", matches.get(0).user().getName());
        assertTrue(matches.get(0).distanceKm() < 1.0);
    }

    @Test
    void returnsNoMatchesWhenNoUsersSupportRequestedType() {
        SOSHelpService service = new SOSHelpService();
        service.registerUser(new User("Helper", 40.7128, -74.0060, Set.of(HelpType.FOOD)));

        SOSRequest request = new SOSRequest("Requester", HelpType.SAFETY, 40.7128, -74.0060, 5.0);

        assertTrue(service.findNearbyHelpers(request).isEmpty());
    }
}
