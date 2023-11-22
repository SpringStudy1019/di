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

// 여행 계획 등록
function registerAttractionPlan(planIdx, planRequest, success, fail) {
    local.post(`${url}/${planIdx}`, JSON.stringify(planRequest)).then(success).catch(fail);
}

// 여행 계획 조회
function getAttractionPlan(attractionPlanId, success, fail) {
    console.log("여행 계획 조회 API 요청");
    local.get(`${url}/${attractionPlanId}`).then(success).catch(fail);
}

function modifyPlan(attractionPlanId, success, fail) {
    local.put(`${url}/${attractionPlanId}`).then(success).catch(fail);
}

function getModifyPlan(planIdx, success, fail) {
    local.get(`${url}/modify/${planIdx}`).then(success).catch(fail);
}

// 방 만들기
function registerPlan(setPlanRequest, success, fail) {
    local.post(`${url}`, JSON.stringify(setPlanRequest)).then(success).catch(fail);
}

function updatePlan(planIdx, planModifyRequest, success, fail) {
    local.put(`${url}/modify/${planIdx}`, JSON.stringify(planModifyRequest)).then(success).catch(fail);
}

export {
    joinPlan,
    getPlans,
    getInvitedPlan,
    getCreatedPlan,
    registerAttractionPlan,
    modifyPlan,
    getModifyPlan,
    registerPlan,
    updatePlan,
    getAttractionPlan
};
