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

      // Use a function to create a closure for each iteration
      const createClickHandler = (marker, title) => {
        return function () {
          // 커스텀 오버레이에 표시할 컨텐츠 입니다
          // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
          // 별도의 이벤트 메소드를 제공하지 않습니다 
          const content = `
          <style>
            .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
            .wrap * {padding: 0;margin: 0;}
            .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
            .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
            .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
            .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
            .info .close:hover {cursor: pointer;}
            .info .body {position: relative;overflow: hidden;}
            .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
            .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
            .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
            .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
            .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
            .info .link {color: #5085BB;}
          </style>
          
          <div class="wrap">
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

          // 마커 위에 커스텀오버레이를 표시합니다
          // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
          var overlay = new kakao.maps.CustomOverlay({
              content: content,
              map: map,
              position: marker.getPosition()       
          });

          // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
          kakao.maps.event.addListener(marker, 'click', function() {
            overlay.setMap(map);
          });
        };
      };

      // Attach click event to marker using the function
      kakao.maps.event.addListener(marker, "click", createClickHandler(marker, data[i].title));
    }

    // 지도 중심을 이동 시킵니다
    map.setCenter(new kakao.maps.LatLng(latitude, longitude));
  }
};

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
function closeOverlay() {
    overlay.setMap(null);     
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