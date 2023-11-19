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
        title: data[i].title,
        clickable: true,
      });

      markers.value.push(marker);

      // Create content for the custom overlay
      const content = `<div class="wrap">
                        <div class="info">
                          <div class="title">
                            ${data[i].title}
                            <div class="close" onclick="closeOverlay()" title="닫기"></div>
                          </div>
                          <div class="body">
                            <div class="img">
                              <img src="${data[i].firstImage}" width="73" height="70">
                            </div>
                            <div class="desc">
                              <div class="ellipsis">${data[i].addr1}</div>
                              <div><a href="https://map.kakao.com/link/to/${data[i].title},${data[i].latitude},${data[i].longitude}" target="_blank" class="link">길찾기</a></div>
                            </div>
                          </div>
                        </div>
                      </div>`;

      // Create custom overlay
      const overlay = new kakao.maps.CustomOverlay({
        content: content,
        map: null, // Do not set the map initially
        position: marker.getPosition(),
      });

      // Attach click event to marker to toggle the custom overlay
      kakao.maps.event.addListener(marker, 'click', function () {
        if (overlay.getMap()) {
          overlay.setMap(null);
        } else {
          overlay.setMap(map);
        }
      });
    }

    // Set the map center to the last marker's position
    map.setCenter(new kakao.maps.LatLng(latitude, longitude));
  }
};

// Close the overlay when the close button is clicked
function closeOverlay() {
  if (overlay) {
    overlay.setMap(null);
  }
}




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