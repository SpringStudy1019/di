<script setup>
import PlanMap from '@/components/plan/PlanMap.vue';
import Drag from '../components/plan/Drag.vue';
</script>

<template>
    <h1>여행 계획을 세워볼까요?</h1>
    <router-view></router-view>
    <Drag />
    <PlanMap/>
</template>

<style scoped>
* {
    margin: 0;
    padding: 0;
}
</style>