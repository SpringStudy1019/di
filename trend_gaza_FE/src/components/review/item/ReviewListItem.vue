<script setup>
import { computed } from 'vue';
defineProps({ review: Object });

</script>

<template>
    <div class="card mb-3">
        <div class="card-header text-center">
        여행후기 #{{ review.reviewIdx }}
        </div>
        <div class="card-body">
        <h5 class="card-title">{{ review.title }}</h5>
        <p class="card-text">{{ review.content }}</p>
        
        <p class="card-text"><small class="text-muted">평점: {{ review.score }}</small></p>
        </div>
        <div class="card-footer text-muted text-center">
        <router-link
            :to="{ name: 'review-view', params: { reviewIdx: review.reviewIdx } }"
            class="btn btn-primary"
        >
            View Details
        </router-link>
        </div>
    </div>
</template>

<style scoped>
</style>