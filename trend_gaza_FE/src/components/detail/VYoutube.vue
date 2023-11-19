<script setup>
import { ref, onMounted, defineProps } from "vue";
import { searchVideos } from "@/api/youtube";

const { VITE_YOUTUBE_SERVICE_KEY, VITE_YOUTUBE_URL } = import.meta.env;

let baseUrl = VITE_YOUTUBE_URL // https://youtube.googleapis.com/youtube/v3/search
const props = defineProps({
  attractionTitle: String
});

// const query = ref('');
const videos = ref([]);

onMounted(() => {
  searchYoutube();
});

const searchYoutube = () => {
  const keyword = `${props.attractionTitle} 여행`;
  const params = {
    part: 'snippet',
    maxResults: 3,
    q: keyword,
    type: 'video',
    key: VITE_YOUTUBE_SERVICE_KEY
  };

  searchVideos(params, (response) => {
    makeList(response.data);
  }, (error) => {
    console.error('Error searching YouTube:', error);
  });
};

const makeList = (data) => {
  videos.value = data.items.map(item => ({
    videoId: item.id.videoId,
    iframe: `
      <iframe id="ytplayer" type="text/html" width="720" height="405"
        src="https://www.youtube.com/embed/${item.id.videoId}"
        frameborder="0" allowfullscreen></iframe><br/>
    `
  }));
};

</script>

<template>
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
.bottom-margin {
  margin-bottom: 50px;
}
</style>