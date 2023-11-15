<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/user'
const store = useUserStore()
const editMode = ref(false);

const toggleEditMode = () => {
  editMode.value = !editMode.value;
};

</script>

<template>
    <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div v-if="!editMode" class="card mt-3 m-auto" style="max-width: 700px">
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
            <button type="button" class="btn btn-outline-secondary mt-2" @click="toggleEditMode" >수정</button>
          </div>
        </div>
        <div v-if="editMode">
          <form>
            form 을 입력해야 합니다. 

            <button @click="saveChanges" type="button" class="btn btn-outline-primary mt-2">수정 완료</button>
            <button type="reset" class="btn btn-outline-secondary mt-2">초기화</button>
            <button type="button" class="btn btn-outline-danger mt-2" @click="toggleEditMode" >취소</button>
          </form>
        </div>
        
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>