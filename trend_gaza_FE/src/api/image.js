import { imageAxios } from "@/util/http-commons";

const image = imageAxios();   // axios instance

const url = "/images";

function uploadImage(imageFile, success, fail) {
    image.post(`${url}`, imageFile).then(success).catch(fail);
}

export {
    uploadImage
}