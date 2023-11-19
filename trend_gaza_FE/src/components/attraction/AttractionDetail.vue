<script setup>
import {ref, onMounted} from 'vue';
import {useRoute, useRouter} from "vue-router";
import AttractionMap from './item/AttractionMap.vue';
import {getAttractionDetail} from '@/api/attraction';
import {getScores} from '@/api/review';
import VYoutube from '@/components/detail/VYoutube.vue';

const route = useRoute();
const router = useRouter();

const attraction = ref({});
const {attractionIdx} = route.params;

onMounted(() => {
    getAttraction();
    getScoresInfo();
});

const getAttraction = () => {
    getAttractionDetail(attractionIdx,
    ({data}) => {
        attraction.value = data;
        // console.log(data);
        window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    (error) => {
        console.log(error);
    }
    );
};

// 리뷰 쓰러 가기
const moveWrite = (contentId) => {
  router.push({ name: 'review-write', params: { contentId: contentId } });
};

// 리뷰 점수별로 카운트
const scoresInfo = ref({
  "one": "",
  "two": "",
  "three":"",
  "four":"",
  "five":""
})

const getScoresInfo = () => {
  // console.log(attractionIdx)
  getScores(
    attractionIdx,
    ({data}) => {
      // console.log(data)
      scoresInfo.value.one = data.scoreOneCount;
      scoresInfo.value.two = data.scoreTwoCount;
      scoresInfo.value.three = data.scoreThreeCount;
      scoresInfo.value.four = data.scoreFourCount;
      scoresInfo.value.five = data.scoreFiveCount;
      // console.log(scoresInfo.value)
    },
    (error) => console.log(error)
  )
}
</script>

<template>
    <h1>{{ attraction.title }}</h1>
    <div class="col-md-2 text-start">
        <button type="button" class="btn btn-outline-primary btn-sm" 
        @click="moveWrite(attraction.contentId)">
            리뷰 쓰기
        </button>
    </div>
    <div>{{ attraction.tel }}</div>
    <img :src="attraction.defaultImg">
    <div v-if='attraction.latitude && attraction.longitude'>
        <AttractionMap :longitude="attraction.longitude" :latitude="attraction.latitude"/>
    </div>

    <!-- 리뷰 점수별 통계 내기: 1~5 몇 명인지 카운트 -->
    
    <VYoutube />

</template>

<style scoped>
.attraction-detail {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

h1 {
  font-size: 2em;
  margin-bottom: 10px;
}

.attraction-info {
  display: flex;
  flex-direction: column;
}

.attraction-telephone {
  font-weight: bold;
  margin-bottom: 10px;
}

img {
  max-width: 100%;
  height: auto;
  margin-bottom: 20px;
}

</style>