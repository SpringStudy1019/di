<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getUser } from '@/api/user';
// import { getUser } from '@/api/review';
import { countFollowers, listRelated } from '@/api/follow';

onMounted(() => {
  getUserInfo();
  getcountFollowers();
});

const route = useRoute();
const { userId } = route.params;

const userInfo = ref({})
const followers = ref([])
const reviews = ref([])

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

// 리뷰 작성 수 & 이 사람이 올린 리뷰 (사진과 같이 카드 형식)



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
  
    <div>{{userInfo.userName}}</div>
    <div>이미지</div>
    <div>{{count}}명이 팔로잉 중</div>
    <div></div>
    <button id="follow-button">+ Follow</button>	
</template>

<style scoped>

</style>