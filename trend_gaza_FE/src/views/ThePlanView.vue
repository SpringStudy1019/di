<script setup>
import PlanMap from '@/components/plan/PlanMap.vue';
import PlanSearch from '@/components/plan/PlanSearch.vue'
</script>

<template>
    <h1>여행 계획을 세워볼까요?</h1>
    <router-view></router-view>
    <PlanSearch />
    <PlanMap/>
</template>

<style scoped>
</style>