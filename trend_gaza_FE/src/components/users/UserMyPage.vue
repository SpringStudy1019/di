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
            <button type="button" class="btn btn-outline-secondary mt-1 mb-3" @click="toggleEditMode" >수정</button>
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
                      <input id="editName" type="text" v-model="store.userInfo.userName" />
                    </li>
                    <li class="list-group-item">
                      <label for="editUserId">아이디:</label>
                      <input id="editUserId" type="text" v-model="store.userInfo.userId" disabled />
                    </li>
                    <li class="list-group-item">
                      <label for="editUserId">핸드폰번호:</label>
                      <input id="editUserId" type="text" v-model="store.userInfo.mobile" />
                    </li>
                    <li class="list-group-item">
                      <label for="editUserEmailId">이메일:</label>
                      <input id="editUserEmailId" type="text" v-model="store.userInfo.emailId" />
                      @
                      <!-- <label for="editUserId">이메</label> -->
                      <input id="editUserEmailDomain" type="text" v-model="store.userInfo.emailDomain" />
                    </li>
                    <div class="mb-3">
                      <label for="gender" class="form-label">성별 : </label>
                      <select class="form-select" aria-label="성별 선택" name="gender" v-model="store.userInfo.gender">
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
              <button @click="saveChanges" type="button" class="btn btn-outline-primary me-2">수정 완료</button>
              <button type="reset" class="btn btn-outline-secondary me-2">초기화</button>
              <button type="button" class="btn btn-outline-danger" @click="toggleEditMode" >취소</button>
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