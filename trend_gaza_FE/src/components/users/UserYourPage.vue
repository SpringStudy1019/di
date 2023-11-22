<script setup>
import { ref, onMounted, watch } from "vue";
import { useRoute, onBeforeRouteUpdate } from "vue-router";
import { useRouter } from "vue-router";
import { getUser } from '@/api/user';
import { getReviewsByUserId } from '@/api/review';
import { countFollowers, listRelated, checkFollow, offFollow, onFollow } from '@/api/follow';
import { useUserStore } from '@/stores/user'

onMounted(() => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
  getUserInfo();
  getcountFollowers();
  getReviews();
  checkFollowing();
});

const route = useRoute();
const userId  = ref(route.params.userId);

const store = useUserStore()
const router = useRouter();

const userInfo = ref({})

const getUserInfo = () => {
    getUser(
        userId.value,
        ({ data }) => { 
            userInfo.value = data;
            },
        (error) => {
          console.log(error);
        }
    )
}

// 사용자가 작성한 리뷰
const reviews = ref([])
const getReviews = () => {
  getReviewsByUserId(
    userId.value,
    ({ data }) => { 
      reviews.value = data; },
        (error) => {
          console.log(error);
        }
  )
}

// 팔로워 수 (팔로우 버튼)
const count = ref(0)
const getcountFollowers = () => {
  countFollowers(
    userId.value,
    ({ data }) => { 
            count.value = data;
            },
        (error) => {
          console.log(error);
        }
  )
}


// following 버튼을 누르는 순간, 
// 연관 사용자가 떠야 함. 
// 즉 following 한 status라면, 연관 사용자가 떠야 하고, 
// 그렇지 않으면 뜨면 안 됨.
const otherUsersToggle = ref(false)
// follow 체크 
const followingStatus = ref(false)
const followInfo = ref({
  "followeeId": store.userInfo.userId,
  "followerId": userId.value
})
const checkFollowing = () => {
  checkFollow(
    followInfo.value.followerId,
    followInfo.value.followeeId,
    ({ data }) => { 
      if (data === 1) followingStatus.value = true;  // 팔로잉 중 
      toggleButton()
          },
        (error) => {
          console.log(error);
        }
  )
}

// 팔로잉 button 효과
function toggleButton() {
  const followButton = document.getElementById('follow-button');
  // 가장 먼저 follow 되어 있는지 확인, follow 되어 있으면, following이라는 버튼이 떠야하고,
  // 그렇지 않으면, +follow가 떠야 함.
  if (followingStatus.value) {
    followButton.textContent = 'Following';
    followButton.style.width = '95px';
    followButton.style.backgroundColor = '#8128d5';
    followButton.style.color = '#ffffff';
    followButton.style.borderColor = '#8128d5';
  } else {
    followButton.textContent = '+ Follow';
    followButton.style.width = '85px';
    followButton.style.backgroundColor = '#ffffff';
    followButton.style.color = '#8128d5';
    followButton.style.borderColor = '#8128d5';
  }
}

// 팔로우 등록 및 취소 toggleButton() 추가
const toggleFollowing = () => {
  if (followingStatus.value) { // 팔로잉 중이라면 follow 취소 
      offFollow(
        followInfo.value.followerId,
        followInfo.value.followeeId,
        ({ data }) => { 
          alert("팔로우가 취소되었습니다!")
          followingStatus.value = !followingStatus.value
          count.value = count.value-1
          toggleButton()
          otherUsersToggle.value = !otherUsersToggle.value
          },
        (error) => {
          console.log(error);
        }
      )
  } else {
    onFollow(
      followInfo.value,
      ({ data }) => { 
          alert("팔로우를 시작했습니다!")
          followingStatus.value = !followingStatus.value  // following status change
          count.value = count.value+1  // follower count up
          toggleButton()  // button toggle change
          otherUsersToggle.value = !otherUsersToggle.value
          getRelatedPeople()  // showing related people list 
          },
        (error) => {
          console.log(error);
        }
      )
  }
}

const relatedPeople = ref([])
const getRelatedPeople = () => {
  listRelated(
    store.userInfo.userId,
    userId.value,
    ({ data }) => { 
      relatedPeople.value = data
      console.log("related people:::", data);
          },
        (error) => {
          console.log(error);
        }
  )
}

const routeUpdate = function (relatedUserId) {
  router.push({ name: 'user-yourpage', params: { userId: relatedUserId } })
}

onBeforeRouteUpdate((to, from) => {
  userId.value = to.params.userId
  getUserInfo();
  getcountFollowers();
  getReviews();
  checkFollowing();
  getRelatedPeople();
  otherUsersToggle.value = !otherUsersToggle.value
})
</script>

<template>
  <div class='margin-large'></div>
  
    <div class="profile-container">
      <div class='margin-big'></div>
    <div class="profile-left">
      <div class="profile-image">
        <!-- Use the actual user image source here -->
        <img :src="userInfo.imgUrl"
        alt="Profile Image">
      </div>
    </div>
    <div class="profile-right">
      <div class="user-info">
        <div class="user-name">{{ userInfo.userName }}</div>
        <div class="user-id">{{ userInfo.userId }}</div>
        <div class="follow-info">{{ count }}명이 팔로잉 중</div>
        <div class='margin-small'></div>

        <!-- 로그인한 사용자 아닌 경우에만 팔로잉 버튼을 보여줘야 함 -->
        <div v-if='userInfo.userId !== store.userInfo.userId'>
          <button id="follow-button" @click='toggleFollowing'>+ Follow</button>
        </div>

        <!-- 팔로우 버튼을 누르면 관련 사용자 띄우기 -->
        <div v-if='otherUsersToggle && relatedPeople.length > 0' class="related-people-box">
          <div class='margin-very-small'></div>
          <h3>알 수도 있는 사람들</h3>
          <div v-for="relatedPerson in relatedPeople" :key="relatedPerson.userId" class="related-person">
            <div  class="user-info">
            <img :src="relatedPerson.imgUrl" alt="User Image" class="user-image" />
              <button class="fancy-button"
              @click="routeUpdate(relatedPerson.userId)">
              {{ relatedPerson.userName }} ({{ relatedPerson.userId }})
              </button>
            </div>
          </div>
        </div>

        <div class='margin-small'></div>
        <div>{{reviews.length}}개의 리뷰 작성</div>
      </div>
    </div>
    </div>
  <div class='margin-big'></div>

    <div class="row" >
      <div class='margin-big'></div>
      <div class="col-sm-6 col-md-4" v-for='review in reviews' :key='review.reviewIdx'>
          <div class="card">
            <img class="card-img-top" 
            :src="review.fileInfos && review.fileInfos.length > 0 ? 
            review.fileInfos[0].saveFile : 'https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/no_image.jpg'" 
            alt="Card image cap">
              <router-link 
                  :to="{ name: 'review-view', params: { reviewIdx: review.reviewIdx } }" 
                  class="user-link"  
                  >
                    <div class="card-header" id='title'>{{ review.title }}</div>
                </router-link>
              </div>
              <div class='margin-big'></div>
      </div>
    </div>
</template>

<style scoped>
.fancy-button {
  margin-left: 5px;
  padding: 4px 14px;
  border-radius: 20px;
  background-color: #8128d5;
  color: #ffffff;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.fancy-button:hover {
  background-color: #ffffff;
  color: #8128d5;
  outline-color: #8128d5;
  border-color: #8128d5;
}
.related-person {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}
.user-image {
  border-radius: 50%;
  overflow: hidden;
  width: 30px; 
  height: 30px; 
  margin: 0 auto;
}
.related-people-box {
  border: 1px solid #ccc;
  padding: 10px;
  margin-top: 20px;
}

#related-person {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.profile-container {
  display: flex;
}

.profile-left {
  flex: 1;
  text-align: center;
}

.profile-image {
  border-radius: 50%;
  overflow: hidden;
  width: 300px; 
  height: 300px; 
  margin: 0 auto;
}

.profile-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-right {
  flex: 1;
  padding: 60px 30px;
}

.user-info {
  text-align: left;
}

.user-name {
  font-size: 24px;
  font-weight: bold;
}

.user-id {
  font-size: 18px;
  color: #777;
}

.follow-info {
  font-size: 16px;
  color: #555;
}
.user-link {
  font-weight: bold;
  font-size: 20px;
  text-decoration: none; 
  color: #333; 
  transition: color 0.3s ease; 
}
.user-link:hover {
  color: #83A2FF; 
}
.margin-big {
  margin-bottom: 50px;
}

.margin-large {
  margin-bottom: 60px;
}

.margin-small {
  margin-bottom: 20px;
}

.margin-very-small {
  margin-bottom: 10px;
}

/* 팔로잉 버튼 */
#follow-button {
  display: inline-block;
  color: #8128d5;
  font-family: "Helvetica";
  font-size: 12pt;
  font-weight: bold;
  background-color: #ffffff;
  border: 1px solid;
  border-color: #3399FF;
  border-radius: 3px;
  width: 85px;
  height: 30px;
  top: 50px;
  left: 50px;	
  cursor: pointer;		
  transition: all 0.3s ease;
}

.user-link {
  text-decoration: none; 
  color: #333; 
  transition: color 0.3s ease; 
}
.user-link:hover {
  color: #83A2FF; 
}
</style>