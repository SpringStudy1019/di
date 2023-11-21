import axios from "axios";

const { VITE_VUE_API_URL, VITE_YOUTUBE_URL } = import.meta.env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Authorization": sessionStorage.getItem("accessToken"),
    },
  });

  // Request 발생 시 적용할 내용.
  // instance.defaults.headers.common["Authorization"] = "";
  // instance.defaults.headers.post["Content-Type"] = "application/json";
  // instance.defaults.headers.put["Content-Type"] = "application/json";
  return instance;
}

function videoAxios() {
  const instance = axios.create({
    baseURL: VITE_YOUTUBE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8"
    },
  });
  return instance;
}

function imageAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "multipart/form-data",
      "Authorization": sessionStorage.getItem("accessToken"),
    },
  });
  return instance;
}

export { localAxios, videoAxios, imageAxios };
