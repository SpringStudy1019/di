<script setup>
import { useRouter } from 'vue-router';
import TheCategory from '../components/category/TheCategory.vue';

const router = useRouter();

</script>

<template>
        <!-- 검색 -->
        <div class='search'>
        <div class="container text-center">
            <div class="row">
              <div class="col-3"></div>
              <div class="col-6">
                <form class="d-flex">
                  <div class="input-group input-group-sm">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="가고 싶은 여행지를 입력하세요"
                    />
                    <button class="btn btn-dark" type="button">검색</button>
                  </div>
                </form>
              </div>
              <div class="col-3"></div>
            </div>
          </div>
        </div>
        <!-- 카테고리 -->
        <TheCategory />
        
        <!-- 추천 여행지 -->
        


</template>

<style scoped>
.search {
  margin-top: 20px;
  margin-bottom: 20px;
}

</style>