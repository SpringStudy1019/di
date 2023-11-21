<script setup>
import { ref, onMounted } from 'vue';
import {getReviewImage} from "@/api/review"

const props = defineProps({ review: Object });

const images = ref([]);

onMounted(() => {
    getReviewImage(props.review.reviewIdx,
        ({ data }) => {
            console.log("이미지 불러와지나요?!!!! ")
            images.value = data;
            console.log(images.value)
        }, (error) => {
            console.log(error);
        });
});

// console.log(images.value)
</script>

<template>
    <div class="card mb-3">
        <div class="image">
            <div v-if="images.length === 0">
                <img class="card-img-top" 
                    src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/no_image.jpg" />
                </div>
                <div v-else>
                    <img class="card-img-top" :src="images[0].saveFile" />
                </div>
                <!-- <div v-else>
                    <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div v-for="(image, index) in images" :key="index" :class="{ 'carousel-item': true, active: index === 0 }">
                                <img class="d-block w-100" :src="image.saveFile" :alt="'Slide ' + (index + 1)">
                            </div>
                        </div>
                    </div>
                </div> -->
                <!-- <div v-for="image in images" :key="image.saveFile">
                    <img class="card-img-top" :src="image.saveFile" />
                </div> -->
        </div>
        <!-- <img class="card-img-top" :src="images.saveFile" alt="Card image cap"> -->
        <div class="card-header text-center">
        여행후기 #{{ review.reviewIdx }}
        </div>

        <div class="card-body">
            <h5 class="card-title">{{ review.title }}</h5>
            <p class="card-text">{{ review.content }}</p>
            <p class="card-text"><small class="text-muted">평점: {{ review.score }}</small></p>
            <p>좋아요</p>
        </div>

        <div class="card-footer text-muted text-center">
        <router-link
            :to="{ name: 'review-view', params: { reviewIdx: review.reviewIdx } }"
            class="btn btn-primary"
        >
            View Details
        </router-link>
        </div>
    </div>
</template>

<style scoped>
</style>