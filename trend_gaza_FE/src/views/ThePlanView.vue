<script setup>
import PlanMap from '@/components/plan/PlanMap.vue';
</script>

<template>
    <h1>여행 계획을 세워볼까요?</h1>
    <router-view></router-view>
    <PlanMap/>
</template>

<style scoped>
* {
    margin: 0;
    padding: 0;
}
</style>