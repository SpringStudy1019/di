import { localAxios } from "@/util/http-commons";

const local = localAxios();
const url = "/notification";

function registNoti(notiInfo, success, fail) {
    local.post(`${url}`, JSON.stringify(notiInfo)).then(success).catch(fail);
}


export {
    registNoti
};