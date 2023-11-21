<script setup>

</script>

<template>
    <div class='container'>
        <h2><mark>플랜 등록</mark></h2>
        <div class='form-plan'>
            <div class='form-content'>
                <form>
                    <div class='form-group'>
                        <label for='title'>제목</label>
                        <input id='title' name='title' placeholder='여행 계획 제목 입력'/>
                    </div>
        
                    <div class='form-group'>
                        <label for='start-date'>출발일자</label>
                        <input type="date" id='start-date' name='start-date'/>
                    </div>
        
                    <div class='form-group'>
                        <label for='end-date'>도착일자</label>
                        <input type="date" id='end-date' name='end-date'/>
                    </div>
        
                    <button class='write-btn'>등록</button>
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