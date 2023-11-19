<script setup>
import {defineProps} from "vue";

const props = defineProps({
   attraction: Object
});
</script>

<template>
    <div class="container">
        <span class="title">{{attraction.title}}</span>
        <div class="img-content">
            <img class="img" :src="attraction.firstImage"/>
        </div>
        <span>{{ attraction.addr1 }} </span>
        <div class="add-btn">
            <button>추가</button>
        </div>
    </div>
</template>

<style scoped>
.container {
    border: 1px solid black;
    margin: 10px;
    width: 300px;
    height: 300px;
    border-radius: 8px;
}

.img {
    width: 250px;
}

.img-content {
    margin: 5px auto;
    display: flex;
    align-items: center;
    justify-content: center;
}

.title {
    font-size: 20px;
}

.add-btn {
    text-align: right;
    display: inline;
}
</style>