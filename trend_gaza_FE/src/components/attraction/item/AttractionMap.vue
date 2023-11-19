<script setup>
import { ref, watch, onMounted } from "vue";

var map;

const markers = ref([]);
const props = defineProps({latitude: Number, longitude: Number});

const position = {
    latitude: props.latitude,
    longitude: props.longitude
}

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(position.latitude, position.longitude),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
  map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);   
  
  loadMarkers();

};

const loadMarkers = () => {
  markers.value = [];
  const marker = new kakao.maps.Marker({
    map: map,       // 마커를 표시할 지도
    position: new kakao.maps.LatLng(position.latitude, position.longitude),
    title: "마커",
    clickable: true,
  });
  markers.value.push(marker);
};


</script>

<template>
    <div id="map"></div>
</template>

<style scoped>
    #map {
        width: 1000px;
        height: 500px;
        margin: auto;
    }

    #roadview {
  width: 30%;
  height: 30%;
}
</style>