<script setup>
import { ref, watch, onMounted } from "vue";


var map;
//const positions = ref(position);
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

  loadMarkers();
};

const loadMarkers = () => {
    markers.value = [];
    const marker = new kakao.maps.Marker({
        map: map,       // 마커를 표시할 지도
        position: new kakao.maps.LatLng(position.latitude, position.longitude),
        title: "마커",
        clickable: true
    });

    markers.value.push(marker);

    kakao.maps.event.addListener(marker, "click", function () {
        var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">' +
            '            카카오 스페이스닷원' +
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>' +
            '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' +
            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';
    });

    var overlay = new kakao.maps.CustomOverlay({
        content: "내용",
        map: map,
        position: marker.getPosition()       
    });
}

</script>

<template>
    <div id="map"></div>
</template>

<style scoped>
    #map {
        width: 40%;
        height: 300px;
    }
</style>