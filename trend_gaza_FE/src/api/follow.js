import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/follow";

function followList(followeeId, success, fail) {
    local.get(`${url}/${followeeId}`).then(success).catch(fail);
}

export {
    followList,
}