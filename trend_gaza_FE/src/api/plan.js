import {localAxios} from "@/util/http-commons";

const local = localAxios();

const url = "/plans";

// 초대된 여행 계획에 참여하기
function joinPlan(joinInfo, success, fail) {
    local.post(`${url}/join`, JSON.stringify(joinInfo)).then(success).catch(fail);
}

function getPlans(success, fail) {
    local.get(`${url}`).then(success).catch(fail);
}

function getInvitedPlan(userId, success, fail) {
    local.get(`${url}/invite/${userId}`).then(success).catch(fail);
}

function getCreatedPlan(userId, success, fail) {
    local.get(`${url}/created/${userId}`).then(success).catch(fail);
}

function registerPlan(planIdx, planRequest, success, fail) {
    console.log(planRequest);
    local.post(`${url}/${planIdx}`, JSON.stringify(planRequest)).then(success).catch(fail);
}

export {
    joinPlan,
    getPlans,
    getInvitedPlan,
    getCreatedPlan,
    registerPlan
};
