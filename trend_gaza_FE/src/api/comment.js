import { localAxios } from "@/util/http-commons";

const local = localAxios();

const url = "/comment";

function listComment(reviewIdx, success, fail) {
    local.get(`${url}/list/${reviewIdx}`).then(success).catch(fail);
}
    
export {
    listComment,
}