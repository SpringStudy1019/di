import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/bookmark";

function registerBookmark(bookmarkInfo, success, fail) {
    local.post(`${url}`, JSON.stringify(bookmarkInfo)).then(success).catch(fail);
}

function deleteBookmark(userId, contentId, success, fail) {
    local.delete(`${url}/${userId}?contentId=${contentId}`,).then(success).catch(fail);
}

function listBookmark(userId, success, fail) {
    local.get(`${url}/${userId}`).then(success).catch(fail);
}

export {
    registerBookmark,
    deleteBookmark,
    listBookmark
}