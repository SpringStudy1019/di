<script setup>
import { ref, onMounted, computed } from 'vue';
import {useRoute} from "vue-router";
// import AttractionMap from './item/AttractionMap.vue';
import {listByCategory} from '@/api/attraction';
import PageNavigation from "@/components/common/PageNavigation.vue";
// import TheCategoryListItem from '@/components/category/TheCategoryListItem.vue';
const { VITE_ATTRACTION_LIST_SIZE } = import.meta.env;

const route = useRoute();
const contentTypeId = ref(route.params.contentTypeId);
// console.log("content type id:::", contentType.value);

const attractions = ref([]);

// 카테고리별 관광지 가져오기 
onMounted(() => {
    getAttraction();
});

const currentPage = ref(1);
const totalPage = ref(0);
const sido = ref("0");
const keyword = ref("");

const param = ref({
  contentTypeId: contentTypeId.value,
  sido: "",
  keyword: "",
  pgno: currentPage.value,
  spp: VITE_ATTRACTION_LIST_SIZE,
});

const getAttraction = () => {
  // console.log("sido value::",sido.value);
  if (sido.value !== '0') {
    param.value.sido = sido.value;
  } 
  console.log("keyword value::", keyword.value);
  // if (keyword.value !== '') {
  // }
  param.value.keyword = keyword.value;

  listByCategory(
    param.value,
    ({data}) => {
        attractions.value = data.attractions; // data가 아닌 data.attractions이다! (postman에서 데이터 확인)
        currentPage.value = data.currentPage;
        totalPage.value = data.totalPageCount;
        // console.log("current page:::", currentPage.value);
        // console.log("total page:::", totalPage.value);
    },
    (error) => {
        console.log(error);
    }
    );
};

const onPageChange = (val) => {
  // console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getAttraction();
};

// 관광지 이름 설정 (computed)
const categoryName = computed(() => {
    switch (contentTypeId.value) {
        case '14':
            return '문화시설';
        case '15':
            return '축제공연행사';
        case '28':
            return '레포츠';
        case '32':
            return '숙박';
        case '38':
            return '쇼핑';
        case '39':
            return '음식점';
        default:
            return '';
    }
});

</script>

<template>
    <div>
      <h2>{{ categoryName }} 목록</h2>
      <!-- Kakao Map -->
      <!-- When user clicked the title of attraction, 
          then it is marked in the map. 
          Plus, we'll provide road view. -->
    </div>

    <!-- 검색 추후에 컴포넌트로 빼기 -->
    <div class="search-container">
    <form class="d-flex my-3" onsubmit="return false;" role="search">
      <div class="form-group me-3 d-block">
        <select id="search-area" class="form-select me-3" v-model="sido">
          <option value="0" selected>검색 할 지역 선택</option>
          <option value="1">서울</option>
          <option value="2">인천</option>
          <option value="3">대전</option>
          <option value="4">대구</option>
          <option value="5">광주</option>
          <option value="6">부산</option>
          <option value="7">울산</option>
          <option value="8">세종특별자치시</option>
          <option value="31">경기도</option>
          <option value="32">강원특별자치도</option>
          <option value="33">충청북도</option>
          <option value="34">충청남도</option>
          <option value="35">경상북도</option>
          <option value="36">경상남도</option>
          <option value="37">전라북도</option>
          <option value="38">전라남도</option>
          <option value="39">제주도</option>
        </select>
      </div>

      <div class="form-group me-5">
        <input
          id="search-keyword"
          class="form-control me-2"
          v-model="keyword"
          type="search"
          placeholder="검색어"
          aria-label="검색어"
        />
      </div>
      <button
        id="btn-search"
        class="search-button"
        @click="getAttraction"
        type="button"
      >
        검색
      </button>
    </form>
  </div>
    <!-- 검색 추후에 컴포넌트로 빼기 -->

  <div class="card mb-3" v-for="attraction in attractions" :key="attraction.contentId">
        <div class="card-header text-center">
          <router-link 
          :to="{ name: 'attraction-view', params: { attractionIdx: attraction.contentId } }" 
          class='attraction-link'>
            {{ attraction.title }}
        </router-link>
        </div>
        <div class="card-body">
        <p>{{ attraction.addr1 }}</p> 
        </div>
    </div>
  
  <!-- <TheCategoryListItem
    v-for="attraction in attractions"
    :key="attraction.contentId"
    :attraction="attraction"
  ></TheCategoryListItem> -->

  <PageNavigation
    :current-page="currentPage"
    :total-page="totalPage"
    @pageChange="onPageChange"
  ></PageNavigation>

  </template>

<style scoped>
.search-button {
  padding: 6px; 
  margin-right: 50px; 
  margin-top: 8px;
  cursor: pointer;
  color: #8128d5; 
  height: 40px;
  border-radius: 8px;
  background-color: white;
  border: 1px solid #8128d5; /* Purple border */
}

.search-button:hover {
  background-color: #8128d5;
  color: white;
}
.attraction-link {
  text-decoration: none; 
  color: #333; 
  transition: color 0.3s ease; 
  font-weight: bold;
}
.attraction-link:hover {
  color: #8128d5; 
}
 /* Container styling */
 div {
    margin: 10px auto; /* Center the container */
    max-width: 800px; /* Adjust the maximum width as needed */
  }

  /* Heading styling */
  h2 {
    color: #333;
    font-size: 24px;
    margin-bottom: 20px;
    text-align: center; 
  }

  /* Table styling */
  table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
  }

  /* Table header styling */
  th {
    padding: 10px;
    background-color: #FF8080;
    color: #fff;
  }

  /* Table cell styling */
  td {
    padding: 10px;
    border: 1px solid #ddd;
  }

  /* Link styling */
  .article-title {
    text-decoration: none;
    color: #0066cc; /* Your preferred link color */
    font-weight: bold;
  }

  /* Link hover effect */
  .article-title:hover {
    text-decoration: underline;
  }
</style>