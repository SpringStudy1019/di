<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import {getModifyPlan, registerPlan} from "@/api/plan";

const router = useRouter();

const props = defineProps({
    type: String,
    planIdx: String
})

const plan = ref({
    endDate: "",
    startDate: "",
    title: "",
});

if (props.type === "modify") {
    onMounted(() => {
        getModifyPlanFunc();
    });
}

const getModifyPlanFunc = () => {
    getModifyPlan(props.planIdx,
        ({ data }) => {
            console.log(data);
        }, (error) => {
            console.log(error);
        });
}

const onSubmit = () => {
    props.type === "regist" ? writePlan() : updatePlan();
}

const writePlan = () => {
    registerPlan(plan.value,
        ({ data }) => {
            console.log(data);
        },
        (error) => {
            console.log(error);
        });
}
</script>

<template>
    <div class='container'>
        <h2><mark>플랜 등록</mark></h2>
        <div class='form-plan'>
            <div class='form-content'>
                <form @submit.prevent='onSubmit'>
                    <div class='form-group'>
                        <label for='title'>제목</label>
                        <input id='title' name='title' v-model='plan.title' placeholder='여행 계획 제목 입력'/>
                    </div>
        
                    <div class='form-group'>
                        <label for='start-date'>출발일자</label>
                        <input type="date" id='start-date' v-model='plan.startDate' name='start-date'/>
                    </div>
        
                    <div class='form-group'>
                        <label for='end-date'>도착일자</label>
                        <input type="date" id='end-date' v-model='plan.endDate' name='end-date'/>
                    </div>
        
                    <button class='write-btn' type='submit' v-if="type === 'regist'">등록</button>
                    <button class='modify-btn' type='submit' v-else>수정</button>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped>

.container {
    margin: 50px;
}

h2 {
    text-align: center;
}

.form-plan {
    margin: 50px auto;
    text-align: center;
    display: flex; /* 수평 가운데 정렬을 위한 flex 설정 */
    justify-content: center; /* 수평 가운데 정렬을 위한 설정 */
    border: 1px solid black;
    border-radius: 3%;
    width: 500px;
    height: 500px;
}

.form-content {
    width: 350px;
    height: 350px;
    display: flex;
    justify-content: center;
    flex-direction: column;
}

.form-group {
    margin: 30px;
}

input {
    width: 300px;
}

label {
    display: block;
    margin-bottom: 5px;
}

.write-btn {
    width: 300px;
    background-color: plum;
    color: white;
    border-radius: 5%;
}
</style>