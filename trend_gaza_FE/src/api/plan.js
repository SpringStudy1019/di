import {localAxios} from "@/util/http-commons";

const local = localAxios();

const url = "/plans";

function joinPlan(param, success, fail) {
    local.post(`${url}`, { params: param }).then(success).catch(fail);
}

function getPlans(success, fail) {
    local.get(`${url}`).then(success).catch(fail);
}

export {
    joinPlan,
    getPlans,
};