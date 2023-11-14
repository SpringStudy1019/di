<script setup>

</script>

<template>
    
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
        <div class="container text-center">
          <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
              <div class="category">
                <div class="category-card">
                  <img src="@/assets/category/museum.png" width="40" height="40" alt="">
              
                  <option value="14">문화시설</option>
                </div>
                <div class="category-card">
                  <img src="@/assets/category/concert.png" width="40" height="40" alt="">
                  <option value="15">축제공연행사</option>
                </div>
                <!-- <div class="category-card">
                  <img src="@/assets/category/destination.png" width="40" height="40" alt="">
                  <option value="25">여행코스</option>
                </div> -->
                <div class="category-card">
                  <img src="@/assets/category/skiing.png" width="40" height="40" alt="">
                  <option value="28">레포츠</option>
                </div>
                <div class="category-card">
                  <img src="@/assets/category/hotel.png" width="40" height="40" alt="">
                  <option value="32">숙박</option>
                </div>
                <div class="category-card">
                  <img src="@/assets/category/shopping-cart.png" width="40" height="40" alt="">
                  <option value="38">쇼핑</option>
                </div>
                <div class="category-card">
                  <img src="@/assets/category/restaurant.png" width="40" height="40" alt="">
                  <option value="39">음식점</option>
                </div>
              </div>
            </div>
            <div class="col-1"></div>
          </div>
          </div>

        
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