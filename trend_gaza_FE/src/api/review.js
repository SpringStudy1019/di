import { localAxios } from "@/util/http-commons";

const local = localAxios();   // axios instance

const url = "/review";

function list(param, success, fail) {
    local.get(`${url}`, { params: param }).then(success).catch(fail);
}

function detailReview(reviewIdx, success, fail) {
    local.get(`${url}/${reviewIdx}`).then(success).catch(fail);
}
    
export {
    list,
    detailReview,

}