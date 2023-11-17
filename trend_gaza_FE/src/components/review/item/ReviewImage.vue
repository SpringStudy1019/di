<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import {getReviewImage} from "@/api/review"

const reviewIdx = useRoute().params.reviewIdx;
const images = ref([]);

onMounted(() => {
    getReviewImage(reviewIdx,
        ({ data }) => {
            console.log(data);
            images.value = data;
        }, (error) => {
            console.log(error);
        });
});
</script>

<template>
    <div v-for='image in images' :key='image.saveFile'>
        <img class="img" :src='image.saveFile'/>
        <!-- <div class='card'>
            <img class="img" src="@/assets/pika.png" alt="Pikachu Image"/>
        </div> -->
    </div>
</template>

<style scoped>
.img {
    width: 100px;
}

.card {
    display: flex;
    width: 20%;
    justify-content: center;
    margin: 10px;
}


</style>