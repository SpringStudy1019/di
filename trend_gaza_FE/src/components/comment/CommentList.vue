<script setup>
import { ref, onMounted, defineProps } from "vue";
// import { useRouter } from "vue-router";

import { listComment } from "@/api/comment";

const props = defineProps({
  reviewIdx: Number, // Define the prop to accept reviewIdx as a Number
});

const comments = ref([]);

onMounted(() => {
    getCommentList();
});


const getCommentList = () => {
    listComment(props.reviewIdx,
    (response) => {
      console.log("Success:", response.status, response.data);
      comments.value = response.data;
    },
    (error) => {
        console.log(error);
    }
  );
};

</script>

<template>
   <h2 id="comment-title">이 리뷰에 대한 댓글</h2>
    <div class="comment-list">
      <div v-for="comment in comments" :key="comment.idx" class="comment">
        <div class="comment-content">{{ comment.content }}</div>
        <div class="comment-meta">
          <span class="comment-date">{{ comment.registerDate }}</span>
          <span class="comment-author">{{ comment.userId }}</span>
          <span class="comment-edit-date">{{ comment.modifyDate }}</span>
        </div>
        <div>
        <button type="button" class="btn btn-outline-primary mb-1 ms-3" @click="moveModify">
              수정
        </button>
        <button type="button" class="btn btn-outline-danger mb-1 ms-1" @click="onDeleteArticle">
              삭제
        </button>
        </div>
      </div>
    </div>
   
</template>

<style scoped>
#comment-title {
  margin-top: 50px; /*윗부분에 마진을 20px 줍니다.*/
  margin-bottom: 30px; /*아랫부분에 마진을 20px 줍니다.*/
  font-size: 24px; /*글자 크기를 24px로 설정합니다.*/
  color: #FF5733; /*글자 색상을 화려한 색상으로 설정합니다.*/
}


.comment-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 500px;
  margin: 0 auto;
}

.comment {
  background-color: #f7f7f7;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.comment-content {
  font-size: 16px;
  line-height: 1.4;
}

.comment-meta {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #888;
}

.comment-date {
  flex: 1;
}

.comment-author {
  flex: 1;
  text-align: center;
}

.comment-edit-date {
  flex: 1;
  text-align: right;
}
</style>