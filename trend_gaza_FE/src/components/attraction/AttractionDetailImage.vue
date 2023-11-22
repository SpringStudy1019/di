<script setup>
import {ref, onMounted, computed} from 'vue';
import { useRoute, useRouter } from "vue-router";
import { getUserImage } from '@/api/attraction';

const route = useRoute();
const router = useRouter();
const attractionIdx = ref(route.params.attractionIdx);
let title = '';

onMounted(() => {
    getImages();
    console.log("attractionIdx::", attractionIdx.value);
});

// 사용자가 업로드한 이미지 가져오기
const userImages = ref([])
const getImages = () => {
  getUserImage(
    attractionIdx.value,
    ({ data }) => {
        userImages.value = data;
        title = userImages.value[0].attraction_title;
        console.log("title::", title);
      // console.log(userImages.value)
    },
    (error) => {
      console.log(error);
    }
  )
};


</script>

<template>
    <div class="margin"></div>
    <router-link 
        :to="{ name: 'attraction-view', 
        params: { attractionIdx: attractionIdx } }" 
        class='link'>
        <h1 class="title">{{ title }}</h1>
    </router-link>
    <div class="margin"></div>
    <div class='frame'>
        <div class='row'>
            <div class="col-sm-6 col-md-4" v-for='userImage in userImages' :key='userImage.review_idx'>
            <div class="card border-white round">
                <router-link 
                :to="{ name: 'review-view', 
                params: { reviewIdx: userImage.review_idx } }" 
                >
                <img class="card-img-top" 
                :src="userImage.save_file" 
                alt="Card image cap" /> 
                </router-link>
            </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.link {
  color: black;
  text-decoration: none;
  transition: color 0.3s ease; /* Add a smooth transition effect on color change */
}

.link:hover {
  color: #8128d5;
}
.title {
  text-align: center;
  margin-top: 20px;
  font-weight: bold;
  font-size: 2.5em;
}
.frame {
    margin-left: 50px;
    margin-right: 50px;
}

.margin {
    margin-top: 50px;
}

</style>