<script setup>

</script>

<template>
    <div class="container text-center">
<div class="row">
<div class="col-1"></div>
<div class="col-10">
    <div class="category">
    <div class="category-card">
        <img src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/museum.png" width="40" height="40" alt="">
        <router-link to="/category/culture">문화시설</router-link>
        <!-- <option value="14" @click="moveToCulture">문화시설</option> -->
    </div>
    <div class="category-card">
        <img src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/concert.png" width="40" height="40" alt="">
        <option value="15">축제공연행사</option>
    </div>
    <div class="category-card">
        <img src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/skiing.png" width="40" height="40" alt="">
        <option value="28">레포츠</option>
    </div>
    <div class="category-card">
        <img src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/hotel.png" width="40" height="40" alt="">
        <option value="32">숙박</option>
    </div>
    <div class="category-card">
        <img src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/shopping-cart.png" width="40" height="40" alt="">
        <option value="38">쇼핑</option>
    </div>
    <div class="category-card">
        <img src="https://instagramimages16.s3.ap-northeast-2.amazonaws.com/IMAGE/admin/restaurant.png" width="40" height="40" alt="">
        <option value="39">음식점</option>
    </div>
    </div>
</div>
<div class="col-1"></div>
</div>
</div>
</template>

<style scoped>
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