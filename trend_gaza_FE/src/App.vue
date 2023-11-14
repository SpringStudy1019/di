<script setup>

import { RouterView } from "vue-router";
import TheHeadingNavbar from "@/components/layout/TheHeadingNavbar.vue";
import TheFooter from './components/layout/TheFooter.vue';

</script>

<template>
  <TheHeadingNavbar></TheHeadingNavbar>
  <section>
    <router-view></router-view>
  </section>
  <TheFooter />
</template>

<style scoped>
section {
  min-height: 100vh;
}
</style>
