//import L from "leaflet";
//import { CRS } from "proj4leaflet";
//
//import '@geoman-io/leaflet-geoman-free';
//import '@geoman-io/leaflet-geoman-free/dist/leaflet-geoman.css';

console.log("mapview.js loaded");

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

//Code for custom icons
var mountainTop = L.icon({
  iconUrl: "/images/peak.png",
  shadowUrl: "",

  iconSize: [50, 50], // size of the icon
  shadowSize: [0, 0], // size of the shadow
  iconAnchor: [25, 55], // point of the icon which will correspond to marker's location
  shadowAnchor: [0, 0], // the same for the shadow
  popupAnchor: [0, -55], // point from which the popup should open relative to the iconAnchor
  className: "typeIcon"
});

var hiking = L.icon({
  iconUrl: "/images/walking.png",
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

//hard-coded top to start out with
let marker = L.marker([67.904363, 18.527085], { icon: mountainTop }).addTo(map);

marker.bindPopup("<b>Hello marker!</b><br>I am a popup.");
//marker.bindTooltip("my tooltip text").openTooltip();

//hard-coded routes to start with
let route = [
  [67.918486, 18.601353],
  [67.903964, 18.621239],
  [67.893922, 18.646379],
  [67.870871, 18.648868],
  [67.868984, 18.617324]
];

let polyline = L.polyline(route, { className: "polyline" }).addTo(map);

let polylineMarker = L.marker([67.893922, 18.646379], { icon: hiking }).addTo(
  map
);

polylineMarker.bindPopup("<b>Hello marker!</b><br>I am a popup.");

polyline.bindPopup("<b>Hello line!</b><br>I am a popup.");

// skapar en pop-up med koordinater, long/lat
let popUp = L.popup();

function onMapClick(e) {
  popUp
    .setLatLng(e.latlng)
    .setContent("Koordinater: " + e.latlng.toString())
    .openOn(map);
}

//map.on("click", onMapClick);

//Test of API-call to back end
function testGetRoute() {
  console.log("Inne i metoden testGetRoute");

  fetch("http://localhost:8080/testRoute")
    .then(test => test.json())
    .then(out => {
      console.log("Checkout this JSON! ", out);

      let routeType = out.type;
      let newMarker;

      switch (routeType) {
        case "hiking":
          newMarker = L.marker([out.positions[0], out.positions[1]], {
            icon: hiking
          }).addTo(map);
          break;

        case "skiing":
          newMarker = L.marker([out.positions[0], out.positions[1]], {
            icon: skiing
          }).addTo(map);
          break;

        case "mountainTop":
          newMarker = L.marker([out.positions[0], out.positions[1]], {
            icon: mountainTop
          }).addTo(map);

          break;

        default:
          break;
      }
      newMarker.bindPopup(out.name);
    })
    .catch(err => {
      throw err;
    });
}

// adding leaflet-geoman controls/toolbar with some options to the map
/*
map.pm.addControls({
  position: 'topleft',
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
*/

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

map.on("pm:create", e => {
  console.log("draw end");
  map.pm.addControls({
    position: "topleft",
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
    console.log(e.layer._latlng);
    map.pm.disableDraw("Marker");
  } else if (e.shape === "Line") {
    console.log("creating line");
    console.log(e.layer._latlngs[0].lat + " och " + e.layer._latlngs[0].lng);
    for (const position of e.layer._latlngs) {
      console.log(position);
    }
  } else {
    console.log("Error, no marker or line");
  }

  e.layer.on("pm:edit", e => {
    console.log("new position(s): ");
    console.log(e.target._latlngs);
    if (!e.target._latlngs) {
      console.log("editing marker");
      console.log(e.target._latlng);
    } else {
      console.log("editing line");
      for (const position of e.target._latlngs) {
        console.log(position);
      }
    }
  });
});
