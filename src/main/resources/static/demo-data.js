/*
=========================================================
 SOS CONNECT
 DEMO DATA
=========================================================

 Fake data used to demonstrate SOS Connect
 before the real backend is connected.

 IMPORTANT:
 This is DEMO DATA only.
 No real users are being created.
=========================================================
*/


const SOS_DEMO_VERSION = "2";


const SOS_DEMO_DATA = {

    /*
    =====================================================
    FAKE CONNECTORS
    =====================================================
    */

    connectors: [

        {
            id: "connector-001",

            firstName: "Marcus",
            lastName: "Johnson",

            fullName: "Marcus Johnson",

            photo: "/images/marcus.jpg",

            rating: 4.9,
            reviews: 127,

            completedConnections: 127,

            bio:
                "I enjoy helping people in my community whenever I can.",

            memberSince: "March 2025",

            available: true,

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9650,
                longitude: -75.1800
            }
        },


        {
            id: "connector-002",

            firstName: "Jasmine",
            lastName: "Williams",

            fullName: "Jasmine Williams",

            photo: "/images/jasmine.jpg",

            rating: 4.8,
            reviews: 84,

            completedConnections: 84,

            bio:
                "Always happy to lend a hand and meet people in my community.",

            memberSince: "July 2025",

            available: true,

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9490,
                longitude: -75.1600
            }
        },


        {
            id: "connector-003",

            firstName: "David",
            lastName: "Carter",

            fullName: "David Carter",

            photo: "/images/david.jpg",

            rating: 5.0,
            reviews: 46,

            completedConnections: 46,

            bio:
                "If I can help, I will. That's what community is about.",

            memberSince: "January 2026",

            available: true,

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9580,
                longitude: -75.1450
            }
        },


        {
            id: "connector-004",

            firstName: "Tasha",
            lastName: "Brown",

            fullName: "Tasha Brown",

            photo: "/images/tasha.jpg",

            rating: 4.7,
            reviews: 63,

            completedConnections: 63,

            bio:
                "I love helping neighbors and paying it forward.",

            memberSince: "May 2025",

            available: true,

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9380,
                longitude: -75.1750
            }
        }

    ],


    /*
    =====================================================
    FAKE REQUESTERS
    =====================================================
    */

    requesters: [

        {
            id: "requester-001",

            firstName: "Ava",
            lastName: "Thompson",

            fullName: "Ava Thompson",

            photo: "/images/ava.jpg",

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9526,
                longitude: -75.1652
            }
        },


        {
            id: "requester-002",

            firstName: "Mike",
            lastName: "Rodriguez",

            fullName: "Mike Rodriguez",

            photo: "/images/mike.jpg",

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9600,
                longitude: -75.1550
            }
        },


        {
            id: "requester-003",

            firstName: "Sarah",
            lastName: "Brown",

            fullName: "Sarah Brown",

            photo: "/images/sarah.jpg",

            location: {
                city: "Philadelphia",
                state: "PA",
                latitude: 39.9450,
                longitude: -75.1750
            }
        }

    ],


    /*
    =====================================================
    FAKE SOS REQUESTS
    =====================================================
    */

    requests: [

        {
            id: "request-001",

            requesterId: "requester-001",

            requesterName: "Ava Thompson",

            type: "Rides & Escorts",

            title: "Medical appointment ride",

            description:
                "I need a ride to a medical appointment and help getting inside.",

            location: "Philadelphia, PA",

            latitude: 39.9526,
            longitude: -75.1652,

            distance: 1.8,

            status: "SEARCHING",

            created: "Just now"
        },


        {
            id: "request-002",

            requesterId: "requester-002",

            requesterName: "Mike Rodriguez",

            type: "Home Repairs",

            title: "Leaky kitchen faucet",

            description:
                "My kitchen faucet is leaking and I need help figuring out what is wrong.",

            location: "Philadelphia, PA",

            latitude: 39.9600,
            longitude: -75.1550,

            distance: 2.4,

            status: "SEARCHING",

            created: "4 minutes ago"
        },


        {
            id: "request-003",

            requesterId: "requester-003",

            requesterName: "Sarah Brown",

            type: "Yard Work & Maintenance",

            title: "Help clearing fallen branches",

            description:
                "A storm left several branches in my yard and I need help moving them.",

            location: "Philadelphia, PA",

            latitude: 39.9450,
            longitude: -75.1750,

            distance: 3.1,

            status: "SEARCHING",

            created: "8 minutes ago"
        },


        {
            id: "request-004",

            requesterId: "requester-001",

            requesterName: "Ava Thompson",

            type: "Errands & Deliveries",

            title: "Grocery pickup",

            description:
                "I need help picking up a small grocery order.",

            location: "Philadelphia, PA",

            latitude: 39.9526,
            longitude: -75.1652,

            distance: 1.2,

            status: "SEARCHING",

            created: "11 minutes ago"
        }

    ],


    /*
    =====================================================
    NO ACTIVE CONNECTION AT START
    =====================================================

    A connection is created only when a Connector
    presses ACCEPT.
    =====================================================
    */

    activeConnection: null

};


/*
=========================================================
 INITIALIZE DEMO DATA
=========================================================
*/

function initializeDemoData() {

    const currentVersion =
        localStorage.getItem(
            "sosDemoVersion"
        );


    /*
    If this is a new version of the demo data,
    replace the old demo data.

    This is important because your browser may
    still have the previous demo data saved.
    */

    if (currentVersion !== SOS_DEMO_VERSION) {

        localStorage.setItem(

            "sosDemoConnectors",

            JSON.stringify(
                SOS_DEMO_DATA.connectors
            )
        );


        localStorage.setItem(

            "sosDemoRequesters",

            JSON.stringify(
                SOS_DEMO_DATA.requesters
            )
        );


        localStorage.setItem(

            "sosDemoRequests",

            JSON.stringify(
                SOS_DEMO_DATA.requests
            )
        );


        localStorage.setItem(

            "sosDemoActiveConnection",

            JSON.stringify(
                SOS_DEMO_DATA.activeConnection
            )
        );


        localStorage.setItem(

            "sosDemoVersion",

            SOS_DEMO_VERSION
        );


        /*
        Clear the older connection flag.
        */

        localStorage.removeItem(
            "sosActiveConnection"
        );


        console.log(
            "SOS Connect demo data updated to version " +
            SOS_DEMO_VERSION
        );

    }

}


/*
=========================================================
 GET CONNECTORS
=========================================================
*/

function getDemoConnectors() {

    const data =
        localStorage.getItem(
            "sosDemoConnectors"
        );


    if (!data) {

        return SOS_DEMO_DATA.connectors;

    }


    return JSON.parse(data);

}


/*
=========================================================
 GET AVAILABLE CONNECTORS
=========================================================
*/

function getAvailableDemoConnectors() {

    return getDemoConnectors()
        .filter(

            function(connector) {

                return connector.available === true;

            }

        );

}


/*
=========================================================
 GET REQUESTS
=========================================================
*/

function getDemoRequests() {

    const data =
        localStorage.getItem(
            "sosDemoRequests"
        );


    if (!data) {

        return SOS_DEMO_DATA.requests;

    }


    return JSON.parse(data);

}


/*
=========================================================
 GET ACTIVE CONNECTION
=========================================================
*/

function getDemoActiveConnection() {

    const data =
        localStorage.getItem(
            "sosDemoActiveConnection"
        );


    if (!data) {

        return null;

    }


    return JSON.parse(data);

}


/*
=========================================================
 FIND CONNECTOR BY ID
=========================================================
*/

function getDemoConnectorById(id) {

    const connectors =
        getDemoConnectors();


    return connectors.find(

        function(connector) {

            return connector.id === id;

        }

    );

}


/*
=========================================================
 FIND REQUEST BY ID
=========================================================
*/

function getDemoRequestById(id) {

    const requests =
        getDemoRequests();


    return requests.find(

        function(request) {

            return request.id === id;

        }

    );

}


/*
=========================================================
 FIND REQUESTER BY ID
=========================================================
*/

function getDemoRequesterById(id) {

    const data =
        localStorage.getItem(
            "sosDemoRequesters"
        );


    const requesters =
        data
            ? JSON.parse(data)
            : SOS_DEMO_DATA.requesters;


    return requesters.find(

        function(requester) {

            return requester.id === id;

        }

    );

}


/*
=========================================================
 ACCEPT DEMO REQUEST
=========================================================
*/

function acceptDemoRequest(

    requestId,

    connectorId

) {

    const request =
        getDemoRequestById(
            requestId
        );


    const connector =
        getDemoConnectorById(
            connectorId
        );


    /*
    Make sure both records exist.
    */

    if (!request || !connector) {

        console.error(
            "Could not create demo connection."
        );

        return null;

    }


    /*
    Change the request status.
    */

    request.status =
        "ACCEPTED";


    const requests =
        getDemoRequests();


    localStorage.setItem(

        "sosDemoRequests",

        JSON.stringify(
            requests
        )

    );


    /*
    Connector is no longer available
    while helping someone.
    */

    connector.available =
        false;


    const connectors =
        getDemoConnectors();


    localStorage.setItem(

        "sosDemoConnectors",

        JSON.stringify(
            connectors
        )

    );


    /*
    Create the active connection.
    */

    const connection = {

        requestId:
            requestId,

        connectorId:
            connectorId,

        requesterId:
            request.requesterId,

        status:
            "ACCEPTED",

        acceptedAt:
            new Date().toISOString()

    };


    localStorage.setItem(

        "sosDemoActiveConnection",

        JSON.stringify(
            connection
        )

    );


    /*
    Keep compatibility with the
    current connection pages.
    */

    localStorage.setItem(

        "sosActiveConnection",

        "true"

    );


    return connection;

}


/*
=========================================================
 PASS ON DEMO REQUEST
=========================================================
*/

function passDemoRequest(requestId) {

    const requests =
        getDemoRequests();


    const request =
        requests.find(

            function(item) {

                return item.id === requestId;

            }

        );


    if (request) {

        request.status =
            "PASSED";

    }


    localStorage.setItem(

        "sosDemoRequests",

        JSON.stringify(
            requests
        )

    );

}


/*
=========================================================
 COMPLETE DEMO CONNECTION
=========================================================
*/

function completeDemoConnection() {

    const connection =
        getDemoActiveConnection();


    if (!connection) {

        return;

    }


    connection.status =
        "COMPLETED";


    connection.completedAt =
        new Date().toISOString();


    localStorage.setItem(

        "sosDemoActiveConnection",

        JSON.stringify(
            connection
        )

    );


    /*
    Make the Connector available again.
    */

    const connector =
        getDemoConnectorById(
            connection.connectorId
        );


    if (connector) {

        connector.available =
            true;


        const connectors =
            getDemoConnectors();


        localStorage.setItem(

            "sosDemoConnectors",

            JSON.stringify(
                connectors
            )

        );

    }

}


/*
=========================================================
 SAVE CONNECTOR REVIEW
=========================================================
*/

function saveDemoConnectorReview(

    connectorId,

    rating,

    reviewText

) {

    const connectors =
        getDemoConnectors();


    const connector =
        connectors.find(

            function(item) {

                return item.id === connectorId;

            }

        );


    if (!connector) {

        return;

    }


    const oldRating =
        connector.rating;


    const oldReviews =
        connector.reviews;


    const newReviews =
        oldReviews + 1;


    const newRating =
        (
            (
                oldRating * oldReviews
            )
            +
            rating
        )
        /
        newReviews;


    connector.rating =
        Number(
            newRating.toFixed(1)
        );


    connector.reviews =
        newReviews;


    connector.completedConnections =
        (
            connector.completedConnections || 0
        ) + 1;


    /*
    Keep the written review in demo storage.
    */

    const reviewKey =
        "sosDemoReviews";


    const existingReviews =
        JSON.parse(

            localStorage.getItem(
                reviewKey
            ) || "[]"

        );


    existingReviews.push({

        connectorId:
            connectorId,

        rating:
            rating,

        review:
            reviewText,

        createdAt:
            new Date().toISOString()

    });


    localStorage.setItem(

        reviewKey,

        JSON.stringify(
            existingReviews
        )

    );


    localStorage.setItem(

        "sosDemoConnectors",

        JSON.stringify(
            connectors
        )

    );

}


/*
=========================================================
 RESET ALL DEMO DATA
=========================================================
*/

function resetDemoData() {

    localStorage.removeItem(
        "sosDemoVersion"
    );


    localStorage.removeItem(
        "sosDemoConnectors"
    );


    localStorage.removeItem(
        "sosDemoRequesters"
    );


    localStorage.removeItem(
        "sosDemoRequests"
    );


    localStorage.removeItem(
        "sosDemoActiveConnection"
    );


    localStorage.removeItem(
        "sosDemoReviews"
    );


    localStorage.removeItem(
        "sosActiveConnection"
    );


    console.log(
        "SOS Connect demo data reset."
    );


    location.reload();

}


/*
=========================================================
 START DEMO DATA
=========================================================
*/

initializeDemoData();


console.log(
    "SOS Connect demo data is ready."
);