<script setup>

// 날짜 계산
function calculateDays(startDateStr, endDateStr) {
  // Parse the timestamp strings into Date objects
  const startDate = new Date(startDateStr);
  const endDate = new Date(endDateStr);

  // Calculate the difference in milliseconds
  const timeDifference = endDate - startDate;

  // Convert milliseconds to days
  const daysDifference = timeDifference / (1000 * 60 * 60 * 24);

  // Round to the nearest whole number
  const roundedDays = Math.round(daysDifference);

  return roundedDays;
}

// Example usage
const startDateStr = '2023-11-10 20:27:09';
const endDateStr = '2023-11-12 08:17:09';

const daysDifference = calculateDays(startDateStr, endDateStr);

// console.log(`The difference in days is: ${daysDifference}`); 
</script>

<template>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <h1>여행 계획</h1>
        <div class="col-sm-6">
        <div class="card text-center">
        <div class="card-body">
            <h5 class="card-title">plan.title</h5>
            <p class="card-text">user_id와 함께하는 여행이에요. <br>
                startDate부터 endDate까지 (00일)</p>
            <router-link 
                :to="{ name: 'plan'}" 
                class="btn btn-primary me-2">
                여행계획짜기
            </router-link>
   
            <!-- 여행계획 상세보기 -->
            
           
        </div>
        </div>
    </div>
    </div>
    <div class="col-2"></div>
</div>
</template>

<style scoped>

</style>