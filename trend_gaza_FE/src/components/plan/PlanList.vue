<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from "vue-router";
import { followList } from "@/api/follow";
import { registNoti } from "@/api/notification";
import { useUserStore } from '@/stores/user';
import { getInvitedPlan, getCreatedPlan, getPlanDetail, getAttractionPlan, getParticipant } from "@/api/plan";

onMounted(() => {
    getPlanRequest();
    getInvitedPlans();
});

const store = useUserStore()
const router = useRouter();

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

// 내가 초대된 여행 조회하기
const invitations = ref([])
const showYourJourney = ref([]);
const getInvitedPlans = () => {
   // API 호출
    getInvitedPlan(
        store.userInfo.userId,
      ({ data }) => {  
        invitations.value = data;
        showYourJourney.value = Array(invitations.value.length).fill(false);
    },
    (error) => {
        console.log(error);
    });
};

const showYourJourneyToggle = (planIdx) => {
    showYourJourney.value[planIdx] = !showYourJourney.value[planIdx];
}

// 친구가 만든 여행 일정 상세 조회
const yourPlanDetail = (planIdx) => {
    showYourJourneyToggle(planIdx)
    console.log("showjourney toggle", showYourJourney.value)
    getPlanDetail(
        planIdx,
        ({ data }) => {
            journey.value = data;
            console.log("찬구가 만든 journey:::::::::", journey)
        },
        (error) => {
            console.log(error);
        }
    );
}

// 여행 계획 등록 또는 수정 
const checkPlan = (planIdx) => {
    getPlanDetail(
        planIdx,
        ({ data }) => {
            journey.value = data;
        },
        (error) => {
            console.log(error);
        }
    )
    if (journey.value.length === 0) {
        router.push({ name: "plan-map", params: { planIdx: planIdx }  }) // 등록 
    } else {
        router.push({ name: "plan-saved-map", params: { planIdx: planIdx } }); // 수정 
    }
}

// 여행 일정 orderDate, order 순서 정렬 
const sortedJourney = computed(() => {
    // Sort the journey array based on orderDate and order
    const sortedByDate = journey.value.sort((a, b) => {
        if (a.orderDate !== b.orderDate) {
            return a.orderDate - b.orderDate;
        }
        return a.order - b.order;
    });

    // Group the sorted array by orderDate
    const groupedByDate = sortedByDate.reduce((acc, place) => {
        const date = place.orderDate;
        if (!acc[date]) {
            acc[date] = [];
        }
        acc[date].push(place);
        return acc;
    }, {});

    // Create an array with orderDate and places
    const result = Object.keys(groupedByDate).map((date) => ({
        orderDate: date,
        places: groupedByDate[date],
    }));

    return result;
});


// 친구 보였다가, 안 보였다하는 상태
const showFriend = ref(false);
const showMyFriend = (planIdx) => {
    showFriend.value = !showFriend.value
    if (showFriend.value) {
        requestFriends(planIdx)
    }
}

const friends = ref([])
const participantsInfo = ref([])
const remainFriends = ref([])
const requestFriends = (planIdx) => {
   // API 호출
   followList(store.userInfo.userId,
      ({ data }) => {  
          friends.value = data;
          // 그 여행을 같이 가는 친구와 비교해서 여행을 같이 안 가는 친구만 친구 초대 리스트에 뜨도록 computed 만들기
          getParticipant(
              planIdx,
              ({ data }) => {
                  participantsInfo.value = data
                  console.log(participantsInfo.value);
                },
                (error) => {
                    console.log(error);
                }
          )

          
    },
    (error) => {
        console.log(error);
    });
};

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
            <div class="col-sm-6" v-for='myPlan in myPlans' :key='myPlan.planIdx'>
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
                        <button class="btn btn-primary me-2" 
                        @click="checkPlan(myPlan.planIdx)">여행계획짜기</button>
                      
                        <button class="btn btn-warning me-2" 
                        @click='planDetail(myPlan.planIdx)'
                        >여행일정</button>
                        <button @click='showMyFriend(myPlan.planIdx)' class="btn btn-success">친구 초대하기</button>
                        <!-- 친구 초대하기 버튼을 클릭하면 친구가 뜬다 -->
                        <!-- -->
                        <div v-if='showFriend' >
                            <div>
                                <div class='margin-small-friend'></div>
                                <template v-for="friend in friends" :key="friend">
                                    <!-- <template v-if=""></template> -->
                                    {{ friend.userName }} ({{friend.userId}})
                                    <button @click="notificationRequest(friend.userId, myPlan.title)" 
                                    class="btn btn-outline-success">여행갈래?</button>
                                    <div class='margin-small'></div>
                                </template>
                            </div>
                        </div>
                        <!-- 여행 일정 start --><!-- 여행 일정 버튼을 클릭하면 show -->
                        <div v-if='showMyJourney[myPlan.planIdx]'>
                            <div class='margin-big'></div>
                            <div>
                                <h3>여행 일정</h3>
                                <template v-for="day in sortedJourney">
                                    <h4>{{ `Day ${day.orderDate}` }}</h4>
                                    <div v-for="place in day.places" :key="place.contentId">
                                        <router-link 
                                            :to="{ name: 'attraction-view', params: { attractionIdx: place.contentId } }" 
                                            class="place-title">
                                                <h5>{{ place.title }}</h5>
                                        </router-link>
                                        <!-- Display other travel info (imgUrl, address, etc.) as needed -->
                                        <!-- <img :src="place.imgUrl" alt="Place Image" /> -->
                                        <p>{{ place.address }}</p>
                                    </div>
                                </template>
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

            <!-- part1 바탕으로 Part2 해보기 start -->
        <div class="margin-big"></div>
        <h1>내가 초대된 여행</h1>
        <div v-if='invitations.length > 0'>
            <div class='row'>
            <div class="col-sm-6" v-for='invitation in invitations' :key='invitation.planIdx'>
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">{{ invitation.title }}</h5>
                        <p class="card-text">{{invitation.userId}}이 만든 여행이에요. <br>
                        {{ formatDate(invitation.startDate) }}부터 {{ formatDate(invitation.endDate) }}까지 
                        ({{calculateDays(invitation.startDate, invitation.endDate)}}일)</p>
        
                        <!-- 먼저 짜놓은 계획이 있는지 확인 -->
                        <button class="btn btn-primary me-2" 
                        @click="checkPlan(invitation.planIdx)">여행계획짜기</button>
                      
                        <button class="btn btn-warning me-2" 
                        @click='yourPlanDetail(invitation.planIdx)'
                        >여행일정</button>
                       
                        <!-- 여행 일정 start --><!-- 여행 일정 버튼을 클릭하면 show -->
                        <div v-if='showYourJourney[invitation.planIdx]'>
                            <div class='margin-big'></div>
                            <div>
                                <h3>여행 일정</h3>
                                <template v-for="day in sortedJourney">
                                    <h4>{{ `Day ${day.orderDate}` }}</h4>
                                    <div v-for="place in day.places" :key="place.contentId">
                                        <router-link 
                                            :to="{ name: 'attraction-view', params: { attractionIdx: place.contentId } }" 
                                            class="place-title">
                                                <h5>{{ place.title }}</h5>
                                        </router-link>
                                        <!-- Display other travel info (imgUrl, address, etc.) as needed -->
                                        <!-- <img :src="place.imgUrl" alt="Place Image" /> -->
                                        <p>{{ place.address }}</p>
                                    </div>
                                </template>
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
         
            <!-- part1 바탕으로 Part2 해보기 End-->

         
    </div>
    </div>
    
    <div class="col-2"></div>
    <div class="margin-big"></div>
</template>

<style scoped>
.place-title {
    text-decoration: none; 
  color: #333; 
  transition: color 0.3s ease; 
}
.place-title:hover {
  color: #8128d5; 
  font-weight: 900;
}
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