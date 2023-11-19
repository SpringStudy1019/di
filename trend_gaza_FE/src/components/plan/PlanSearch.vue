<script setup>
import {ref, onMounted} from "vue";
import {searchAttractionsByCondition} from '@/api/attraction.js'

const sido = ref("0");
const contentTypeId = ref("0");
const keyword = ref("");
const markers = ref([]);

const emit = defineEmits(["getAttractionData"]);

const param = ref({
    sido: "",
    contentTypeId: "",
    keyword: ""
});

onMounted(() => {
    callAPI();
});

let areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey= 
${import.meta.env.VITE_OPEN_API_SERVICE_KEY}&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`;

function callAPI() {
    // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
    fetch(areaUrl, { method: "GET" })
      .then((response) => response.json())
      .then((data) => makeOption(data));
}

function makeOption(data) {
    let areas = data.response.body.items.item;
    let sel = document.getElementById("search-area");
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));
        sel.appendChild(opt);
    });
}

const searchAttractions = () => {
    param.value.sido = sido.value;
    param.value.contentTypeId = contentTypeId.value;
    param.value.keyword = keyword.value;
    
    searchAttractionsByCondition(
        param.value,
        ({data}) => {
            console.log(data);
            emit("getAttractionData", data);
            //loadMarkers(data);
        },
        (error) => {
            console.log(error);
        }
    )
}
</script>

<template>
    <div class="container">
        <form class="d-flex my-3" onsubmit="return false;" role="search">
            <select id="search-area" class="form-select me-2" v-model="sido">
                <option value="0" selected>검색 할 지역 선택</option>
            </select>
            <select id="search-content-id" class="form-select me-2" v-model="contentTypeId">
                <option value="0" selected>관광지 유형</option>
                <option value="12">관광지</option>
                <option value="14">문화시설</option>
                <option value="15">축제공연행사</option>
                <option value="25">여행코스</option>
                <option value="28">레포츠</option>
                <option value="32">숙박</option>
                <option value="38">쇼핑</option>
                <option value="39">음식점</option>
            </select>
            <input id="search-keyword" class="form-control me-2" v-model="keyword" type="search" placeholder="검색어" aria-label="검색어" />
            <button id="btn-search" class="btn btn-outline-success" @click="searchAttractions" type="button">검색</button>
    </form>

    </div>
</template>

<style scoped>
.container {
    border: 1px black;
}
</style>