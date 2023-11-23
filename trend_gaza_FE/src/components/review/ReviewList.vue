<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

import VSelect from "@/components/common/VSelect.vue";
import ReviewListItem from "@/components/review/item/ReviewListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";
import { list } from "@/api/review";

const router = useRouter();

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "리뷰번호", value: "review_idx" },
  { text: "제목", value: "title" },
  { text: "작성자 아이디", value: "user_id" },
]);

const reviews = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_REVIEW_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_REVIEW_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getReviewList();
});

const getReviewList = () => {
  // console.log("서버에서 글목록 얻어오자!");
  // API 호출
  list(
    param.value,
    ({ data }) => {
      reviews.value = data.reviews;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      router.push({ name: "user-login" });
      console.log(error);
    }
  );
};

const changeKey = (val) => {
  // console.log("ReviewList에서 선택한 조건 : " + val);
  param.value.key = val;
};

const onPageChange = (val) => {
  // console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  getReviewList();
};
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10"></div>
      <div class="col-lg-10">
        <div class="row align-self-center mb-2">
          <div class="col-md-2 text-start">
            <!-- <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
              리뷰 쓰기
            </button> -->
          </div>
          <div class="col-md-5 offset-5">
            <form class="d-flex" @submit.prevent="getReviewList">
              <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
              <div class="input-group input-group-sm">
                <input
                  type="text"
                  class="form-control"
                  v-model="param.word"
                  placeholder="검색어..."
                  @keyup.enter="getReviewList"
                />
                <button class="btn btn-dark" type="button" @click="getReviewList">검색</button>
              </div>
            </form>
          </div>
        </div>

        <div class="card-columns">
          <ReviewListItem
            v-for="review in reviews"
            :key="review.reviewIdx"
            :review="review"
          ></ReviewListItem>
        </div>
      </div>

      <PageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @pageChange="onPageChange"
      ></PageNavigation>
    </div>
  </div>
</template>

<style scoped></style>
