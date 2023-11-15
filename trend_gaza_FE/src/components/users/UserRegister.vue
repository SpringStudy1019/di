<script setup>
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { registUser, idCheck } from "@/api/user";

const router = useRouter();
const isAvailable = ref(false);
const user = ref({
  "emailDomain": "",
  "emailId": "",
  "gender": "",
  "mobile": "",
  "password": "",
  "userId": "",
  "userName": ""
})
const pwdcheck = ref("");

function registerUser() {
  console.log("Form Data:", user.value);
  if (!isAvailable.value) {
      let msg = "아이디를 확인하세요!"
      alert(msg);
      return;
  } 
  registUser(user.value,
    (response) => {
      let msg = "회원가입이 완료되었습니다!"
      alert(msg);
      router.push({ name: "main" });
    },
    (error) => console.log(error)
  )
}


// 아이디 길이체크, 중복체크 로직 

function checkId() {
  const userId = user.value.userId;
  if (userId.length < 6 || userId.length > 16) {
    isAvailable.value = false;
  } else {
    idCheck(userId,
      (response) => {
        // console.log(response);
        if (response.status === 200) {
          // Assuming 200 status means the ID is available
          isAvailable.value = true;
        } else {
          isAvailable.value = false;
        }
      },
      (error) => console.log(error)
    );
  }
}
watch(() => user.value.userId, checkId);

</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <form>
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input type="text" class="form-control" placeholder="이름..." name="userName" v-model="user.userName"/>
          </div>
          <div class="mb-3">
            <label for="userId" class="form-label">아이디 : </label>
            <input type="text" class="form-control" placeholder="아이디..." name="userId" v-model="user.userId"/>
          </div>
          <!-- id checking -->
          <div id="idcheck-result" v-if="user.userId.length>0">
            <span v-if="isAvailable" class="text-primary">{{ user.userId }}는 사용할 수 있습니다.</span>
            <span v-else class="text-danger">{{ user.userId }}는 사용할 수 없습니다.</span>
          </div>
          <div class="mb-3">
            <label for="userPwd" class="form-label">비밀번호 : </label>
            <input type="text" class="form-control" placeholder="비밀번호..." name="userPwd" v-model="user.password"/>
          </div>
          <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" id="pwdcheck" placeholder="비밀번호확인..." v-model="pwdcheck"/>
          </div>
          <div class="mb-3">
            <label for="userPwd" class="form-label">핸드폰 번호 : </label>
            <p>숫자만 입력하세요.</p>
            <input type="text" class="form-control" placeholder="핸드폰번호..." name="mobile" v-model="user.mobile" />
          </div>
          <div class="mb-3">
            <label for="emailId" class="form-label" name="emailId" >이메일 : </label>
            <div class="input-group">
              <input type="text" class="form-control" placeholder="이메일아이디" v-model="user.emailId" />
              <span class="input-group-text">@</span>
              <select class="form-select" aria-label="이메일 도메인 선택" name="emailDomain" v-model="user.emailDomain">
                <option selected value="">선택</option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
              </select>
            </div>
          </div>
          <div class="mb-3">
            <label for="gender" class="form-label">성별 : </label>
            <select class="form-select" aria-label="성별 선택" name="gender" v-model="user.gender">
                <option selected value="">선택</option>
                <option value="MALE">남성</option>
                <option value="FEMALE">여성</option>
                <option value="UNDEFINED">밝히지 않음</option>
              </select>
          </div>
          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-primary mb-3" @click='registerUser'>회원가입</button>
            <button type="reset" class="btn btn-outline-success ms-1 mb-3">초기화</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>