import { localAxios } from "@/util/http-commons";

const local = localAxios();   // axios instance

const url = "/review";

function list(param, success, fail) {
    local.get(`${url}`, { params: param }).then(success).catch(fail);
}

function detailReview(reviewIdx, success, fail) {
    local.get(`${url}/view/${reviewIdx}`).then(success).catch(fail);
}

function registReview(review, success, fail) {
    local.post(`{url}`).then(success).catch(fail);
}

function deleteReview(reviewIdx, success, fail) {
    local.delete(`${url}/${reviewIdx}`).then(success).catch(fail);
}

function modifyReview(reviewIdx, success, fail) {
    local.put(`${url}/${reviewIdx}`).then(success).catch(fail);
}

function getModifyReview(reviewIdx, success, fail) {
    local.get(`${url}/modify/${reviewIdx}`).then(success).catch(fail);
}

    
export {
    list,
    detailReview,
    deleteReview,
    modifyReview,
    getModifyReview,
    registReview
}