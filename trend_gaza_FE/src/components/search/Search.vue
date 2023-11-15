<script setup>
import { ref, watch } from "vue";
import { searchAttraction } from '@/api/attraction';

const keyword = ref("")
const searchResults = ref([]);

const inputKeyword = () => {
    searchAttraction(keyword.value,
        ({ data }) => {
            
            searchResults.value = data.result;
            for (var i = 0; i < searchResults.value.length; i++) {
                console.log("출력" + searchResults.value[i].title);
            }
        },
        (error) => {
            console.log(error);
        }
    );
}

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
                            <li class="list-group-item" v-for='result in searchResults' :key='result.id'><img src='@/assets/search.svg'>&nbsp;{{ result.title }}</li>
                        </ul>
                    </div>
                <div class="col-3"></div>
            </div>
        </div>
    </div>
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