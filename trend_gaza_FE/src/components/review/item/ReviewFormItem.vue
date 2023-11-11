<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registReview, modifyReview, detailReview, getModifyReview } from "@/api/review";

import VSelect from '../../common/VSelect.vue';

import emptyStar from '@/assets/emptyStar.svg';

const router = useRouter();
const route = useRoute();

const {VITE_VUE_API_URL} = import.meta.env;

const props = defineProps({ type: String, reviewIdx: String });
console.log("부모에게 받은 값" + props.reviewIdx);

const score = ref(1);
const isUesId = ref(false);
const optionMsg = "------------------------------------------------------------------------------선택------------------------------------------------------------------------------";
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
    userId: "alswjd",       // 로그인했다는 가정
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
    console.log(props.reviewIdx);
    console.log(props.reviewIdx + "번글 얻으러 가자!!!");

    getModifyReview(props.reviewIdx,
            ({ data }) => { 
                console.log(data);
                review.value = data;  
                score.value = data.score;

                // select 요소의 각 option을 조사
                console.log(selectOption.value.length);
                for (let i = 0; i < selectOption.value.length; i++) {
                const option = selectOption.value[i];
                console.log(option);

                // 현재 옵션의 값과 타겟 값이 일치하면 checked 속성 부여
                if (option.value === data.companion) {
                    option.selected = true;
                    // 또는 option.setAttribute('selected', 'selected'); 를 사용할 수도 있습니다.
                }
            }
    },
    (error) => {
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
        } else endDateErrMsg.value = "";
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
        if (len == 0 || len > 30) {
            titleErrMsg.value = "제목을 확인해 주세요!!!";
        } else titleErrMsg.value = "";
    },
    { immediate: true }
);

watch(
    () => review.value.content,
    (value) => {
        let len = value.length;
        if (len == 0 || len > 500) {
            contentErrMsg.value = "내용을 확인해 주세요!!!";
        } else contentErrMsg.value = "";
    },
    { immediate: true }
);

// const changeKey = (val) => {
//     param.value.key = val;
// }

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
    this.score = index + 1;
}

function updateReview() {
    review.value.score = score.value - 1;
    modifyReview(review.value,
        ({data}) => {
            router.push({name:"review-list"});
        },
        (error) => {
            console.log(error);
        });
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
                    <span v-else>
                        <img class="empty-star" src="@/assets/emptyStar.svg">
                    </span>
                </div>
             </div> 
        </div>
        <div class="content">
            <h3>언제 다녀오셨나요?</h3>
            <div>
                <lable for='startDate'>출발 </lable>
                <input class="review-date" type='date' name='startDate' id='startDate' v-model='review.startDate'/>
            </div>
            <div>
                <label for='endDate'>도착 </label>
                <input class="review-date" type='date' name='endDate' id='endDate' v-model='review.endDate'/>
            </div>
        </div>
        <div class="content">
            <h3>누구와 함께 가셨나요?</h3>
            <VSelect class="select-option" :selectOption='selectOption' v-model='review.companion' />
        </div>
        <div class="content">
            <h3>제목</h3>
            <input class="review-title" type='text' v-model='review.title' placeholder='제목'/>
            <span class="max-word-length right-align">20 최대 글자수</span>
        </div>
        <div class="content">
            <h3>리뷰 쓰기</h3>
            <textarea class="review-body" v-model='review.content'></textarea>
            <span class="max-word-length right-align">2000 최대 글자수</span>
        </div>
        <div class="content">
            <p class="upload-label">[선택 사항] 이미지 업로드하기</p>
            <input type='file'>
        </div>
        <div class="content">
            <button type='submit' v-if="type === 'regist'">리뷰 제출</button>
            <button class="btn-modify" type='submit' v-else>리뷰 수정</button>    
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

.btn-modify {
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