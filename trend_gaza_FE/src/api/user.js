import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/user";

async function userConfirm(param, success, fail) {
    console.log("param", param);
    await local.post(`/user/login`, param).then(success).catch(fail);
    console.log("userConfirm ok");
}

async function findById(userId, success, fail) {
local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
await local.get(`/user/info/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
await local.post(`${url}/refresh`, JSON.stringify(user)).then(success).catch(fail);
}

async function logout(userId, success, fail) {
    await local.get(`${url}/logout/${userId}`).then(success).catch(fail);
}

function registUser(user, success, fail) {
    local.post(`${url}/register`, JSON.stringify(user)).then(success).catch(fail);
}

function modifyUser(user, success, fail) {
    local.put(`${url}/modify`, JSON.stringify(user)).then(success).catch(fail);
}

function idCheck(userId, success, fail) {
    local.get(`${url}/idCheck/${userId}`).then(success).catch(fail);
}

function deleteUser(userId, success, fail) {
    local.delete(`${url}/${userId}`).then(success).catch(fail);
}

function getUser(userId, success, fail) {
    local.get(`${url}/view/${userId}`).then(success).catch(fail);
}

function modifyImage(imageInfo, success, fail) {
    local.put(`${url}/image`, JSON.stringify(imageInfo)).then(success).catch(fail);
}

export {
    userConfirm, 
    findById, 
    tokenRegeneration, 
    logout,
    registUser, 
    modifyUser, 
    idCheck, 
    deleteUser,
    getUser,
    modifyImage
};
