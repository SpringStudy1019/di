<script setup>
import { useRouter } from 'vue-router';
import TheCategory from '../components/category/TheCategory.vue';
import Search from '@/components/search/Search.vue';
import AttractionRecommend from '../components/attraction/AttractionRecommend.vue';
import { ref } from 'vue'
import { useUserStore } from '@/stores/user';

const router = useRouter();

</script>

<template>

        <!-- 검색바 -->
        <Search />

        <!-- 카테고리 -->
        <TheCategory />
        
        <!-- 추천 여행지 -->
        <AttractionRecommend />
        


</template>

<style scoped>
.search {
  margin-top: 20px;
  margin-bottom: 20px;
}

</style>