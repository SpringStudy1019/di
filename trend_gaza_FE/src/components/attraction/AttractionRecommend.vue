<script setup>
import {ref, onMounted} from 'vue';
import {getHotplace} from '@/api/review';
import { registerBookmark, deleteBookmark } from "@/api/bookmark";
import { useUserStore } from '@/stores/user'

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

// 찜하기
const store = useUserStore()

const bookmarkInfo = ref({
    "userId": "",
    "contentId": "",
})

const bookmarkRequest = (contentId) => {
    if (!store.userInfo?.userId) {
        console.log(store.userInfo?.userId);
        let msg = "로그인을 먼저 해주세요!";
        alert(msg);
        return;
    }
    bookmarkInfo.value.userId = store.userInfo.userId;
    bookmarkInfo.value.contentId = contentId
    registerBookmark(bookmarkInfo.value,
    (response) => {
        let msg = "찜목록에 저장되었어요❤️"
        alert(msg)
    },
    (error) => {
        // if 406 status (Not Acceptable)
        //  alert "이미 찜을 하셨습니다!"
        if (error.response && error.response.status === 406) {
                let msg = "이미 찜을 하셨습니다!";
                alert(msg);
            } else {
                console.error(error);
            }
    }
    )
}

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
                        <router-link 
                        :to="{ name: 'attraction-view', params: { attractionIdx: hotplace.contentId } }" 
                        class="btn btn-primary me-2">
                            자세히 보기
                        </router-link>
                        <button 
                        class="btn btn-primary" 
                        @click="bookmarkRequest(hotplace.contentId)">
                            찜하기
                        </button>
                    </div>
                    </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
   
</template>

<style scoped>
#top {
    margin-top: 50px;
}



</style>