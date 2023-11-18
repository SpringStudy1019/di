<script setup>
import {ref, onMounted} from 'vue';
import {useRoute} from "vue-router";
// import AttractionMap from './item/AttractionMap.vue';
import {searchByCategory} from '@/api/attraction';

const route = useRoute();
const {contentTypeId} = route.params;
const sido = ref("");
const word = ref("");

const attractions = ref([]);


onMounted(() => {
    getAttraction();
});

const getAttraction = () => {
    searchByCategory(
        contentTypeId,
        sido.value,
        word.value,
    ({data}) => {
        attractions.value = data;
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
    <h2>List of Attractions</h2>
    <ul>
      <li v-for="attraction in attractions" :key="attraction.contentId">
        <h3>{{ attraction.title }}</h3>
        <!-- <p>{{ attraction.description }}</p> -->
        <!-- <img :src="attraction.imageUrl" alt="Attraction Image" /> -->
      </li>
    </ul>
  </div>
</template>

<style scoped>

</style>