



const apiKey = "abcf678d-570f-3e84-ace0-3dae82ae4ebe";

const crs = new L.Proj.CRS(
    "EPSG:3006",
    "+proj=utm +zone=33 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs",
    {
        resolutions: [4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8],
        origin: [-1200000.0, 8500000.0],
        bounds: L.bounds([-1200000.0, 8500000.0], [4305696.0, 2994304.0])
    }
);

const map = new L.Map("map-new-route", {
    crs: crs,
    continuousWorld: true
});

new L.TileLayer(
    `https://api.lantmateriet.se/open/topowebb-ccby/v1/wmts/token/${apiKey}/1.0.0/topowebb/default/3006/{z}/{y}/{x}.png`,
    {
        maxZoom: 18,
        minZoom: 0,
        continuousWorld: true,
        attribution:
            '&copy; <a href="https://www.lantmateriet.se/en/">Lantmäteriet</a> Topografisk Webbkarta Visning, CCB'
    }
).addTo(map);

map.setView([65.104326, 16.875124], 1.5);
//end of map configuration

//map.pm.setPathOptions({
//  color: 'orange',
//  fillColor: 'green',
//});

//hard-coded routes to start with
/*
let route = [
    [63.43336264092851, 13.100512530890732],
    [63.42612654639262, 13.162583161441411],
    [63.395953086104676, 13.231165979318754],
    [63.38021322970091, 13.168080742484434],
    [63.379097388031624, 13.170713930555567]
];

let route2 = [
    [67.4777659389, 19.88225463636],
    [67.4975352569, 19.76008683650],
    [67.4838912798, 19.59515819672],
    [67.4295552309, 19.72250342069]
];

let route3 = [
    [63.16248165247, 12.36796303705],
    [63.08423965644, 12.42831493833],
    [63.003404162007, 12.22797964426],
    [62.919686257953, 12.42257997620],
    [63.069424547189, 12.58503363623],
    [63.155588700623, 12.38705480770]
];
*/

//let polyline = L.polyline(route, { color: "blue" }).addTo(map);
//let polyline2 = L.polyline(route2, { color: "blue" }).addTo(map);
//let polyline3 = L.polyline(route3, { color: "blue" }).addTo(map);

// skapar en pop-up med koordinater, long/lat
//let popUp = L.popup();

//function onMapClick(e) {
//    popUp
//        .setLatLng(e.latlng)
//        .setContent("Koordinater: " + e.latlng.toString())
//        .openOn(map);
//}

//map.on("click", onMapClick);

//hard-coded markers to start out with
//let marker = L.marker([67.71837131142199, 17.794997304476783]).addTo(map);

// adding leaflet-geoman controls/toolbar with some options to the map
map.pm.addControls({
    position: "topleft",
    drawCircle: false,
    dragMode: false,
    drawPolygon: false,
    drawPolyline: true,
    drawMarker: true,
    drawRectangle: false,
    drawCircleMarker: false,
    cutPolygon: false,
    editMode: false,
    removalMode: false
});

//let position = [];
//let userRouteArray = [];

// listen to vertexes being added to currently drawn layer (called workingLayer)
//map.on('pm:drawstart', ({ workingLayer }) => {
//  workingLayer.on('pm:vertexadded', e => {
//
//    userRouteArray.push(e.latlng.lat + ",  " + e.latlng.lng);
//console.log(userRouteArray.toString());
//
//  });
//});

let latitudes = "";
let longitudes = "";


map.on('pm:create', e => {

    let totaldistance = 0;
    //console.log("draw end")
    map.pm.addControls({
        position: 'topleft',
        drawCircle: false,
        dragMode: false,
        drawPolygon: false,
        drawMarker: false,
        drawPolyline: false,
        drawRectangle: false,
        drawCircleMarker: false,
        cutPolygon: false,
        editMode: true

    });

    if (e.shape === "Marker") {
        console.log("creating marker");
        //console.log(e.layer._latlng)
        map.pm.disableDraw("Marker");
    } else if (e.shape === "Line") {
        console.log("creating line");
        //console.log("first point: " + e.layer._latlngs[0])
        //console.log("lenght of points array: " + e.layer._latlngs.length)
        //console.log("second point: " + e.layer._latlngs[1])
        //console.log("distance: " + (e.layer._latlngs[0]).distanceTo(e.layer._latlngs[1]))



        //console.log(e.layer._latlngs[0].lat + " och " + e.layer._latlngs[0].lng)
        // for (let ii = 0; ii < e.layer._latlngs.length - 1; ii++) {

        //    totaldistance += (e.layer._latlngs[ii]).distanceTo(e.layer._latlngs[ii + 1])

        //}
        console.log("total distance: " + totaldistance)
        updateDistance(calculateDistance(e.layer));
        savePositions(e.layer);




    }
    else {
        console.log("Error, no marker or line")
    }


    e.layer.on('pm:edit', e => {
        //console.log("new position(s): ")
        //console.log(e.target._latlngs)
        if (!e.target._latlngs) {
            console.log("Editing marker")
            console.log(e.target._latlng)
        } else {
            console.log("Editing line")
            //totaldistance = 0;

            //console.log("distance between first two points: " + e.target._latlngs[0].distanceTo(e.taget._latlngs[1]))

            //for (let ii = 0; ii < e.target._latlngs.length - 1; ii++) {
            //    totaldistance += (e.target._latlngs[ii]).distanceTo(e.target._latlngs[ii + 1])
            //}
            updateDistance(calculateDistance(e.target));
            savePositions(e.target);

        }

    })

});

//Modal (Select type of route)
function setTypeForRouteCreation(typeOfRoute) {
    console.log(typeOfRoute);

    if (typeOfRoute === "hiking" || typeOfRoute === "skiing") {

        console.log("vi är på rad 13")
        map.pm.addControls({
            position: 'topleft',
            drawCircle: false,
            dragMode: false,
            drawPolygon: false,
            drawMarker: false,
            drawPolyline: true,
            drawRectangle: false,
            drawCircleMarker: false,
            cutPolygon: false,
            editMode: false

        });
    } else {

        console.log("vi är i else")
        map.pm.addControls({
            position: 'topleft',
            drawCircle: false,
            dragMode: false,
            drawPolygon: false,
            drawMarker: true,
            drawPolyline: false,
            drawRectangle: false,
            drawCircleMarker: false,
            cutPolygon: false,
            editMode: false

        });
    }

    document.getElementById("typeOfRoute").value = typeOfRoute;
}

function calculateDistance(routeObject) {
    let returnDistance = 0;
    for (let ii = 0; ii < routeObject._latlngs.length - 1; ii++) {
        returnDistance += (routeObject._latlngs[ii]).distanceTo(routeObject._latlngs[ii + 1])
    }
    return returnDistance;

}

function updateDistance(totalDistance) {

    document.getElementById("lengthToShow").innerText = "Längd på markerad tur: " + (totalDistance / 1000).toFixed(1) + " km"
    document.getElementById("length").value = Math.round(totalDistance) //distance will be saved in meters
    document.getElementById("height").value = 0; //todo: replace with actual height
}


function savePositions(routeObject) {
    //erase any old positions
    latitudes = "";
    longitudes = "";

    for (let ii = 0; ii < routeObject._latlngs.length - 1; ii++) {
        latitudes += routeObject._latlngs[ii].lat + ", ";
        longitudes += routeObject._latlngs[ii].lng + ", ";
    }
    latitudes += routeObject._latlngs[routeObject._latlngs.length - 1].lat + ", ";
    longitudes += routeObject._latlngs[routeObject._latlngs.length - 1].lng + ", ";


}

function saveRouteToDatabase() {

    document.getElementById("latitudes").value = latitudes;
    document.getElementById("longitudes").value = longitudes;
}




