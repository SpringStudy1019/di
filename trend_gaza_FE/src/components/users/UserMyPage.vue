<script setup>
import { ref, onMounted, computed } from 'vue'
import { useUserStore } from '@/stores/user'
import { useRouter } from "vue-router";
import { modifyUser, deleteUser } from "@/api/user";
import { followList, offFollow } from "@/api/follow";
import { registNoti } from "@/api/notification";

// heading navbar 메뉴 
import { useMenuStore } from "@/stores/menu";
const menuStore = useMenuStore();
const { changeMenuState } = menuStore;

const store = useUserStore()
const editMode = ref(false);
const router = useRouter();
const deleteId = ref('');

const toggleEditMode = () => {
  editMode.value = !editMode.value;
  // Set editInfo to a copy of userInfo when entering edit mode
  if (editMode.value) {
    editInfo.value = { ...store.userInfo };
  }
};

const editInfo = ref({
  "emailDomain": "",
  "emailId": "",
  "gender": "",
  "mobile": "",
  "userId": "",
  "userName": ""
})

const saveChanges = () => {
  modifyUser(editInfo.value,
    (response) => {
      let msg = "정보 수정이 완료되었습니다!"
      alert(msg);
      toggleEditMode();
      store.userInfo = {...editInfo.value};
      router.push({ name: "user-mypage" });
    },
    (error) => console.log(error)
  )
};

const resetForm = () => {
  editInfo.value = { ...store.userInfo };
};

// 팔로잉 목록
const followees = ref([])
onMounted(() => {
    getFollowers();
});

const getFollowers = () => {
   // API 호출
   followList(store.userInfo.userId,
      ({ data }) => {  
        followees.value = data;
    },
    (error) => {
        console.log(error);
    });
};

// 팔로우 취소
const deleteFollow = (followee) => {
    offFollow(
      store.userInfo.userId,
      followee,
    (response) => {
      let msg = "팔로우가 취소되었습니다!"
      alert(msg);
      getFollowers();
      router.push({ name: "user-mypage" });
    },
    (error) => console.log(error)
  )
};

// 회원탈퇴 요청
const deleteRequest = () => {
   // API 호출
   deleteUser(store.userInfo.userId,
      (response) => {  
        let msg = "회원탈퇴가 완료되었습니다!"
        alert(msg);
        changeMenuState();
        router.push({ name: "main" });
    },
    (error) => {
        console.log(error);
    });
};

// 여행 갈래 요청 (알림 발송)
const notiInfo = ref({
  "message": "나랑 여행 갈래?",
  "pathId": "",
  "pushCase": "PERSONAL",
  "pushDate": "",
  "pushStatus": "INCOMPLETE",
  "userIdFrom": store.userInfo.userId,
  "userIdTo": ""
})
const notificationRequest = (followee) => {
  notiInfo.value.userIdTo = followee
   // API 호출
   registNoti(notiInfo.value,
      (response) => {  
        let msg = "알림이 발송되었습니다! 친구의 답변을 조금만 기다려주세요!"
        alert(msg);
    },
    (error) => {
        console.log(error);
    });
};

</script>

<template>
    <div class="container">
    <div class="row justify-content-center" v-if="!editMode">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내 정보 조회</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div  class="card mt-3 m-auto" style="max-width: 700px">
          
          <div class="row g-0">

            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded-start"
                alt="..."
              />
            </div>

            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">이름: {{ store.userInfo.userName }}</li>
                  <li class="list-group-item">아이디: {{ store.userInfo.userId }}</li>
                  <li class="list-group-item">핸드폰번호: {{ store.userInfo.mobile }}</li>
                  <li class="list-group-item">이메일: {{ store.userInfo.emailId }}@{{ store.userInfo.emailDomain }}</li>
                  <li class="list-group-item">성별: {{ store.userInfo.gender }}</li>
                  <li class="list-group-item">가입일자: {{ store.userInfo.joinDate }}</li>
                </ul>
              </div>
            </div>

          </div>

          <div>
            <button type="button" class="btn btn-outline-info mt-1 mb-3 me-2" @click="toggleEditMode" >수정</button>
            <button @click="deleteRequest" type="button" class="btn btn-outline-secondary mt-1 mb-3">회원탈퇴</button>
          </div>
        </div>
        
        <div class='following'>
          <h3 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="orange">팔로잉</mark>
          </h3>
          <div class="row" >
            <div class="col-sm-6 my-3" v-for="followee in followees" :key="followee">
              <div class="card">
                <div class="card-body">
                  <img
                    src="https://source.unsplash.com/random/250x250/?food"
                    class="img-fluid rounded-start"
                    alt="..."  style="height: 100px; width: 100px;"
                  />
                  <h5 class="card-title" >{{ followee }}</h5>
                  <div>
                    <button @click="notificationRequest(followee)" class="btn btn-warning me-3">여행갈래?</button>
                    <button class="btn btn-dark"  @click="deleteFollow(followee)">팔로우 취소</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row justify-content-center" v-if="editMode">
    <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내 정보 수정</mark>
        </h2>
      </div>
    <div>
      <!-- -->
 <div  class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded-start"
                alt="..."
              />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">
                      <label for="editName">이름:</label>
                      <input id="editName" type="text" v-model="editInfo.userName" />
                    </li>
                    <li class="list-group-item">
                      <label for="editUserId">아이디:</label>
                      <input id="editUserId" type="text" v-model="editInfo.userId" disabled />
                    </li>
                    <li class="list-group-item">
                      <label for="editUserId">핸드폰번호:</label>
                      <input id="editUserId" type="text" v-model="editInfo.mobile" />
                    </li>
                    <li class="list-group-item">
                      <label for="editUserEmailId">이메일:</label>
                      <input id="editUserEmailId" type="text" v-model="editInfo.emailId" />
                      @
                      <input id="editUserEmailDomain" type="text" v-model="editInfo.emailDomain" />
                    </li>
                    <div class="mb-3">
                      <label for="gender" class="form-label">성별 : </label>
                      <select class="form-select" aria-label="성별 선택" name="gender" v-model="editInfo.gender">
                          <option selected value="">선택</option>
                          <option value="MALE" >남성</option>
                          <option value="FEMALE">여성</option>
                          <option value="UNDEFINED">밝히지 않음</option>
                        </select>
                    </div>
                </ul>
              </div>
            </div>
            <div class="mt-1 mb-3">
              <button @click="saveChanges" type="button" class="btn btn-outline-info me-2">수정 완료</button>
              <button @click="resetForm" type="reset" class="btn btn-outline-secondary me-2">초기화</button>
              <button @click="toggleEditMode" type="button" class="btn btn-outline-danger">취소</button>
            </div>
          </div>
      <!-- -->
      
    </div>
  </div>
  </div>
</div>
</template>

<style scoped>

</style>