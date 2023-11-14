<script setup>
import { useRouter } from 'vue-router';
import TheCategory from '../components/category/TheCategory.vue';

const router = useRouter();

</script>

<template>
    <router-view></router-view>
        <!-- <h1>메인 페이지입니다</h1> -->
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
        <h2>계절별 여행지</h2>


</template>

<style scoped>
.search {
  margin-top: 20px;
  margin-bottom: 20px;
}
.category {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-bottom: 20px;
}

.category-card {
    margin: 0 auto;
  flex-basis: calc(33.33% - 10px); /* Adjust the width and margin based on your design */
  margin-top: 20px;
  
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
  text-align: center;
}
</style>