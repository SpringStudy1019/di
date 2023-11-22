<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import PlanSearch from '@/components/plan/PlanSearch.vue'
import PageNavigation from '@/components/common/PageNavigation.vue'
import { registerAttractionPlan, getAttractionPlan } from "@/api/plan"

const route = useRoute();
const { planIdx } = route.params;

var map;
const markers = ref([]);
const attractionList = ref([]);
const selectList = ref([]);
const allSelect = ref([]);
const curDay = ref(0);
const today = ref(formattingDate(new Date()));
const totalPages = ref(1);
const startDate = ref("");
const endDate = ref("");
//const props = defineProps({latitude: Number, longitude: Number});

const position = {
    latitude: 37.57889445000000000,
    longitude: 126.97703190000000000
}

// const planRequest = {
//   attractionId: 0,
//   order: 1,
//   orderDate: 1
// }

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

  initArray();
  getSelectedPlans();
  //getAttractionPlan();
});

// 2차원 배열 초기화
const initArray = () => {
  allSelect.value = new Array(3);
  for(let i=0; i<3; i++) {
    allSelect[i] = new Array();
  }
}

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

const loadAttractionList = (data) => {
  attractionList.value = data;
  loadMarkers(data);
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

// 선택한 관광지를 해당 N일차에 추가하기
const selectFunc = (data) => {
  if(allSelect.value[curDay.value] == null) {
    allSelect.value[curDay.value] = [];
  } else if (allSelect.value[curDay.value].length >= 10) {    // 각 N일차는 최대 10개만 등록 가능
    window.alert("하루에 최대 10개만 등록 가능합니다.");
    return;
  }
  allSelect.value[curDay.value].push(data);
}

const deleteItem = (data) => {
  let idx = allSelect.value[curDay.value].findIndex(item => item.contentId == data.contentId);
  allSelect.value[curDay.value].splice(idx, 1);
}

const onSelectListUpdate = (updatedList) => {
  // Handle the updated selectList in the parent component
  selectList.value = updatedList;
};

const startDrag = (event, item) => {
    event.dataTransfer.dropEffect = "move"
    event.dataTransfer.effectAllowed = "move"
    event.dataTransfer.setData("selectedItem", item.contentId)
};

const onDrop = (event, colNum) => {
  const selectedItem = Number(event.dataTransfer.getData("selectedItem"));
  // 리스트에서 선택된 아이템과 같은 content 값을 가진 요소를 찾아 index를 반환한다.
  let targetIdx;
  let targetItem;
  allSelect.value[curDay.value].forEach((obj, index) => {
    if (obj.contentId === selectedItem) {
        targetIdx = index;
        targetItem = obj;
      }
    })

  // 스위치 연산
  const temp = allSelect.value[curDay.value][colNum];
  allSelect.value[curDay.value][colNum] = allSelect.value[curDay.value][targetIdx];
  allSelect.value[curDay.value][targetIdx] = temp;
};

const trasformRequestDTO = () => {
  const requestList = [];
  for (let i = 0; i < allSelect.value.length; i++) {
    for (let j=0; j < allSelect.value[i].length; j++) {
      let planRequest = {
        "attractionId": allSelect.value[i][j].contentId,
        "order": j+1,
        "orderDate": i+1
      }
      requestList.push(planRequest);
    }
  }
  return requestList;
}

const savePlans = () => {
  registerAttractionPlan(1, trasformRequestDTO(),
    ({ data }) => {
      console.log(data);
    }, (error) => {
      console.log(error);
    })
};

// 기존에 저장했던 여행 계획을 조회
const getSelectedPlans = () => {
  getAttractionPlan(planIdx,
    ({ data }) => {
      for (let i = 0; i < data.length; i++) {
        if(allSelect.value[data[i].orderDate - 1] == null) {
          allSelect.value[data[i].orderDate - 1] = [];
        }
        let attraction = {
          title: data[i].title,
          firstImage: data[i].imgUrl,
          addr1: data[i].address,
          contentId: data[i].contentId, 
        }
        allSelect.value[data[i].orderDate - 1].push(attraction);
      }
    }, (error) => {
      console.log(error);
  })
}

/* 버튼을 클릭하면 해당 일자에 작성한 계획으로 전환됨 */
const moveNDay = (value) => {
  curDay.value = value;
}

function formattingDate(date) {
    const dateObject = new Date(date);
    dateObject.setDate(dateObject.getDate() + 1);
    return dateObject.toISOString().split('T')[0];
}

const updateButtonCount = () => {
  const start = new Date(startDate.value);
  const end = new Date(endDate.value);
  const diffInDays = Math.floor((end - start) / (1000 * 60 * 60 * 24)) + 1;

  console.log("날짜의 차:" + diffInDays);
  totalPages.value = diffInDays;
}
</script>

<template>
  
  <div class="wrapper">
    <button class="btn" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">🔍</button>
    <button class="btn float-end" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
      <img src="@/assets/hamburgerBtn.svg"/>
    </button>

<div class="offcanvas offcanvas-start" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasScrollingLabel">🧳 관광지 검색</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <PlanSearch @getAttractionData="loadAttractionList" />

    <!-- planSearchList -->
    <div class="container" v-for="attraction in attractionList" :key="attraction.contentId">  
        <p class="title">{{attraction.title}}</p>
          <div class="img-content">
              <img class="img" :src="attraction.firstImage"/>
          </div>
          <span>{{ attraction.addr1 }} </span>
          <div>
            <button class="add-btn" @click="selectFunc(attraction)">추가</button>
          </div> 
    </div>
  </div>  
</div>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasRightLabel">🎒현재 코스 리스트</h5>
      <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>

  <div class="offcanvas-body">
      <div class='date-group'>
        <div class='start-date'>
          <lable for='start-date'>출발일자 </lable>
          <input type='date' class="start-date-input" id="start-date" v-model="startDate" :min="today" @change='updateButtonCount'/>
        </div>
        <div class='end-date'>
          <lable for='end-date'>도착일자 </lable>
          <input type='date' id="end-date" class="end-date-input" v-model="endDate" :min="today" @change='updateButtonCount'/>
        </div>
      </div>
      <div class="page-nav">
        <nav  aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item" v-for='index in totalPages' :key='index'>
              <a class="page-link" @click='moveNDay(index - 1)'>{{ index }}</a>
            </li>
            <!-- <li class="page-item"><a class="page-link" @click='moveNDay(0)'>1</a></li>
            <li class="page-item"><a class="page-link" @click='moveNDay(1)'>2</a></li>
            <li class="page-item"><a class="page-link" @click='moveNDay(2)'>3</a></li> -->
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    <!-- PlanSelectList -->
    <!--<div class="container" v-for="(allSelectItem, idx) in allSelect" :key="idx">-->
      <div class='selected-group' v-for="(selectItem, idx) in allSelect[curDay]" :key="selectItem.contentId">
        <div class="container" @drop.prevent="onDrop($event, idx)" @dragover.prevent>
            <div @dragstart="startDrag($event, selectItem)" draggable="true">
                <p class="title">{{selectItem.title}}</p>
                <div class="img-content">
                    <img class="img" :src="selectItem.firstImage"/>
                </div>
                <span>{{ selectItem.addr1 }} </span>
                <div>
                    <button class="delete-btn" @click='deleteItem(selectItem)'>삭제</button>
                </div>
            </div>
      </div>
    <!--</div>-->
      </div>
  </div>

  <div class='offcanvas-footer'>
    <button class='save-btn' @click='savePlans'>저장</button>
  </div>
</div>

    <!-- <div calss="left-section">
      <PlanSearchList v-for="attraction in attractionList" :attraction="attraction" :key="attraction.contentId"/>
    </div> -->
    <div class="right-section">
      <div id="map"></div>
    </div>
  </div>
</template>

<style scoped>
    #map {
        width: 100%;
        height: 800px;
    }

    .left-section {
    flex: 1; /* Takes up all available space in the flex container */
    padding: 20px; /* Adjust padding as needed */
    width: 30%;
    background-color: aqua;
    display: flex;
    flex-direction: column;
}

.right-section {
  flex: 2; /* Takes up twice the space of the left section */
  padding: 20px; /* Adjust padding as needed */
}

/* PlanSearchList */
.container {
    border: 1px solid gainsboro;
    /*margin: 10 10px;*/
    margin-bottom: 25px;
    width: 300px;
    height: 350px;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.img {
    width: 250px;
    height: 200px;
    display: block;
    border-radius: 5%;
}

.img-content {
    margin: 5px auto;
    display: flex;
    align-items: center;
    justify-content: center;
}

.title {
  font-size: 20px;
  text-align: center;
  margin: 0;
}

.add-btn {
  text-align: right;
  display: inline;
  background-color: plum;
  color: white;
  border: white;
}

.page-nav {
  margin: 0 auto;
  justify-content: center;
  display: flex;
}

.selected-group {
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.date-group {
  margin-bottom: 20px; 
}

.delete-btn {
  background-color: plum;
  color: white;
  border: white;
}

.save-btn {
  background-color: plum;
  color: white;
  border: white;
}

.search-group {
  margin: 50px;
}

.start-date {
  margin-bottom: 10px;
}

.start-date-input {
  width: 80%;
}

.end-date-input {
  width: 80%;
}
</style>