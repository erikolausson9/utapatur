import L from "leaflet";
import { CRS } from "proj4leaflet";

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

const map = new L.Map("map", {
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

//ritar ut en rutt
let route = [
  [63.195372, 14.536352],
  [63.196272, 14.530312],
  [63.195268, 14.720513],
  [63.195372, 14.536352]
];

let polyline = L.polyline(route, { color: "purple" }).addTo(map);

// skapar en pop-up med koordinater, long/lat
let popUp = L.popup();

function onMapClick(e) {
  popUp
    .setLatLng(e.latlng)
    .setContent("koordinater: " + e.latlng.toString())
    .openOn(map);
}

map.on("click", onMapClick);

let marker = L.marker([63.215495, 14.732213]).addTo(map);
