<script setup>
import { ref, onMounted, computed } from "vue";
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";
import { listNoti } from "@/api/notification";

// heading navbar 메뉴
const menuStore = useMenuStore();
const { menuList } = storeToRefs(menuStore);
//const { changeMenuState } = menuStore;
const { loginState } = menuStore;
const { logoutState } = menuStore;

// user 정보
const userStore = useUserStore();
const { userLogout, isLogin, userInfo } = userStore;

const isLoginUser = ref(true);

const logout = () => {
  console.log("로그아웃 시작!!");
  if (userStore.userInfo === null) {
    logoutState();
    return;
  }
  userLogout(userStore.userInfo.userId);
  //changeMenuState();

  sessionStorage.removeItem("access-token");
  sessionStorage.removeItem("refresh-token");

  logoutState();

  //isLoginUser.value = false;

  console.log("로그아웃 종료!!");
};

onMounted(() => {
  const userStore = sessionStorage.getItem("userStore");
  var userStoreObject = JSON.parse(userStore);

  // "isLogin" 속성의 값 가져오기
  var isLoginValue = userStoreObject.isLogin;

  // 결과 출력
  console.log(isLoginValue);

  if (isLoginUser) {
    loginState();
  }
});
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <router-link :to="{ name: 'main' }" class="navbar-brand"> Trend Gaza </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- 왼쪽 부분 -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link :to="{ name: 'review' }" class="nav-link">여행후기</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'plan-write' }" class="nav-link">플랜생성</router-link>
          </li>
        </ul>
        <!-- 오른쪽 부분 -->
        <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
          <!-- v-for 안에 v-if를 쓰고 싶으면, template을 2번 쓰면 된다! -->
          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <!--<template v-if="isLoginUserFunc === true && menu.show === false">-->
              <!--<template v-if="menu.routeName === 'user-logout'">-->
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link to="/" @click.prevent="logout" class="nav-link">{{
                    로그아웃
                  }}</router-link>
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <!-- <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                    menu.name
                  }}</router-link> -->
                  <!--<template v-if="isLoginUserFunc === true">-->
                  <template v-if="menu.name === '로그아웃'">
                    <router-link :to="{ name: menu.routeName }" class="nav-link" @click="logout">{{
                      menu.name
                    }}</router-link>
                  </template>
                  <template v-else>
                    <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                      menu.name
                    }}</router-link>
                  </template>
                </li>
              </template>
            </template>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar-brand {
  font-weight: bold;
  font-family: "Roboto", sans-serif;
}
</style>
