<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registReview, modifyReview, detailReview, getModifyReview } from "@/api/review";
import { useUserStore } from '@/stores/user'

import VSelect from '../../common/VSelect.vue';

import emptyStar from '@/assets/emptyStar.svg';

const router = useRouter();
const store = useUserStore()

const {VITE_VUE_API_URL} = import.meta.env;

const props = defineProps({ type: String, reviewIdx: String });

const contentLength = ref(0);
const titleLength = ref(0);
const today = ref(formattingDate(new Date()));
const score = ref(1);

const optionMsg = "------------------------------------------선택------------------------------------------";
const selectOption = ref([
    { text: optionMsg, value: ""},
    { text: "가족", value: "FAMILY" },
    { text: "친구", value: "FRIEND"},
    { text: "커플", value: "COUPLE" },
    { text: "혼자", value: "SOLO" },
    { text: "비즈니스", value: "BUSINESS"},
]);
const review = ref({
    reviewIdx: props.reviewIdx,
    score: 0,
    title: "",
    content: "",
    userId: store.userInfo.userId,       // 로그인했다는 가정
    companion: "",
    registerDate: "",
    startDate: "",
    endDate: "",
    contentId: "125405",        // 임시로 넣어둠(관광지 상세가 아직 없음)
});

if (props.type === "modify") {
    onMounted(() => {
        getReview();
    });
}

const getReview = () => {
    getModifyReview(props.reviewIdx,
        ({ data }) => { 
            review.value = data;  
            score.value = data.score;
            console.log(score.value);
            review.value.startDate = formattingDate(data.startDate);
            review.value.endDate = formattingDate(data.endDate);
            contentLength.value = data.content.length;
            titleLength.value = data.title.length;

            markingSelectOption();
        },(error) => {
            console.log(error);
        });
}


const scoreErrMsg = ref("");
const startDateErrMsg = ref("");
const endDateErrMsg = ref("");
const companionErrMsg = ref("");
const titleErrMsg = ref("");
const contentErrMsg = ref("");

watch(
    () => review.value.score,
    (value) => {
        if (value == 0) {
            scoreErrMsg.value = "점수를 선택해주세요!!!";
        } else scoreErrMsg.value = "";
    },
    { immediate: true }
);

watch(
    () => review.value.startDate,
    (value) => {
        if (value === "") {
            startDateErrMsg.value = "출발 일자를 선택해주세요!!!";
        } else startDateErrMsg.value = "";
    },
    { immediate: true }
);

watch(
    () => review.value.endDate,
    (value) => {
        if (value === "") {
            endDateErrMsg.value = "도착 일자를 선택해주세요!!!";
        } else if (value > today) {
            endDateErrMsg.value = "현재 일자보다 도착 일자가 클 수 없습니다!!!";
        }else if (value < review.value.startDate) {
            startDateErrMsg.value = "출발 일자는 도착일자보다 클 수 없습니다!!!";
        }else endDateErrMsg.value = "";
    },
    { immediate: true }
);

watch(
    () => review.value.companion,
    (value) => {
        let len = value.length;
        if (len == 0) {
            companionErrMsg.value = "동행자를 선택해주세요!!!";
        } else companionErrMsg.value = "";
    },
    { immediate: true }
);

watch(
    () => review.value.title,
    (value) => {
        let len = value.length;
        if (len == 0 || len > 20) {
            titleErrMsg.value = "제목을 확인해 주세요!!!";
        } else titleErrMsg.value = "";
    },
    { immediate: true }
);

watch(
    () => review.value.content,
    (value) => {
        let len = value.length;
        if (len == 0 || len > 2000) {
            contentErrMsg.value = "내용을 확인해 주세요!!!";
        } else contentErrMsg.value = "";
    },
    { immediate: true }
);

function onSubmit() {
    if (scoreErrMsg.value) {
        alert(scoreErrMsg.value);
    }
    else if (startDateErrMsg.value) {
        alert(startDateErrMsg.value);
    }
    else if (endDateErrMsg.value) {
        alert(endDateErrMsg.value);
    }
    else if (companionErrMsg.value) {
        alert(companionErrMsg.value);
    }
    else if (titleErrMsg.value) {
        alert(titleErrMsg.value);
    } else if (contentErrMsg.value) {
        alert(contentErrMsg.value);
    } else {
        props.type === "regist" ? writeReview() : updateReview();
    }
}

function writeReview() {
    //router.push({ name: "registReview", params: { review:review.value } });
    registReview(review.value,
        ({ data }) => {
            console.log(data);
            //router.push({name:"review-view", params: {reviewIdx:data.reviewIdx}});
            router.push({name:"review-list"});
        },
        (error) => {
            console.log(error);
        });
}

function check(index) {
    score.value = index + 1;
    review.value.score = index;
}

function updateReview() {
    //review.value.score = score.value - 1;
    modifyReview(review.value,
        ({data}) => {
            router.push({name:"review-list"});
        },
        (error) => {
            console.log(error);
        });
}

function formattingDate(date) {
    const dateObject = new Date(date);
    return dateObject.toISOString().split('T')[0];
}

const countTitle = () => {
    titleLength.value = review.value.title.length;
}

const countContent = () => {
    contentLength.value = review.value.content.length;
}

const markingSelectOption = () => {
    for (let i = 0; i < selectOption.value.length; i++) {
        const option = selectOption.value[i];

        if (option.value === data.companion) {
            selectOption.value[i].selected = selected;
        }
    }
}
</script>

<template>
    <form @submit.prevent='onSubmit'>
        <div class="content">
            <h3>별점으로 평가해주세요</h3>
            <div class="star-rating">
                <div class="star" v-for="index in 5" :key="index" @click="check(index)">
                    <span class="yello-star" v-if="index < score">
                        <img class="yellow-star" src="@/assets/yelloStar.svg">
                    </span>
                    <span v-if="index >= score">
                        <img class="empty-star" src="@/assets/emptyStar.svg">
                    </span>
                </div>
             </div> 
        </div>
        <div class="content">
            <h3>언제 다녀오셨나요?</h3>
            <div>
                <label for='startDate'>출발 </label>
                <input class="review-date" type='date' name='startDate' id='startDate' v-model='review.startDate' :max="today"/>
            </div>
            <div>
                <label for='endDate'>도착 </label>
                <input class="review-date" type='date' name='endDate' id='endDate' v-model='review.endDate' :max="today"/>
            </div>
        </div>
        <div class="content">
            <h3>누구와 함께 가셨나요?</h3>
            <VSelect class="select-option" :selectOption='selectOption' v-model="review.companion" />
        </div>
        <div class="content">
            <h3>제목</h3>
            <input class="review-title" type='text' v-model='review.title' @input="countTitle()" placeholder='제목'/>
            <span class="max-word-length right-align">{{titleLength}}/20 최대 글자수</span>
        </div>
        <div class="content">
            <h3>리뷰 쓰기</h3>
            <textarea class="review-body" v-model='review.content' @input="countContent()"></textarea>
            <span class="max-word-length right-align">{{contentLength}}/2000 최대 글자수</span>
        </div>
        <div class="content">
            <p class="upload-label">[선택 사항] 이미지 업로드하기</p>
            <input type='file'>
        </div>
        <div class="content">
            <button class="btn" type='submit' v-if="type === 'regist'">리뷰 제출</button>
            <button class="btn" type='submit' v-else>리뷰 수정</button>    
        </div>
    </form>
</template>

<style scoped>
*{
    margin: 0;
    padding: 0;
}
.content {
    margin: 20px;
}

.review-body {
    width: 100%;
    height: 300px;
}

.review-title {
    width: 100%
}

.review-date {
    width: 50%;
    margin: 10px;
}

.star {
    display: inline;
}

.empty-star {
    width: 24px;
    margin-right: 10px;
}

.yellow-star {
    width: 24px;
}

.right-align {
    display: block;
    text-align: right;
}

.btn {
    background-color: black;
    color: white;
    border-radius: 28px;
    width: 100%;
    height: auto;
    padding: 5px;
}

.upload-label {
    margin-bottom: 8px;
}

.select-option {
    text-align: center;
}
</style>