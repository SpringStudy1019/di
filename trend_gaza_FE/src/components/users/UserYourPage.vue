<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { getUser } from '@/api/user';

onMounted(() => {
  getUserInfo();
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

// 이름
// 팔로워 수 (팔로우 버튼)
// 메세지 보내기?
// 리뷰 작성 수 & 이 사람이 올린 리뷰 (사진과 같이 카드 형식)
</script>

<template>
    <div>{{userInfo.userName}}</div>
    <div>이미지</div>
    <div>00명이 팔로잉 중</div>
    <div></div>
</template>

<style scoped>

</style>