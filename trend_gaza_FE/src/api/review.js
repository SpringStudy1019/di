import { localAxios } from "@/util/http-commons";

const local = localAxios();   // axios instance

const url = "/review";

function list(param, success, fail) {
    local.get(`${url}`, { params: param }).then(success).catch(fail);
}

function detailReview(reviewIdx, success, fail) {
    local.get(`${url}/view/${reviewIdx}`).then(success).catch(fail);
}

function getReviewImage(reviewIdx, success, fail) {
    local.get(`${url}/${reviewIdx}/images`).then(success).catch(fail);
}

function registReview(review, success, fail) {
    local.post(`${url}`, JSON.stringify(review)).then(success).catch(fail);
}

function getModifyReview(reviewIdx, success, fail) {
    local.get(`${url}/modify/${reviewIdx}`).then(success).catch(fail);
}

function modifyReview(review, success, fail) {
    local.put(`${url}`, JSON.stringify(review)).then(success).catch(fail);
}

function deleteReview(reviewIdx, success, fail) {
    local.delete(`${url}?reviewIdx=${reviewIdx}`).then(success).catch(fail);
}
    
export {
    list,
    detailReview,
    registReview, 
    modifyReview, 
    deleteReview,
    getModifyReview,
    getReviewImage
}