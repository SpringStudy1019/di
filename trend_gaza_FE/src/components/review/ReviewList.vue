<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { list } from "@/api/review";

const router = useRouter();

const reviews = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
    getReviewList();
});

const getReviewList = () => {
    console.log("서버에서 글목록 얻어오자!!!", param.value);
   // API 호출
    list(param.value,
    ({ data }) => {    // data.DTO필드명
        reviews.value = data.review;
        currentPage.value = data.currentPage;
        totalPage.value = data.totalPageCount;
    },
    (error) => {
        console.log(error);
    });
};

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getReviewList();
};

const moveWrite = () => {
    router.push({ name: "review-write" });
};

</script>

<template>
    <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">리뷰 목록</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="row align-self-center mb-2">
          <div class="col-md-2 text-start">
            <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
              리뷰 쓰기
            </button>
          </div>
          <div class="col-md-5 offset-5">
            <form class="d-flex">
              <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
              <div class="input-group input-group-sm">
                <input
                  type="text"
                  class="form-control"
                  v-model="param.word"
                  placeholder="검색어..."
                />
                <button class="btn btn-dark" type="button" @click="getArticleList">검색</button>
              </div>
            </form>
          </div>
        </div> 
        
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">리뷰 번호</th>
              <th scope="col">제목</th>
              <th scope="col">점수</th>
              <th scope="col">내용</th>
              <th scope="col">작성일</th>
              <th scope="col">수정일</th>
              <th scope="col">작성자</th>
              <th scope="col">관광지번호</th>
            </tr>
          </thead>
          <tbody>
            <ReviewListItem
                v-for="review in reviews"
                :key="review.reviewIdx"
                :review="review"
            ></ReviewListItem>
          </tbody>
        </table>
      </div>
      <PageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @pageChange="onPageChange"
      ></PageNavigation>
    </div>
  </div>
</template>

<style scoped>

</style>