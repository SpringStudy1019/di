<script setup>
import {defineProps} from "vue";

const props = defineProps({
   selectAttraction: Object
});
</script>

<template>
    <div class="container">
        <span class="title">{{selectAttraction.title}}</span>
        <div class="img-content">
            <img class="img" :src="selectAttraction.firstImage"/>
        </div>
        <span>{{ selectAttraction.addr1 }} </span>
        <div class="delete-btn">
            <button>삭제</button>
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
    height: 200px;
    display: block;
}

.img-content {
    margin: 5px auto;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>