<script setup>
import ReviewFormItem from '@/components/review/item/ReviewFormItem.vue';
import {useRoute} from "vue-router";

const reviewIdx = useRoute().params.reviewIdx;
</script>

<template>
    <div>
        <h2>
            <mark>리뷰 수정</mark>
        </h2>
        <div>
            <ReviewFormItem type='modify' :reviewIdx="reviewIdx"/>
        </div>
    </div>
</template>

<style scoped>

</style>