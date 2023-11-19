<script setup>
import { ref, watch, onMounted } from "vue";
import PlanSearch from '@/components/plan/PlanSearch.vue'

var map;
const markers = ref([]);
//const props = defineProps({latitude: Number, longitude: Number});

const position = {
    latitude: 37.52251412000000000,
    longitude: 128.29191150000000000
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
    level: 5,
  };

  map = new kakao.maps.Map(container, options);

  // 지도 타입 변경 컨트롤을 생성한다
  var mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
  
  // 지도에 확대 축소 컨트롤을 생성한다
  var zoomControl = new kakao.maps.ZoomControl();
  
  // 지도의 우측에 확대 축소 컨트롤을 추가한다
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

//  loadMarkers();
};

const loadMarkers = (data) => {
  console.log("마커 찍기 함수 호출");
  console.log(data);
  markers.value = [];
  for(let i=0; i< data.length; i++) {
    const latitude = data[i].latitude; 
    const longitude = data[i].longitude;

    if (latitude !== null && longitude !== null) {
        const marker = new kakao.maps.Marker({
            map: map,       // 마커를 표시할 지도
            position: new kakao.maps.LatLng(latitude, longitude),
            title: "마커",
            clickable: true,
        });
        
        markers.value.push(marker);
    }
  }
};
</script>

<template>
    <PlanSearch @getAttractionData="loadMarkers" />
    <div id="map"></div>
</template>

<style scoped>
    #map {
        width: 100%;
        height: 800px;
    }
</style>