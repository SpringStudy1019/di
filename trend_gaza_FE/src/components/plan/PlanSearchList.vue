<script setup>
import {defineProps, ref} from "vue";

const props = defineProps({
   attraction: Object
});

const emit = defineEmits(["selectAttractionData"]);

//const selectArr = ref([]);
const showAddBtn = ref(true);       // 추가 버튼 활성화

const selectAttractionFunc = () => {
    console.log("부모에게 전달할 데이터" + props.attraction);
    //selectArr.value.push(props.attraction);
    showAddBtn.value = false;        // 추가 버튼 비활성화
    emit("selectAttractionData", props.attraction);
};
</script>

<template>
    <div class="container">
        <span class="title">{{attraction.title}}</span>
        <div class="img-content">
            <img class="img" :src="attraction.firstImage"/>
        </div>
        <span>{{ attraction.addr1 }} </span>
        <div class="add-btn" v-show="showAddBtn">
            <button @click="selectAttractionFunc">추가</button>
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

.title {
    font-size: 20px;
}

.add-btn {
    text-align: right;
    display: inline;
}
</style>