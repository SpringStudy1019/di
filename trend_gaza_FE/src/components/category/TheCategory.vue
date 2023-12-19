<script setup>

</script>

<template>
    <div class="container text-center">
<div class="row">
<div class="col-1"></div>
<div class="col-10">
    <div class="category">
    <div class="category-card">
        <img src="http://d2qe2q8v1imxmh.cloudfront.net/admin/museum.png" width="40" height="40" alt="">
        <router-link :to="{ name: 'category-view', params: { contentTypeId: 14 } }"  style="text-decoration: none">
            <span class="icon-text">문화시설</span>
        </router-link>
    </div>
    <div class="category-card">
        <img src="http://d2qe2q8v1imxmh.cloudfront.net/admin/concert.png" width="40" height="40" alt="">
        <router-link :to="{ name: 'category-view', params: { contentTypeId: 15 } }"  style="text-decoration: none">
            <span class="icon-text">축제공연행사</span>
        </router-link>
    </div>
    <div class="category-card">
        <img src="http://d2qe2q8v1imxmh.cloudfront.net/admin/skiing.png" width="40" height="40" alt="">
        <router-link :to="{ name: 'category-view', params: { contentTypeId: 28 } }"  style="text-decoration: none">
            <span class="icon-text">레포츠</span>
        </router-link>
    </div>
    <div class="category-card">
        <img src="http://d2qe2q8v1imxmh.cloudfront.net/admin/hotel.png" width="40" height="40" alt="">
        <router-link :to="{ name: 'category-view', params: { contentTypeId: 32 } }"  style="text-decoration: none">
            <span class="icon-text">숙박</span>
        </router-link>
    </div>
    <div class="category-card">
        <img src="http://d2qe2q8v1imxmh.cloudfront.net/admin/shopping-cart.png" width="40" height="40" alt="">
        <router-link :to="{ name: 'category-view', params: { contentTypeId: 38 } }"  style="text-decoration: none">
            <span class="icon-text">쇼핑</span>
        </router-link>
    </div>
    <div class="category-card">
        <img src="http://d2qe2q8v1imxmh.cloudfront.net/admin/restaurant.png" width="40" height="40" alt="">
        <router-link :to="{ name: 'category-view', params: { contentTypeId: 39 } }"  style="text-decoration: none">
            <span class="icon-text">음식점</span>
        </router-link>
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

.icon-text {
    margin-top: 10px;
    font-family: 'Your-Font-Here', sans-serif; /* Use a preferred font */
    font-size: 20px; 
    color: #333; 
    text-decoration: none  !important;; /* Remove underline */
    display: block;
}

.icon-text:hover {
    color: #2B3499; 
    font-weight: bold;
}
</style>