<script setup>
import {ref, onMounted, computed} from 'vue';
import {useRoute, useRouter} from "vue-router";
import AttractionMap from './item/AttractionMap.vue';
import {getAttractionDetail} from '@/api/attraction';
import {getScores} from '@/api/review';
import { searchVideos } from "@/api/youtube";

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
        // searchYoutube();    // 과도한 api 사용을 막기 위해 잠시 주석처리. 
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

// 유튜브 영상
const { VITE_YOUTUBE_SERVICE_KEY, VITE_YOUTUBE_URL } = import.meta.env;
const videos = ref([]);
const searchYoutube =  () => {
  const params = {
    part: 'snippet',
    maxResults: 3,
    q: `${attraction.value.title} 여행`,
    type: 'video',
    key: VITE_YOUTUBE_SERVICE_KEY
  };

  searchVideos(params, 
  (response) => {
    const data = response.data;
    videos.value = data.items.map(item => ({
      videoId: item.id.videoId,
      iframe: `
        <iframe id="ytplayer" type="text/html" width="720" height="405"
          src="https://www.youtube.com/embed/${item.id.videoId}"
          frameborder="0" allowfullscreen></iframe><br/>
      `
    }));
  },
  // (response) => {
  //   makeList(response.data);
  // }, 
  (error) => {
    console.error('Error searching YouTube:', error);
  });
};
</script>

<template>
   <div class="margin"></div>
    <h1 class="title">{{ attraction.title }}</h1>
    <div class="margin"></div>
    <div class="row">
    <div class="col-1"></div>
    <div class="col-3">
        <div class="list-group" id="list-tab" role="tablist">
          <a class="list-group-item list-group-item-action active" id="list-home-list" 
            data-bs-toggle="list" href="#list-home" role="tab" aria-controls="home">
            여행지 소개 ✋  </a>
            <a class="list-group-item list-group-item-action" id="list-messages-list" 
            data-bs-toggle="list" href="#list-messages" role="tab" aria-controls="messages">
               주소
            </a>
            <a class="list-group-item list-group-item-action" id="list-profile-list" 
            data-bs-toggle="list" href="#list-profile" role="tab" aria-controls="profile">
              전화번호</a>
          <button class="list-group-item list-group-item-action" id="list-settings-list" data-bs-toggle="list" 
          @click="moveWrite(attraction.contentId)" role="tab" aria-controls="settings">
            리뷰쓰러가자</button>
        </div>
    </div>
    <div class="col-7">
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
              {{ attraction.overview }}
            </div>
            <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
              {{ attraction.tel }}
            </div>
            <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">
              {{ attraction.address }}
            </div>
            <!-- <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">
            </div> -->
        </div>
    </div>
    <div class="col-1"></div>
</div>
  <div class="margin"></div>

    <!-- 이미지 -->
    <img :src="attraction.defaultImg">
    <div class="margin"></div>

    <h3 class="title2">지도에서 보기</h3>
    <!-- 지도 -->
    <div v-if='attraction.latitude && attraction.longitude'>
        <AttractionMap :longitude="attraction.longitude" :latitude="attraction.latitude"/>
    </div>
    <hr>
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
    <hr>
    <!-- 유튜브 영상 -->
    <!-- <VYoutube :attractionTitle="attraction.title" /> -->
    <div class="container-fluid p-4">
        <h1 class="text-center">여행 유튜버가 알려주는 여행 꿀팁</h1>
        <!-- <p class="text-center">검색어를 입력하면 다양한 여행 영상이 제공됩니다</p> -->
    </div>

    <!-- <div class="d-flex justify-content-center align-items-center mb-3">
        <input v-model="query" type="text" class="form-control mx-5" 
        placeholder="가고 싶은 여행지를 입력하세요" name="query"  @keypress.enter="searchYoutube">
        <button @click="searchYoutube" type="button" class="btn btn-outline-primary me-5">Search</button>
    </div> -->

    <div class="container text-center">
        <div v-for="(video, index) in videos" :key="index" v-html="video.iframe"></div>
    </div>
    <div class="bottom-margin"></div>

</template>

<style scoped>
img {
    display: block;
    margin: 0 auto; 
    height: 500px;
    width: 500px; 
}
.title {
    margin-top: 20px; /* Adjust the top margin as needed */
    text-align: center;
    font-size: 35px;
}

.title2 {
  text-align: center;
}
.margin {
  margin-bottom: 50px;
}
.number {
  margin-left: 10px;
  margin-top: -2px;
}
.review-score {
  background-color: antiquewhite;
  border-radius: 15px; 
  padding: 20px; 
  margin: 20px 50px; 
}
#review-header {
  margin-left: 40px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.bar {
  margin-left: 20px; 
  margin-right: 20px; 
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
.bottom-margin {
  margin-bottom: 50px;
}
</style>