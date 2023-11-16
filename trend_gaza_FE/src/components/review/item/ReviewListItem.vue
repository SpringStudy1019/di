<script setup>
import { computed } from 'vue';
defineProps({ review: Object });

// const computedStars = computed(() => {
//     const stars = [];
//     const score = review.score;

//   for (let i = 1; i <= 5; i++) {
//     stars.push(i <= score);
//   }

//   return stars;
// });
</script>

<template>
    <div class="card mb-3">
        <div class="card-header text-center">
        여행후기 #{{ review.reviewIdx }}
        </div>
        <div class="card-body">
        <h5 class="card-title">{{ review.title }}</h5>
        <p class="card-text">{{ review.content }}</p>
        <div class="stars">
            <span v-for="(star, index) in computedStars" :key="index" class="star" :class="{ filled: star }">★</span>
        </div>
        <p class="card-text"><small class="text-muted">Score: {{ review.score }}</small></p>
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
.stars {
  display: flex;
  justify-content: center;
}

.star {
  color: gold;
  font-size: 1.5em;
  margin-right: 3px;
}

.filled {
  color: gold;
}
</style>