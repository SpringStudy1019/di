<script setup>
import { ref, onMounted } from 'vue';
import { followList } from "@/api/follow";
import { registNoti } from "@/api/notification";
import { useUserStore } from '@/stores/user';
import { getInvitedPlan, getCreatedPlan, getPlanDetail } from "@/api/plan";

onMounted(() => {
    getPlanRequest();
    getInvitedPlans();
});

const store = useUserStore()

// 친구 보였다가, 안 보였다하는 상태
const showFriend = ref(false);
const showMyFriend = () => {
    showFriend.value = !showFriend.value
    if (showFriend.value) {
        requestFriends()
    }
}

const friends = ref([])
const requestFriends = () => {
   // API 호출
   followList(store.userInfo.userId,
      ({ data }) => {  
          friends.value = data;
    },
    (error) => {
        console.log(error);
    });
};

// 내가 계획한 여행 가져오기
const myPlans = ref([])
const getPlanRequest = () => {
    getCreatedPlan(
        store.userInfo.userId,
        ({ data }) => {  
                myPlans.value = data;
            console.log("내가 계획한 여행", myPlans.value)
            // console.log("내가 계획한 여행 개수", myPlans.value.length)
            showMyJourney.value = Array(myPlans.value.length).fill(false);
        },
        (error) => {
            console.log(error);
        });
}

// 날짜 계산
function calculateDays(startDateStr, endDateStr) {
  const startDate = new Date(startDateStr);
  const endDate = new Date(endDateStr);
  const timeDifference = endDate - startDate;
  // Convert milliseconds to days
  const daysDifference = timeDifference / (1000 * 60 * 60 * 24);
  // Round to the nearest whole number
  const roundedDays = Math.round(daysDifference);
  return roundedDays;
}
const formatDate = (dateStr) => {
    const date = new Date(dateStr);
  return date.toLocaleDateString();
};

// 여행 갈래 요청 (알림 발송)
const notiInfo = ref({
  "message": "",
  "pathId": "",
  "pushCase": "PERSONAL",
  "pushDate": "",
  "pushStatus": "INCOMPLETE",
  "userIdFrom": store.userInfo.userId,
  "userIdTo": ""
})
const notificationRequest = (friend, title) => {
    notiInfo.value.userIdTo = friend
    // message에 여행 title set
    notiInfo.value.message = title
   // API 호출
    registNoti(
        notiInfo.value,
      (response) => {  
        let msg = "알림이 발송되었습니다! 친구의 답변을 조금만 기다려주세요!"
        alert(msg);
    },
    (error) => {
        console.log(error);
    });
};

// 내가 초대된 여행 조회하기
const invitations = ref([])
const getInvitedPlans = () => {
   // API 호출
    getInvitedPlan(
        store.userInfo.userId,
      ({ data }) => {  
        invitations.value = data;
    },
    (error) => {
        console.log(error);
    });
};

// 내가 만든 여행 일정 상세 조회
const showMyJourney = ref([]);

const showJourneyToggle = (planIdx) => {
    showMyJourney.value[planIdx] = !showMyJourney.value[planIdx];
}
const journey = ref([])
const planDetail = (planIdx) => {
    showJourneyToggle(planIdx);
    getPlanDetail(
        planIdx,
        ({ data }) => {
            journey.value = data;
            console.log("journey:::::::::", journey)
        },
        (error) => {
            console.log(error);
        }
    );
}

// 친구가 만든 여행 일정 상세 조회
const showYourJourney = ref([]);

// TASK 2
// attraction_plan에서 이미 저장한 데이터가 있다면
// 여행계획짜기에서 다시 불러와서 수정 api를 써야 함.
</script>

<template>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <div class="col-2"></div>
        <div class='margin'></div>

        <!-- part.1 start -->
        <h1>내가 계획한 여행</h1>
        <div v-if='myPlans.length > 0'>
            <div class='row'>
            <div class="col-sm-6" v-for='(myPlan, index) in myPlans' :key='index'>
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">{{ myPlan.title }}</h5>
                        <p class="card-text">
                            {{ myPlan.userCount === 0 ? 
                            "나 홀로 하는 여행" : `${myPlan.userCount}명과 함께하는 여행` }}
                            <br>
                            {{ formatDate(myPlan.startDate) }}부터 {{ formatDate(myPlan.endDate) }}까지
                            ({{calculateDays(myPlan.startDate, myPlan.endDate)}}일)
                        </p>
                        <!-- 먼저 짜놓은 계획이 있는지 확인 -->
                        <!-- <router-link 
                        :to="{ name: 'plan-map'}" 
                        class="btn btn-primary me-2">
                        여행계획짜기
                        </router-link>
                        <router-link 
                        :to="{ name: 'plan-modify', params: { planIdx: myPlan.planIdx } }" 
                        class="btn btn-primary me-2">
                        여행계획수정
                        </router-link> -->
                        <button class="btn btn-warning me-2" 
                        @click='planDetail(myPlan.planIdx)'
                        >여행일정</button>
                        <button @click='showMyFriend' class="btn btn-success">친구 초대하기</button>
                        <!-- 친구 초대하기 버튼을 클릭하면 친구가 뜬다 -->
                        <div v-if='showFriend' >
                            <div>
                                <div class='margin-small-friend'></div>
                                <template v-for="friend in friends" :key="friend">
                                    {{ friend.userName }} ({{friend.userId}})
                                    <button @click="notificationRequest(friend.userId, myPlan.title)" 
                                    class="btn btn-outline-success">여행갈래?</button>
                                    <div class='margin-small'></div>
                                </template>
                            </div>
                        </div>
                        <!-- 여행 일정 start --><!-- 여행 일정 버튼을 클릭하면 show -->
                        <div class="col-sm-6" v-if='showMyJourney[myPlan.planIdx]'>
                            <div class='margin-big'></div>
                            <div>
                                <h3>여행 일정</h3>
                                <div v-for='place in journey' :key='place.contentId'>
                                    {{ place.title }}
                                </div>
                            </div>
                        </div>
                        <!-- 여행 일정 end -->
                    </div>
                </div>
                <div class='margin'></div>
                
            </div>
        </div>

        </div>

        <div v-else >
            아직 여행 계획이 없어요!
        </div>
            
            <!-- part.1 end -->

            <!-- part.2 start -->
        <h1>내가 초대된 여행 계획</h1>
        <div class="col-sm-6">
            <div class="card text-center">
                <div class="card-body">
            <div v-if='invitations.length > 0'>
            <div v-for='invitation in invitations' :key='invitation.planIdx'>
                <h5 class="card-title">{{invitation.title}}</h5>
                <p class="card-text">{{invitation.userId}}이 만든 여행이에요. <br>
                    {{ formatDate(invitation.startDate) }}부터 {{ formatDate(invitation.endDate) }}까지 
                    ({{calculateDays(invitation.startDate, invitation.endDate)}}일)</p>
                <router-link 
                    :to="{ name: 'plan'}" 
                    class="btn btn-primary me-2">
                    여행계획짜기
                </router-link>
                <button class="btn btn-warning me-2" 
                    @click='planDetail(invitation.planIdx)'
                    >
                    여행일정
                </button>
            </div>
        </div>
        <div v-else>
            아직 초대된 여행 계획이 없어요!
        </div>
    </div>
        </div>
    </div>
            <!-- part.2 end -->
            
    </div>
    </div>
    
    <div class="col-2"></div>

</template>

<style scoped>
.margin {
    margin-bottom: 30px;
}
.margin-big {
    margin-bottom: 50px;
}

.margin-small {
    margin-bottom: 10px;
}

.margin-small-friend {
    margin-bottom: 10px;
}
</style>