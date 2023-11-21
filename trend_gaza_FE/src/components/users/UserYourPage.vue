<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getUser } from '@/api/user';
import { getReviewsByUserId } from '@/api/review';
import { countFollowers, listRelated } from '@/api/follow';

onMounted(() => {
  getUserInfo();
  getcountFollowers();
  getReviews();
});

const route = useRoute();
const { userId } = route.params;

const userInfo = ref({})
const followers = ref([])

const getUserInfo = () => {
    getUser(
        userId,
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
    userId,
    ({ data }) => { 
      reviews.value = data;
      console.log(reviews.value);
            },
        (error) => {
          console.log(error);
        }
  )
}


// 팔로워 수 (팔로우 버튼)
const count = ref(0)
const getcountFollowers = () => {
  countFollowers(
    userId,
    ({ data }) => { 
            count.value = data;
            },
        (error) => {
          console.log(error);
        }
  )
}




// 팔로잉 button 효과
function toggleFollow() {
  const followButton = document.getElementById('follow-button');
  // 가장 먼저 follow 되어 있는지 확인, follow 되어 있으면, following이라는 버튼이 떠야하고,
  // 그렇지 않으면, +follow가 떠야 함.

  // if (followButton.textContent === '+ Follow') {
  //   // State Change: To Following
  //   followButton.textContent = 'Following';
  //   followButton.style.width = '95px';
  //   followButton.style.backgroundColor = '#3399FF';
  //   followButton.style.color = '#ffffff';
  //   followButton.style.borderColor = '#3399FF';
  //   // API Call
  //   onFollow(followingInfo.value,
  //   (response) => {
  //     router.push({ name: "review-view" });
  //   }, (error) => {
  //     console.log(error);
  //   })
  // } else {
  //   // State Change: Unfollow
  //   followButton.textContent = '+ Follow';
  //   followButton.style.width = '85px';
  //   followButton.style.backgroundColor = '#ffffff';
  //   followButton.style.color = '#3399FF';
  //   followButton.style.borderColor = '#3399FF';
  //   // API Call
  //   offFollow(
  //     followingInfo.value.followeeId,
  //     followingInfo.value.followerId,
  //   (response) => {
  //     router.push({ name: "review-view" });
  //   }, (error) => {
  //     console.log(error);
  //   })
  // }
}

// following 버튼을 누르는 순간, 
// 연관 사용자가 떠야 함. 
// 즉 following 한 status라면, 연관 사용자가 떠야 하고, 
// 그렇지 않으면 뜨면 안 됨.

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
        <!-- Add other user information as needed -->
      </div>
      <button id="follow-button">+ Follow</button>
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
</style>