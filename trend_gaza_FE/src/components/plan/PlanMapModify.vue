<script setup>
import PlanMapFormItem from "./item/PlanMapFormItem.vue";
import { useRoute } from "vue-router";

const attractionPlanId = useRoute().params.attractionPlanId;
</script>

<template>
  <div>
    <div>
      <PlanMapFormItem type="modify" :attractionPlanId="attractionPlanId" />
    </div>
  </div>
</template>

<style scoped></style>
