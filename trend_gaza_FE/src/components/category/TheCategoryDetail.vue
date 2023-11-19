<script setup>
import { ref, onMounted, computed } from 'vue';
import {useRoute} from "vue-router";
// import AttractionMap from './item/AttractionMap.vue';
import {listByCategory} from '@/api/attraction';
import PageNavigation from "@/components/common/PageNavigation.vue";
import TheCategoryListItem from '@/components/category/TheCategoryListItem.vue';

const route = useRoute();
const {contentTypeId} = route.params;

const attractions = ref([]);

// 카테고리별 관광지 가져오기 
onMounted(() => {
    getAttraction();
});

const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ATTRACTION_LIST_SIZE } = import.meta.env;

const param = ref({
  contentTypeId: contentTypeId,
  pgno: currentPage.value,
  spp: VITE_ATTRACTION_LIST_SIZE,
  sido: "",
  word: "",
});

const getAttraction = () => {
  console.log("contenttypeid", contentTypeId)
  console.log("oh yeah!!")
  listByCategory(
    param.value,
    ({data}) => {
      console.log("oooooooh yeah!!!!!!")
        attractions.value = data
        currentPage.value = data.currentPage;
        totalPage.value = data.totalPageCount;
        console.log(data);
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
    switch (contentTypeId) {
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
        Plus, we'll provide road view. 
    -->
  </div>
  
  <TheCategoryListItem
    v-for="attraction in attractions"
    :key="attraction.contentId"
    :attraction="attraction"
  ></TheCategoryListItem>

  <PageNavigation
    :current-page="currentPage"
    :total-page="totalPage"
    @pageChange="onPageChange"
  ></PageNavigation>

  </template>

<style scoped>
 /* Container styling */
 div {
    margin: 20px auto; /* Center the container */
    max-width: 800px; /* Adjust the maximum width as needed */
  }

  /* Heading styling */
  h2 {
    font-family: 'Your-Preferred-Font', sans-serif;
    color: #333;
    font-size: 24px;
    margin-bottom: 20px;
    text-align: center; /* Center the heading */
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