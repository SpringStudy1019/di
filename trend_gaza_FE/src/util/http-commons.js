import axios from "axios";

const { VITE_VUE_API_URL, VITE_YOUTUBE_URL } = import.meta.env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function videoAxios() {
  const instance = axios.create({
    baseURL: VITE_YOUTUBE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function imageAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return instance;
}

export { localAxios, videoAxios, imageAxios };
