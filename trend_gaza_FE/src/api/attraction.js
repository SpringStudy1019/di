import {localAxios} from "@/util/http-commons";

const local = localAxios();

const url = "/attractions";

function getAttractionDetail(attractionId, success, fail) {
    local.get(`${url}/${attractionId}`).then(success).catch(fail);
}

export {
    getAttractionDetail
}