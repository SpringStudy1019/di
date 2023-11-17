<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailReview, deleteReview } from '../../api/review';
import { listComment } from '../../api/comment';
import { useUserStore } from '@/stores/user'
import ReviewImage from './item/ReviewImage.vue';

const route = useRoute();
const router = useRouter();
const store = useUserStore()

const { reviewIdx } = route.params;

const review = ref({});
const comments = ref([]);

onMounted(() => {
  getReview();
  getComments();
});

// 리뷰 번호로 리뷰 얻어오기
const getReview = () => {
  detailReview(reviewIdx,
    ({ data }) => { // success callback
      review.value = data; // Store the data in the 'review' ref
    },
    (error) => {
      console.log(error);
      // Handle any errors here if needed
    }
  );
};

// 리뷰 댓글 얻어오기
const getComments = () => {
  listComment(reviewIdx,
    ({ data }) => { 
      comments.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: "review-list" });
}

function moveModify() {
  router.push({ name: "review-modify", params: { reviewIdx:reviewIdx } });
}

function onDeleteArticle() {
  if(!isDelete()) {
    return;
  }
  const { reviewIdx } = route.params;
  console.log(reviewIdx + "번글 삭제하러 가자!!!");
   // API 호출
   deleteReview(reviewIdx,
    ({data}) => {
      router.push({ name: "review-list" });
    }, (error) => {
      console.log(error);
    })
}

function isDelete() {
  return confirm("정말 삭제하시겠습니까?");
}

// 팔로잉 button 효과
function toggleFollow() {
  const followButton = document.getElementById('follow-button');

  if (followButton.textContent === '+ Follow') {
    // State Change: To Following
    followButton.textContent = 'Following';
    followButton.style.width = '75px'; // Adjust the width as needed
    followButton.style.backgroundColor = '#2EB82E';
    followButton.style.borderColor = '#2EB82E';
  } else {
    // State Change: Unfollow
    followButton.textContent = '+ Follow';
    followButton.style.width = '85px'; // Adjust the width as needed
    followButton.style.backgroundColor = '#ffffff';
    followButton.style.borderColor = '#3399FF';
  }
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">리뷰보기</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <div class="row my-2">
          <h2 class="text-bold px-2">{{ review.title }}</h2>
        </div>
        <div class="row">
          <div class="col-md-8">
            <div class="clearfix align-content-center">
              <span class="fw-bold">{{ review.userId }}</span>
              <button id="follow-button" @click="toggleFollow">+ Follow</button>	
              <div class="text-secondary fw-light">
                {{ review.registerDate }}
              </div>
            
              <p>
                <span class="text fw">
                  점수 : {{ review.score }}
                </span>
              </p>
            </div>
          </div>
          <!-- 댓글 개수 실제 댓글 개수 반영하기 -->
          <div class="col-md-4 align-self-center text-end">댓글 : 0</div>
          <div class="divider mb-3"></div>
          <div class="text-secondary">
            {{ review.content }}
          </div>
          <div >
            <review-image />
          </div>
          <div class="divider mt-3 mb-3"></div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
              리뷰목록
            </button>
            <button type="button" class="btn btn-outline-success mb-3 ms-1" @click="moveModify">
              리뷰수정
            </button>
            <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="onDeleteArticle">
              리뷰삭제
            </button>
          </div>
          <!-- 리뷰 댓글 -->
          <router-view :reviewIdx="parseInt(reviewIdx)"></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#follow-button {
  display: inline-block;
  color: #3399FF;
  font-family: "Helvetica";
  font-size: 10pt;
  background-color: #ffffff;
  border: 1px solid;
  border-color: #3399FF;
  border-radius: 3px;
  width: 85px;
  height: 30px;
  top: 50px;
  left: 50px;	
  margin-left: 10px;
  cursor: pointer;		
}

.img-group {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    border: black solid 1px;
    width: 10vh;
    margin: 20px;
}
</style>