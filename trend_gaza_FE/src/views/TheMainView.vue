<script setup>
import { useRouter } from 'vue-router';
import TheCategory from '../components/category/TheCategory.vue';
import Search from '@/components/search/Search.vue';

const router = useRouter();

</script>

<template>

        <!-- 검색바 -->
        <Search />

        <!-- 카테고리 -->
        <TheCategory />
        
        <!-- 추천 여행지 -->
        <h2>계절별 여행지</h2>


</template>

<style scoped>
.search {
  margin-top: 20px;
  margin-bottom: 20px;
}

</style>