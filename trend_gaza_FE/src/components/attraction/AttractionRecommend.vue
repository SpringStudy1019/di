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
                <h2 class='subtitle'>사용자들이 가장 많은 리뷰를 남긴 여행지에요</h2>
                <div class='margin-small'></div>
                <!-- 여행지 TOP5-->
                <div class="card-columns">
                <div v-for='hotplace in hotplaces' :key="hotplace.contentId">
                <div class="card mb-3">
                <div class="image">
                    <div v-if="hotplace.firstImage === ''">
                        <img class="card-img-top" 
                            src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/no_image.jpg" />
                        </div>
                        <div v-else>
                            <img class="card-img-top" :src="hotplace.firstImage" />
                        </div>
                </div>
                <div class="card-header text-center">
                    {{hotplace.title}}
                </div>

                <div class="card-footer text-muted text-center">
                <router-link
                    :to="{ name: 'attraction-view', params: { attractionIdx: hotplace.contentId } }"
                    class="btn btn-primary me-2"
                >
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
        </div>
            <!-- 여행지 TOP5-->
              
            <div class="col-1"></div>
        </div>
    </div>
   </div>
</template>

<style scoped>
.image img {
    width: 343px;
    height: 300px; 
    object-fit: cover;
}
.subtitle {
    font-weight: 600;
}
#top {
    margin-top: 50px;
}

.margin-small {
    margin-bottom: 30px;
}


</style>