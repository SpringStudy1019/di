import {localAxios} from "@/util/http-commons";

const local = localAxios();

const url = "/plans";

function registerPlan(planIdx, planRequest, success, fail) {
    console.log(planRequest);
    local.post(`${url}/${planIdx}`, JSON.stringify(planRequest)).then(success).catch(fail);
}

export {
    registerPlan
}