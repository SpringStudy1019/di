<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { searchAttraction, getAttractionDetail } from '@/api/attraction';

const keyword = ref("")
const searchResults = ref([]);
const router = useRouter();

const inputKeyword = () => {
    searchAttraction(keyword.value,
        ({ data }) => {
            searchResults.value = data.result;
        },
        (error) => {
            console.log(error);
        }
    );
}

const selectKeyword = (keyword) => {
    //getAttractionDetail()
    console.log(keyword.contentId);
    router.push({name:'attraction-view', params:{attractionIdx: keyword.contentId}});
}

// 다른 곳  클릭하면, 검색 리스트 사라지게 하기
// Close search results when clicking outside the search input or result list
const closeResults = (event) => {
  if (!event.target.closest(".search-container")) {
    searchResults.value = [];
  }
};

onMounted(() => {
  // Add click event listener to the document body
  document.body.addEventListener("click", closeResults);
});

</script>

<template>
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
                                v-model='keyword'
                                @input='inputKeyword'
                                autocomplete='off'
                                role='combobox'
                                aria-controls='matches'
                                aria-expanded='false'
                                aria-live='off'
                                />
                                <button class="btn btn-dark" type="button">검색</button>
                            </div>
                        </form>
                        <!-- 검색 결과 표시 -->
                        <ul class="list-group">
                            <li class="list-group-item" v-for='result in searchResults' :key='result.id' @click='selectKeyword(result)'><img src='@/assets/search.svg'>&nbsp;{{ result.title }}</li>
                        </ul>
                    </div>
                <div class="col-3"></div>
            </div>
        </div>
    </div>
    <!-- result.id 가 뭔지 찍어보자. -->
    <!-- <div>{{ result.id }}</div> -->
</template>

<style scoped>
.search-result {
    list-style-type: none;
    border: black grid;
}

.list-group {
    text-align: left;
}

.list-group-item:hover {
  background-color: #f5f5f5; /* 마우스를 올린 곳의 배경색 */
}

ul {
    width: 50%;
}

.list-group {
    width: 100%;
}
</style>