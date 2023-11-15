import { videoAxios } from "@/util/http-commons";

const video = videoAxios();

function searchVideos(param, success, fail) {
    video.get("", { params: param }).then(success).catch(fail);
}

export { searchVideos };
