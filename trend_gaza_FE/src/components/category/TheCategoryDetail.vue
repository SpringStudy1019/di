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
    <ul>
      <li v-for="attraction in attractions" :key="attraction.contentId">
        <h3>{{ attraction.title }}</h3>
        <!-- <p>{{ attraction.description }}</p> -->
        <!-- <img :src="attraction.imageUrl" alt="Attraction Image" /> -->
      </li>
    </ul>
  </div>
  <tr class="text-center">
    <th scope="row">{{ article.articleNo }}</th>
    <td class="text-start">
      <router-link
        :to="{ name: 'attraction-view', params: { articleno: article.articleNo } }"
        class="article-title link-dark"
      >
        {{ attraction.title }}
      </router-link>
    </td>
    <td>{{ attraction.addr1 }}</td>
    <td>{{ article.hit }}</td>
    <td>{{ article.registerTime }}</td>
  </tr>
</template>

<style scoped>

</style>