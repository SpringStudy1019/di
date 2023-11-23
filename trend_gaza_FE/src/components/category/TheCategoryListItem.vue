<script setup>
import { ref, onMounted } from 'vue';

const props = defineProps({ attraction: Object });

</script>

<template>
   <div class="card mb-3">
        <div class="card-header text-center">
        <router-link 
        :to="{ name: 'attraction-view', params: { attractionIdx: hotplace.contentId } }" 
        class="btn btn-primary me-2">
            {{ attraction.title }}
        </router-link>
        </div>
        <div class="card-body">
        <h5 class="card-title">{{ attraction.addr1 }}</h5> 
        </div>
    </div>
</template>

<style scoped>

</style>