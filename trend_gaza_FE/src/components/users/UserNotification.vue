<script setup>
import { ref, onMounted } from 'vue'
import { useUserStore } from '@/stores/user'
import { listNoti, deleteNoti } from "@/api/notification";
import router from '../../router';

// 사용자 정보 필요 
const store = useUserStore()

// 알림 전체 조회 
onMounted(() => {
    getNotifications();
});
const notifications = ref([])
const getNotifications = () => {
   // API 호출
   listNoti(store.userInfo.userId,
      ({ data }) => {  
        notifications.value = data;
        console.log(notifications.value)
    },
    (error) => {
        console.log(error);
    });
};
// 수락 버튼 누르면 여행 계획 페이지로 이동 (추후 수정/보완)
const accept = () => {
    alert("이제 여행 계획을 짜러 가볼까요?")
    router.push("plan")
}


// 거절 버튼 누르면 알림 삭제
const deleteRequest = (userIdFrom) => {
    const confirmed = window.confirm("정말 거절하실꺼에요?ㅠㅠ");
    if (!confirmed) {
        return;
    }
    // API 호출
    deleteNoti(
        store.userInfo.userId,
        userIdFrom,
        (response) => {  
            let msg = "거절되었습니다! 친구에게 전달되지는 않아요!^^"
            alert(msg)
            getNotifications()
        },
    (error) => {
        console.log(error);
    });
};
</script>

<template>
    <div class="container"></div>
    <div class="container text-center"> 
    <div class="row">
        <div class="col"></div>
        <div class="col">

        <div>
        <h1 id="header">알림이 왔어요!</h1>
        <img 
            src="@/assets/icon/bell.png" 
            alt="알림"
            style="height: 45px; width: 45px;"
         />
    </div>
    <div>
        <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                {{store.userInfo.userName}}님에게 알림이 왔어요!
                <span class="badge bg-primary rounded-pill">{{notifications.length}}</span>
            </li>
            <div v-for="notification in notifications" :key="notification.notificationIdx">
                <li 
                class="list-group-item d-flex align-items-center">
                    {{notification.userIdFrom}}이 "{{notification.message}}"라고 요청을 보내왔어요!
                    <div class="button-class">
                        <button type="button" class="btn btn-outline-info mt-1 mb-3 me-2" 
                        @click="accept" >수락</button>
                        <button type="button" class="btn btn-outline-danger mt-1 mb-3"
                        @click="deleteRequest(notification.userIdFrom)">거절</button>
                    </div>
                </li>
            </div>
        </ul>
    </div>

        </div>
        <div class="col"></div>
    </div>
    </div>

    
</template>

<style scoped>
#header {
    margin-top: 50px;
    display: inline;
}

.button-class {
    display: block;
}
</style>