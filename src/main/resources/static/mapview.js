//JS-script to show route objects on map

//Defining "global?" variables
let showHiking = true;
let showSkiing = true;
let showMountainTop = true;
let showPoi = true;
let dbRoutes;
let layerGroup = L.layerGroup([]);

//Initialization of map
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

const map = new L.Map("mapview", {
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

map.setView([67.893153, 18.75682], 7);
//END of initialization of map

//Code for custom icons
var topp = L.icon({
  iconUrl: "/images/mountaintop_pin.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 55], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -55], // point from which the popup should open relative to the iconAnchor
  className: "typeIcon"
});

var vandringstur = L.icon({
  iconUrl: "/images/hiking.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 25], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -25] // point from which the popup should open relative to the iconAnchor
});

var skidtur = L.icon({
  iconUrl: "/images/skiing.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 25], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -25] // point from which the popup should open relative to the iconAnchor
});

var plats = L.icon({
  iconUrl: "/images/poi.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 25], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -25] // point from which the popup should open relative to the iconAnchor
});

function getAllRoutesFromDatabase() {
  fetch("http://localhost:8080/getallfromdb")
    .then(test => test.json())
    .then(tempDbRoutes => {
      //dbRoutes is the JSON-object with all routes

      dbRoutes = tempDbRoutes;
      drawRoutesOnMap();
      //generateList();
    })
    //Catch to handle errors of the API-call. Not really used anywhere.
    .catch(err => {
      console.log("Fel vid hämtning från databasen");
      throw err;
    });
}

//Function to display routes on map
function drawRoutesOnMap() {
  layerGroup = L.layerGroup([]);

  for (let i = 0; i < dbRoutes.length; i++) {
    //Loop through the array with routes
    let routeType = dbRoutes[i].routeType; //Define what type of route

    let popup = L.popup();
    console.log(dbRoutes[i].routeId);

    if (routeType === "Vandringstur" || routeType === "Skidtur") {
    popup.setContent(
          "<span class='popup-heading'><a href='/tur/" +
            dbRoutes[i].routeId +
            "' class='popup-link'>" +
            dbRoutes[i].routeName +
            "</a></span><br><span class='small-desc'>Sträcka: "
            + (dbRoutes[i].length / 1000).toFixed(1) + " km</span>"
        );


      //A For-loop in order to create a nested JS-array with coordinates that Leaflet requires. The recieved JSON-object only contains a "normal(non-nested)" array
      let coords = [];
      for (let index = 0; index < dbRoutes[i].positions.length; index++) {
        let coord = [];

        //Add the latitude and longitude to one array, pushing it in the main positions array "coords"
        coord.push(dbRoutes[i].positions[index].latitude);
        coord.push(dbRoutes[i].positions[index].longitude);
        coords.push(coord);
      }

      //Switch-case for choosing the right icon for the route
      switch (routeType) {
        case "Vandringstur":
          //IF-statement for filtering
          if (showHiking) {
            polyline = L.polyline(coords, {
              className: "polyline-hike"
            });

            //Draw the line on the map
            polyline.bindPopup(popup);
            layerGroup.addLayer(polyline);
            polyline.addTo(map);

            //Binds a popup to the line, showing information
            marker = L.marker(polyline.getCenter(), {
              //The getCenter-method returns the center point of the route, i.e. the place where the icon should be.
              icon: vandringstur
            });

            marker.bindPopup(popup);
            layerGroup.addLayer(marker);
          }

          break;
        case "Skidtur":
          if (showSkiing) {
            polyline = L.polyline(coords, {
              className: "polyline-ski"
            });

            polyline.bindPopup(popup);
            layerGroup.addLayer(polyline);
            polyline.addTo(map);

            marker = L.marker(polyline.getCenter(), {
              icon: skidtur
            });
            marker.bindPopup(popup);

            layerGroup.addLayer(marker);
          }

          break;

        default:
          console.log(
            "No routeType in Switch-case found for " + dbRoutes[i].routeId
          );

          break;
      }

      //Finally adds the marker to the route.

      //Code to run if route is only a point, similar as above.
    } else if (routeType === "Topp" || routeType === "Plats") {
         popup.setContent(
                  "<p><a href='/tur/" +
                    dbRoutes[i].routeId +
                    "' class='popup-link'>" +
                    dbRoutes[i].routeName +
                    "</a></p>"
                );

      let coord = [];
      coord.push(dbRoutes[i].positions[0].latitude);
      coord.push(dbRoutes[i].positions[0].longitude);

      switch (routeType) {
        case "Topp":
          if (showMountainTop) {
            marker = L.marker(coord, {
              icon: topp
            });
            marker.bindPopup(popup);

            layerGroup.addLayer(marker);
          }

          break;
        case "Plats":
          if (showPoi) {
            marker = L.marker(coord, {
              icon: plats
            });
            marker.bindPopup(popup);

            layerGroup.addLayer(marker);
          }

          break;

        default:
          console.log(
            "No routeType in Switch-case found for " + dbRoutes[i].routeId
          );
          break;
      }
    } else {
      console.log("ERROR: Ingen passande routeType hittades");
      console.log(dbRoutes[i].routeId);
    }
  }

  layerGroup.addTo(map);
}

//Code for listing route objects
function generateList() {
  let listOfNames = [];
  let listOfRouteId = [];

  for (let index = 0; index < dbRoutes.length; index++) {
    //Loop through the array with routes
    let routeName = dbRoutes[index].routeName;
    listOfNames.push(routeName);
    listOfRouteId.push(dbRoutes[index].routeId);
  }

  // Create an unordered list
  let listOfRouteNames = document.createElement("ul");

  for (let index = 0; index < listOfNames.length; index++) {
    let htmlString =
      "<a href=/tur/" +
      listOfRouteId[index] +
      ">" +
      listOfNames[index] +
      "</a>";

    let li = document.createElement("li");
    li.innerHTML = htmlString;
    listOfRouteNames.appendChild(li);
  }

  var app = document.querySelector("#routeobjects");
  app.appendChild(listOfRouteNames);
}

function toggleShowHiking() {
  console.log("Toggle Hiking");
  showHiking = !showHiking;
  console.log(showHiking);
}

function toggleShowSkiing() {
  console.log("Toggle Skiing");
  showSkiing = !showSkiing;
  console.log(showSkiing);
}

function toggleShowMountainTop() {
  console.log("Toggle Mountaintop");
  showMountainTop = !showMountainTop;
  console.log(showMountainTop);
}

function toggleShowPoi() {
  console.log("Toggle POI");
  showPoi = !showPoi;
  console.log(showPoi);
}

function testCallTodbRoutes() {
  console.log(dbRoutes);
}

function clearMap() {
  console.log("clear");

  map.removeLayer(layerGroup);
  drawRoutesOnMap();
}

map.on("dragend", function onDragEnd() {
  var north = map.getBounds().getNorth();
  var east = map.getBounds().getEast();
  var west = map.getBounds().getWest();
  var south = map.getBounds().getSouth();
  console.log("North: " + north);
  console.log("South: " + south);
  console.log("East: " + east);
  console.log("West: " + west);
});
