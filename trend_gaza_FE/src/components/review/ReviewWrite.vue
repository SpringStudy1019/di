<script setup>
import ReviewFormItem from './item/ReviewFormItem.vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const contentId = route.params.contentId;
</script>

<template>
    <div>
        <h2>
            <mark>리뷰 쓰기</mark>
        </h2>
        <div>
            <ReviewFormItem type='regist' :contentId="contentId"/>
        </div>
    </div>
</template>

<style scoped>

</style>