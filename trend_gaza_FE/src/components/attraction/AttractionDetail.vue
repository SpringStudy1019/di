<script setup>
import {ref, onMounted} from 'vue';
import {useRoute} from "vue-router";
import AttractionMap from './item/AttractionMap.vue';
import {getAttractionDetail} from '@/api/attraction';

const route = useRoute();

const attraction = ref({});
const {attractionIdx} = route.params;

onMounted(() => {
    getAttraction();
});

const getAttraction = () => {
    getAttractionDetail(attractionIdx,
    ({data}) => {
        attraction.value = data;
        console.log(data);
    },
    (error) => {
        console.log(error);
    }
    );
};
</script>

<template>
    <div>
        <AttractionMap :longitude="attraction.longitude" :latitude="attraction.latitude"/>
    </div>
</template>

<style scoped>

</style>