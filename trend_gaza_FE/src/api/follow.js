import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/follow";

function followList(followeeId, success, fail) {
    local.get(`${url}/${followeeId}`).then(success).catch(fail);
}

function onFollow(followInfo, success, fail) {
    local.post(`${url}`, JSON.stringify(followInfo)).then(success).catch(fail);
}

function offFollow(followeeId, followerId, success, fail) {
    local.delete(`${url}/${followeeId}?followerId=${followerId}`).then(success).catch(fail);
}

export {
    followList,
    onFollow,
    offFollow
}