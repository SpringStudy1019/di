<script setup>
import PlanFormItem from '@/components/plan/item/PlanFormItem.vue';
import {useRoute} from "vue-router";

const planIdx = useRoute().params.planIdx;
</script>

<template>
    <div>
        <div>
            <PlanFormItem type='modify' :planIdx="planIdx"/>
        </div>
    </div>
</template>

<style scoped>

</style>