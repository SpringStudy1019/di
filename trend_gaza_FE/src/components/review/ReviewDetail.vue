<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailReview } from '../../api/review';

const route = useRoute();
const router = useRouter();

const { reviewIdx } = route.params.reviewIdx;

const review = ref({});

onMounted(() => {
  getReview();
});

const getReview = () => {
  // const { articleno } = route.params;
  console.log(reviewIdx + "번글 얻으러 가자!!!");
    // API 호출
    detailReview(reviewIdx);
   
};

function moveList() {
  router.push({ name: "review-list" });
}

function moveModify() {
  router.push({ name: "review-modify", params: { reviewIdx } });
}

function onDeleteArticle() {
  // const { articleno } = route.params;
  console.log(reviewIdx + "번글 삭제하러 가자!!!");
   // API 호출
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
          <h2 class="text-secondary px-5">{{ review.reviewIdx }}. {{ review.title }}</h2>
        </div>
        <div class="row">
          <div class="col-md-8">
            <div class="clearfix align-content-center">
              <img
                class="avatar me-2 float-md-start bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
              />
              <p>
                <span class="fw-bold">김싸피</span> <br />
                <span class="text-secondary fw-light">
                  {{ review.registerDate }}1 점수 : {{ review.score }}
                </span>
              </p>
            </div>
          </div>
          <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
          <div class="divider mb-3"></div>
          <div class="text-secondary">
            {{ review.content }}
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
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>