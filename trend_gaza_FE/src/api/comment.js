import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "/comment";

function listComment(reviewIdx, success, fail) {
    local.get(`${url}/list/${reviewIdx}`).then(success).catch(fail);
}

function registerCommentRequest(comment, success, fail) {
    local.post(`${url}`, JSON.stringify(comment)).then(success).catch(fail);
}

function deleteCommentRequest(commentIdx, success, fail) {
    local.delete(`${url}/${commentIdx}`).then(success).catch(fail);
}

function getComment(commentIdx, success, fail) {
    local.get(`${url}/getModify/${commentIdx}`).then(success).catch(fail);
}

function modifyCommentRequest(comment, success, fail) {
    local.put(`${url}`, JSON.stringify(comment)).then(success).catch(fail);
}
    
export {
    listComment,
    registerCommentRequest,
    deleteCommentRequest,
    getComment,
    modifyCommentRequest
}