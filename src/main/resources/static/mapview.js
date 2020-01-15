//JS-script to show route objects on map

//Defining "global?" variables

let showHiking = true;
let showSkiing = true;
let showMountainTop = true;
let showPoi = true;

let dbRoutes;

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
var mountainTop = L.icon({
  iconUrl: "/images/mountaintop_pin.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 55], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -55], // point from which the popup should open relative to the iconAnchor
  className: "typeIcon"
});

var hiking = L.icon({
  iconUrl: "/images/hiking.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 25], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -25] // point from which the popup should open relative to the iconAnchor
});

var skiing = L.icon({
  iconUrl: "/images/skiing.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 25], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -25] // point from which the popup should open relative to the iconAnchor
});

var poi = L.icon({
  iconUrl: "/images/poi.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 25], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -25] // point from which the popup should open relative to the iconAnchor
});

function testGetAllRoutesFromDatabase() {
  fetch("http://localhost:8080/getallfromdb")
    .then(test => test.json())
    .then(dbRoutes => {
      //dbRoutes is the JSON-object with all routes
    })
    //Catch to handle errors of the API-call. Not really used anywhere.
    .catch(err => {
      throw err;
    });
}

//Function to get all routes from backend database and display them on the map
function getAllRoutesFromDatabase() {
  //REST-API call to backend. Return JSON-object with all routes from database
  fetch("http://localhost:8080/getallfromdb")
    .then(test => test.json())
    .then(dbRoutes => {
      //dbRoutes is the JSON-object with all routes

      for (let i = 0; i < dbRoutes.length; i++) {
        //Loop through the array with routes
        let routeType = dbRoutes[i].routeType; //Define what type of route

        if (routeType === "hiking" || routeType === "skiing") {
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
            case "hiking":
              //IF-statement for filtering
              if (showHiking) {
                polyline = L.polyline(coords, {
                  className: "polyline"
                }).addTo(map);

                //Draw the line on the map
                polyline.bindPopup(dbRoutes[i].routeName);

                //Binds a popup to the line, showing information
                marker = L.marker(polyline.getCenter(), {
                  //The getCenter-method returns the center point of the route, i.e. the place where the icon should be.
                  icon: hiking
                }).addTo(map);

                marker.bindPopup(dbRoutes[i].routeName);
              }

              break;
            case "skiing":
              if (showSkiing) {
                polyline = L.polyline(coords, {
                  className: "polyline"
                }).addTo(map);

                polyline.bindPopup(dbRoutes[i].routeName);

                marker = L.marker(polyline.getCenter(), {
                  icon: skiing
                }).addTo(map);
                marker.bindPopup(dbRoutes[i].routeName);
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
        } else if (routeType === "mountainTop" || routeType === "poi") {
          let coord = [];
          coord.push(dbRoutes[i].positions[0].latitude);
          coord.push(dbRoutes[i].positions[0].longitude);

          switch (routeType) {
            case "mountainTop":
              if (showMountainTop) {
                marker = L.marker(coord, {
                  icon: mountainTop
                }).addTo(map);
                marker.bindPopup(dbRoutes[i].routeName);
              }

              break;
            case "poi":
              if (showPoi) {
                marker = L.marker(coord, {
                  icon: poi
                }).addTo(map);
                marker.bindPopup(dbRoutes[i].routeName);
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
    })
    //Catch to handle errors of the API-call. Not really used anywhere.
    .catch(err => {
      throw err;
    });
}

function generateList() {
  //Code for listing route objects
  fetch("http://localhost:8080/getallfromdb")
    .then(test => test.json())
    .then(dbRouteReturn => {
      let listOfNames = [];
      let listOfRouteId = [];

      for (let index = 0; index < dbRouteReturn.length; index++) {
        //Loop through the array with routes
        let routeName = dbRouteReturn[index].routeName;
        listOfNames.push(routeName);
        listOfRouteId.push(dbRouteReturn[index].routeId);
      }

      // Create an unordered list
      let listOfRouteNames = document.createElement("ul");

      for (let index = 0; index < dbRouteReturn.length; index++) {
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
    })
    //Catch to handle errors of the API-call. Not really used anywhere.
    .catch(error => {
      throw error;
    });
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
