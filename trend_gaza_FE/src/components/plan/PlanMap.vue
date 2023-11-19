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
  hideMarkers(); // Clear existing markers

  for (let i = 0; i < data.length; i++) {
    const latitude = data[i].latitude;
    const longitude = data[i].longitude;

    if (latitude !== null && longitude !== null) {
      const marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: new kakao.maps.LatLng(latitude, longitude),
        title: "마커",
        clickable: true,
      });

      markers.value.push(marker);

      var iwContent = `<div style="padding:5px;">${data[i].title} <br><a href="https://map.kakao.com/link/map/${data[i].title},${latitude},${longitude}" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/${data[i].title},${latitude},${longitude}" style="color:blue" target="_blank">길찾기</a></div>`;
      var iwPosition = new kakao.maps.LatLng(latitude, longitude);

      // Create an infowindow for each marker
      var infowindow = new kakao.maps.InfoWindow({
        position: iwPosition,
        content: iwContent,
      });

      // Attach click event to marker to open the infowindow
      kakao.maps.event.addListener(marker, "click", function () {
        infowindow.open(map, marker);
      });
    }

    // 지도 중심을 이동 시킵니다
    map.setCenter(new kakao.maps.LatLng(latitude, longitude));
  }
};

// 마커 삭제
function hideMarkers() {
  for(let i=0; i<markers.value.length; i++) {
    markers.value[i].setMap(null);
  }
  markers.value = [];
}


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