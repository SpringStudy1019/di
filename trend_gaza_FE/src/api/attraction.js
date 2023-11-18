import {localAxios} from "@/util/http-commons";

const local = localAxios();

const url = "/attractions";

function getAttractionDetail(attractionId, success, fail) {
    local.get(`${url}/${attractionId}`).then(success).catch(fail);
}

function searchAttraction(keyword, success, fail) {
    local.get(`${url}/auto-search?keyword=${keyword}`).then(success).catch(fail);
}

function searchByCategory(contentTypeId, sido, word, success, fail) {
    local.get(`${url}/searchByCategory?contentTypeId=${contentTypeId}&sido=${sido}&word=${word}`)
    .then(success)
    .catch(fail);
}

export {
    getAttractionDetail,
    searchAttraction,
    searchByCategory,
}