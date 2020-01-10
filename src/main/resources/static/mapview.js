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

//map.pm.setPathOptions({
//  color: 'orange',
//  fillColor: 'green',
//});

//hard-coded routes to start with
let route = [
  [63.43336264092851, 13.100512530890732],
  [63.42612654639262, 13.162583161441411],
  [63.395953086104676, 13.231165979318754],
  [63.38021322970091, 13.168080742484434],
  [63.379097388031624, 13.170713930555567]
];

let route2 = [
  [67.4777659389, 19.88225463636],
  [67.4975352569, 19.7600868365],
  [67.4838912798, 19.59515819672],
  [67.4295552309, 19.72250342069]
];

let route3 = [
  [63.16248165247, 12.36796303705],
  [63.08423965644, 12.42831493833],
  [63.003404162007, 12.22797964426],
  [62.919686257953, 12.4225799762],
  [63.069424547189, 12.58503363623],
  [63.155588700623, 12.3870548077]
];

let polyline = L.polyline(route, { color: "blue" }).addTo(map);
let polyline2 = L.polyline(route2, { color: "blue" }).addTo(map);
let polyline3 = L.polyline(route3, { color: "blue" }).addTo(map);

// skapar en pop-up med koordinater, long/lat
let popUp = L.popup();

function onMapClick(e) {
  popUp
    .setLatLng(e.latlng)
    .setContent("Koordinater: " + e.latlng.toString())
    .openOn(map);
}

//map.on("click", onMapClick);

//hard-coded markers to start out with
let marker = L.marker([67.71837131142199, 17.794997304476783]).addTo(map);

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
