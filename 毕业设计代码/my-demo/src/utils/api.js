import axios from 'axios'

import {
  Message
} from 'element-ui'

let base = ''
export const postRequest = (url, param) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: param,
    transformRequest: [function (data) {
      // 对 data 进行任意转换处理
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      console.log(ret);
      return ret;
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export const postRequestUploadFile = (url, param) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: param,
    processData: false, //必写
    contentType: false,


  })
}
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
  })
}

export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params
  })
}

export const deleteRequest = (url, params) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    data: params
  })
}
