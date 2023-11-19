<script setup>
import {ref, onMounted, computed} from 'vue';
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

const total = computed(() => {
  return scoresInfo.value.one + scoresInfo.value.two + scoresInfo.value.three + scoresInfo.value.four + scoresInfo.value.five;
});

console.log(total)
console.log(scoresInfo.value.one)
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
    <div class="review-score">
      <h3 id="review-header">리뷰</h3>
      <div class="bar"> 
      <div>최악</div>
      <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="{{ scoresInfo.one }}" aria-valuemin="0" aria-valuemax="{{ total }}">
        <div class="progress-bar" :style="{ width: Math.round(scoresInfo.one / total * 100) + '%' }"></div>
        <div class="number">{{ scoresInfo.one }}</div>
      </div>
      <div>별로</div>
      <div class="bar-space"></div>
      <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="{{ scoresInfo.two }}" aria-valuemin="0" aria-valuemax="{{ total }}">
        <div class="progress-bar" :style="{ width: Math.round(scoresInfo.two / total * 100) + '%' }"></div>
        <div class="number">{{ scoresInfo.two }}</div>
      </div>
      <div>보통</div>
      <div class="bar-space"></div>
      <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="{{ scoresInfo.three }}" aria-valuemin="0" aria-valuemax="{{ total }}">
        <div class="progress-bar" :style="{ width: Math.round(scoresInfo.three / total * 100) + '%' }"></div>
        <div class="number">{{ scoresInfo.three }}</div>
      </div>
      <div>좋음</div>
      <div class="bar-space"></div>
      <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="{{ scoresInfo.four }}" aria-valuemin="0" aria-valuemax="{{ total }}">
        <div class="progress-bar" :style="{ width: Math.round(scoresInfo.four / total * 100) + '%' }"></div>
        <div class="number">{{ scoresInfo.four }}</div>
      </div>
      <div>아주좋음</div>
      <div class="bar-space" ></div>
      <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="{{ scoresInfo.five }}" aria-valuemin="0" aria-valuemax="{{ total }}">
        <div class="progress-bar" :style="{ width: Math.round(scoresInfo.five / total * 100) + '%' }"></div>
        <div class="number">{{ scoresInfo.five }}</div>
      </div>
    </div>
    </div>
    
    <VYoutube />

</template>

<style scoped>
.number {
  margin-left: 10px;
  margin-top: -2px;
}
.review-score {
  background-color: antiquewhite;
  border-radius: 15px; /* Adjust the border-radius to your preference */
  padding: 20px; /* Adjust padding for top and bottom spacing */
  margin: 20px 50px; /* Adjust margins for left and right spacing */
}
#review-header {
  margin-left: 40px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.bar {
  margin-left: 20px; /* Adjust left margin */
  margin-right: 20px; /* Adjust right margin */
}
.bar-space {
  margin-top: 10px;
}

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