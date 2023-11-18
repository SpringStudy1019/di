<script setup>
import { ref, onMounted, computed } from 'vue';
import {useRoute} from "vue-router";
// import AttractionMap from './item/AttractionMap.vue';
import {searchByCategory} from '@/api/attraction';

const route = useRoute();
const {contentTypeId} = route.params;
const sido = ref("");
const word = ref("");

const attractions = ref([]);

// 카테고리별 관광지 가져오기 
onMounted(() => {
    getAttraction();
});

const getAttraction = () => {
    searchByCategory(
        contentTypeId,
        sido.value,
        word.value,
    ({data}) => {
        attractions.value = data;
        console.log(data);
    },
    (error) => {
        console.log(error);
    }
    );
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
  
  <thead>
    <tr class="text-center">
        <th scope="col">여행지</th>
        <th scope="col">주소</th>
    </tr>
    </thead>
    <tbody>
  <tr class="text-center" v-for="attraction in attractions" :key="attraction.contentId">
    <td class="text-start" >
      <router-link
        :to="{ name: 'attraction-view', params: { attractionIdx: attraction.contentId } }"
        class="article-title link-dark"
      >
        {{ attraction.title }}
      </router-link>
    </td>
    <td>{{ attraction.addr1 }}</td>
  </tr>
</tbody>
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