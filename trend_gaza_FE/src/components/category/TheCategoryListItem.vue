<script setup>
import { ref, onMounted } from 'vue';

const props = defineProps({ attraction: Object });

</script>

<template>
   <div class="card mb-3">
        <div class="card-header text-center">
        {{ attraction.title }}
        </div>
        <div class="card-body">
        <h5 class="card-title">{{ attraction.addr1 }}</h5> 
        </div>
    </div>
</template>

<style scoped>

</style>