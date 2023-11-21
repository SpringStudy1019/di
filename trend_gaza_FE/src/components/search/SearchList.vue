<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { searchByWord } from '@/api/attraction';

const route = useRoute();
const keyword  = ref(route.params.word);

onMounted(() => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
  getSearchList();
});


const searchList = ref([])
const getSearchList = () => {
    searchByWord(
        keyword.value,
        ({ data }) => {
            searchList.value = data;
            console.log(data)
        },
        (error) => {
            console.log(error);
        }
    )
}

</script>

<template>
    <div class="margin-big"></div>
    <h3>{{keyword}}에 대한 검색 결과입니다</h3>
    <div class="margin-big"></div>
    <div>
        <table class="table">
  <thead>
    <tr>
      <th scope="col">여행지명</th>
      <th scope="col">주소</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="element in searchList" :key="element.contentId">
        <router-link 
            :to="{ name: 'attraction-view', params: { attractionIdx:element.contentId } }" 
            class="attraction-link"  
            >
            <th scope="row">{{ element.title }}</th>
        </router-link>
      <td>{{element.addr1}}</td>
    </tr>
  </tbody>
</table>
    </div>
    <div class="margin-big"></div>
</template>

<style scoped>
.margin-medium {
    margin-bottom: 30px;
}
.margin-big {
    margin-bottom: 50px;
}
.attraction-link {
  text-decoration: none; 
  color: #333; 
  transition: color 0.3s ease; 
}

.attraction-link:hover {
  color: #83A2FF; 
}
</style>