import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/follow";

function followList(followeeId, success, fail) {
    local.get(`${url}/${followeeId}`).then(success).catch(fail);
}

function onFollow(followInfo, success, fail) {
    local.post(`${url}`, JSON.stringify(followInfo)).then(success).catch(fail);
}

function offFollow(followerId, followeeId, success, fail) {
    local.delete(`${url}/${followerId}/${followeeId}`).then(success).catch(fail);
}

function countFollowers(followerId, success, fail) {
    local.get(`${url}/count/${followerId}`).then(success).catch(fail);
}

function listRelated(currentUserId, followeeId, success, fail) {
    local.get(`${url}/related/${currentUserId}/${followeeId}`).then(success).catch(fail);
}

function checkFollow(followerId, followeeId, success, fail) {
    local.get(`${url}/check/${followerId}/${followeeId}`).then(success).catch(fail);
}

export {
    followList,
    onFollow,
    offFollow,
    countFollowers,
    listRelated,
    checkFollow
}