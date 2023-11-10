<script setup>
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
import TheFooter from './components/layout/TheFooter.vue';
// import { RouterView } from "vue-router";
</script>

<template>
  <TheHeadingNavbar></TheHeadingNavbar>
  <div>
    <router-view></router-view>
  </div>
  <TheFooter />
</template>

<style scoped>

</style>
