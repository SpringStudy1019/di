<script setup>
import {ref, onMounted} from 'vue';
import {getHotplace} from '@/api/review';

const hotplaces = ref([])

onMounted(() => {
    recommend();
});

const recommend = () => {
    getHotplace(
    ({data}) => {
        hotplaces.value = data;
        console.log(data);
    },
    (error) => {
        console.log(error);
    }
    );
};

</script>

<template>
   <div class="container" id="top">
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
                <h2>사용자들이 가장 많은 리뷰를 남긴 여행지에요</h2>
                <!-- 여행지 TOP5-->
                <div class="card" style="width: 18rem;" v-for="hotplace in hotplaces" :key="hotplace.contentId">
                    <img class="card-img-top" :src="hotplace.firstImage" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">{{ hotplace.title }} </h5>
                        <!-- <button class="btn btn-primary me-2">자세히 보기</button> -->
                        <router-link 
                        :to="{ name: 'attraction-view', params: { attractionIdx: hotplace.contentId } }" 
                        class="btn btn-primary me-2">
                            자세히 보기
                        </router-link>
                        <button class="btn btn-primary">찜하기</button>
                    </div>
                    </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>

    <!-- test -->
   
</template>

<style scoped>
#top {
    margin-top: 50px;
}



</style>