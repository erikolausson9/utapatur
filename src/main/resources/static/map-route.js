//import L from "leaflet";
//import { CRS } from "proj4leaflet";
//
//import '@geoman-io/leaflet-geoman-free';
//import '@geoman-io/leaflet-geoman-free/dist/leaflet-geoman.css';

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

const map = new L.Map("map-route", {
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



//map.pm.setPathOptions({
//  color: 'orange',
//  fillColor: 'green',
//});

//hard-coded routes to start with



/*
function onMapClick(e) {
  popUp
    .setLatLng(e.latlng)
    .setContent("Koordinater: " + e.latlng.toString())
    .openOn(map);
}
*/

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




function getRouteFromDatabase() {
  console.log("in getRouteFromDatabase")


  fetch("http://localhost:8080/show-specific-route")
    .then(test => test.json())
    .then(dbRoute => {
      //dbRoute is the JSON-object with the chosen route



      let routeType = dbRoute.routeType;


      //change content of page to show route info of requested route
      document.getElementById("nameOfRoute").innerText = dbRoute.routeName;
      document.getElementById("createdBy").innerText = dbRoute.memberId;
      document.getElementById("routeCreated").innerText = dbRoute.routeCreated;
      document.getElementById("dateOfCompletion").innerText = dbRoute.dateOfCompletion;
      document.getElementById("routeType").innerText = dbRoute.routeType;
      document.getElementById("length").innerText = (dbRoute.length / 1000).toFixed(1) + " km";
      document.getElementById("height").innerText = dbRoute.height;
      document.getElementById("difficulty").innerText = dbRoute.difficulty;
      document.getElementById("duration").innerText = dbRoute.days + " dagar och " + dbRoute.hours + " timmar";
      document.getElementById("description").innerText = dbRoute.description;


      if (routeType === "Vandringstur" || routeType === "Skidtur") {
        //A For-loop in order to create a nested JS-array with coordinates that Leaflet requires. The recieved JSON-object only contains a "normal(non-nested)" array
        let coords = [];
        for (let index = 0; index < dbRoute.positions.length; index++) {
          let coord = [];

          //Add the latitude and longitude to one array, pushing it in the main positions array "coords"
          coord.push(dbRoute.positions[index].latitude);
          coord.push(dbRoute.positions[index].longitude);
          coords.push(coord);
        }

        //Draw the line on the map
        polyline = L.polyline(coords, {
          className: "polyline"
        }).addTo(map);

        map.setView(coords[0], 4); //TODO: this should ideally be set to the center of the line, not the first point

        //Switch-case for choosing the right icon for the route
        switch (routeType) {
          case "Vandringstur":
            marker = L.marker(polyline.getCenter(), {
              //The getCenter-method returns the center point of the route, i.e. the place where the icon should be.
              icon: vandringstur
            }).addTo(map);

            break;
          case "Skidtur":
            marker = L.marker(polyline.getCenter(), {
              icon: skidtur
            }).addTo(map);
            break;

          default:
            break;
        }

      } else if (routeType === "Topp" || routeType === "Plats") {
        //do the same for points
        let coord = [];
        coord.push(dbRoute.positions[0].latitude);
        coord.push(dbRoute.positions[0].longitude);

        map.setView(coord, 4);

        switch (routeType) {
          case "Topp":
            marker = L.marker(coord, {
              icon: topp
            }).addTo(map);

            break;
          case "Plats":
            marker = L.marker(coord, {
              icon: plats
            }).addTo(map);
            break;

          default:
            break;
        }
        //marker.bindPopup(dbRoute.routeName);
      } else {
        console.log("ERROR: Ingen passande routeType hittades");
      }

    })
}












