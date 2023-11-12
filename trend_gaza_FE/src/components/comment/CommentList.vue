<script setup>
import { ref, onMounted, defineProps } from "vue";
import { useRouter } from "vue-router";

import { listComment, deleteCommentRequest, registerCommentRequest } from "@/api/comment";

const props = defineProps({
  reviewIdx: Number, // Define the prop to accept reviewIdx as a Number
});

const comments = ref([]);
const commentContent = ref('');

const router = useRouter();

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

function deleteComment(commentIdx) {
    if(!isDelete()) {
    return;
  }
  deleteCommentRequest(commentIdx,
    ({data}) => {
        getCommentList();
      router.push({ name: "review-view" });
    }, (error) => {
      console.log(error);
    })
}

function registerComment() {
    if (!commentContent.value) {
    return;
  }

  const newComment = {
    content: commentContent.value,
    registerDate: "",
    reviewIdx: props.reviewIdx,
    userId: "user01"

  }
    registerCommentRequest(newComment,
        ({ data }) => {
            console.log(data);
            router.push({name:"review-view"});
        },
        (error) => {
            console.log(error);
        });
}


function isDelete() {
  return confirm("정말 삭제하시겠습니까?");
}

</script>

<template>
   <h2 id="comment-title">이 리뷰에 대한 댓글</h2>
   <div class="comment-register">
    <div class="comment-regist">
    <div class="review-register-form">
        <input v-model="commentContent" placeholder="Enter your comment" />
        <button type="button" class="btn btn-outline-primary mb-1 ms-3" @click="registerComment">
            등록</button>
        </div>
    </div>
    </div>  

    <div class="comment-list">
      <div v-for="comment in comments" :key="comment.commentIdx" class="comment">
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
        <button type="button" class="btn btn-outline-danger mb-1 ms-1" @click="() => deleteComment(comment.commentIdx)">
              삭제
        </button>
        </div>
      </div>
    </div>
   
</template>

<style scoped>
#comment-title {
  margin-top: 50px; 
  margin-bottom: 30px; 
  font-size: 30px; 
  color: #ff9cbf;
}

.comment-register {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 500px;
  margin: 0 auto;
}

.comment-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 500px;
  margin: 0 auto;
}

.comment-regist {
  background-color: #ff9cbf;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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