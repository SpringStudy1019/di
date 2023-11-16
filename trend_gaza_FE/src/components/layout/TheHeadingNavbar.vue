<script setup>
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();

const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const logout = () => {
  console.log("로그아웃!!!!");
  changeMenuState();
};

</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <router-link :to="{ name: 'main' }" class="navbar-brand"> 
      Trend Gaza
    </router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!-- 왼쪽 부분 -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      
        <li class="nav-item">
          <router-link :to="{name: 'attraction'}" class="nav-link">관광지 살펴보기</router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{name: 'review'}" class="nav-link">여행후기</router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{name: 'review'}" class="nav-link">플랜생성</router-link>
        </li>
      </ul>
      <!-- 오른쪽 부분 -->
      <!--
      <li class="d-flex mx-1">
        <img src="src/components/img/bell.png" width="20" height="20" class="d-inline-block align-top" alt="">
      </li>
      <li class="d-flex mx-3">
        <img src="src/components/img/bell2.png" width="20" height="20" class="d-inline-block align-top" alt="">
      </li>
      -->
      <ul
          class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll"
          style="--bs-scroll-height: 100px"
        >
      <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link to="/" @click.prevent="logout" class="nav-link">{{
                    menu.name
                  }}</router-link>
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                    menu.name
                  }}</router-link>
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
  font-family: 'Roboto', sans-serif; 
}
</style>