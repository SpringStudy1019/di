<script setup>
import {ref, onMounted} from 'vue';
import {useRoute} from "vue-router";
import AttractionMap from './item/AttractionMap.vue';
import {getAttractionDetail} from '@/api/attraction';
import VYoutube from '@/components/detail/VYoutube.vue';

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
    <h1>{{ attraction.title }}</h1>
    <div>{{ attraction.tel }}</div>
    <img :src="attraction.defaultImg">
    <div v-if='attraction.latitude && attraction.longitude'>
        <AttractionMap :longitude="attraction.longitude" :latitude="attraction.latitude"/>
    </div>
    <VYoutube />

</template>

<style scoped>

</style>