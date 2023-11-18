import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/notification";

function registNoti(notiInfo, success, fail) {
    local.post(`${url}`, JSON.stringify(notiInfo)).then(success).catch(fail);
}

function listNoti(userIdTo, success, fail) {
    local.get(`${url}/${userIdTo}`).then(success).catch(fail);
}

function deleteNoti(userIdTo, userIdFrom, success, fail) {
    local.delete(`${url}/${userIdTo}?userIdFrom=${userIdFrom}`).then(success).catch(fail);
}

// function checkNoti(userIdTo, success, fail) {
//     local.post(`${url}/${userIdTo}`,).then(success).catch(fail);
// }
 

export {
    registNoti,
    listNoti,
    deleteNoti,
    // checkNoti
};