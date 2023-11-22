import {localAxios} from "@/util/http-commons";

const local = localAxios();

const url = "/attractions";

function getAttractionDetail(attractionId, success, fail) {
    local.get(`${url}/${attractionId}`).then(success).catch(fail);
}

function searchAttraction(keyword, success, fail) {
    local.get(`${url}/auto-search?keyword=${keyword}`).then(success).catch(fail);
}

function listByCategory(param, success, fail) {
    local.get(`${url}/listByCategory`, { params: param })
    .then(success)
    .catch(fail);
}

function searchAttractionsByCondition(param, success, fail) {
    local.get(`${url}/search`, {params: param}).then(success).catch(fail);
}

function searchByWord(keyword, success, fail) {
    local.get(`${url}/searchByWord?keyword=${keyword}`).then(success).catch(fail);
}

function getUserImage(contentId, success, fail) {
    local.get(`${url}/image/${contentId}`).then(success).catch(fail);
}

export {
    getAttractionDetail,
    searchAttraction,
    listByCategory,
    //searchByCategory,
    searchAttractionsByCondition,
    searchByWord,
    getUserImage,
}