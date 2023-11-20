<script setup>
import { ref, onMounted } from 'vue';
import { followList } from "@/api/follow";
import { getPlans } from "@/api/plan";
import { useUserStore } from '@/stores/user'

onMounted(() => {
    getPlanRequest();
});

const store = useUserStore()


// 여행 계획이 있는 사용자인지 체크

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
        console.log("friends 데이터 넘어왔다!", friends.value)
    },
    (error) => {
        console.log(error);
    });
};

// 내가 계획한 여행 가져오기
const myPlans = ref([])

const getPlanRequest = () => {
    console.log("여기에 들어왔다!")
    getPlans(
    ({ data }) => {  
            myPlans.value = data;
            console.log(myPlans.value)
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
</script>

<template>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <div class="col-2"></div>
        <div class='margin'></div>
        <h1>내가 초대된 여행 계획</h1>
        <div class="col-sm-6">
        <div class="card text-center">
        <div class="card-body">
            <h5 class="card-title">plan.title</h5>
            <p class="card-text">user_id와 함께하는 여행이에요. <br>
                startDate부터 endDate까지 (00일)</p>
            <router-link 
                :to="{ name: 'plan'}" 
                class="btn btn-primary me-2">
                여행계획짜기
            </router-link>
   
            <!-- 여행계획 상세보기 -->
           
           
        </div>
        </div>
        <div class='margin'></div>
        <h1>내가 계획한 여행</h1>
        <div class="card text-center">
        <div class="card-body">
            <div v-for='myPlan in myPlans' :key='myPlan.planIdx'>
                <h5 class="card-title">{{myPlan.title}}</h5>
                <p class="card-text">
                    {{ myPlan.userCount === 0 ? 
                    "나 홀로 하는 여행" : `${myPlan.userCount}명과 함께하는 여행` }}
                    <br>
                    {{ formatDate(myPlan.startDate) }}부터 {{ formatDate(myPlan.endDate) }}까지
                    ({{calculateDays(myPlan.startDate, myPlan.endDate)}}일)</p>
                <button @click='showMyFriend'>친구 초대하기</button>
                <!-- 친구 초대하기 버튼을 클릭하면 친구가 뜬다 -->
                <div v-if='showFriend'>
                <ul>
                    <li v-for="friend in friends" :key="friend">
                        {{ friend }}</li>
                </ul>
            </div>
        </div>
        </div>
        </div>
    </div>
    </div>
    <div class="col-2"></div>
</div>
</template>

<style scoped>
.margin {
    margin-bottom: 30px;
}
</style>