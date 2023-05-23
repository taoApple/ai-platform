import request from '@/utils/request'

export default {
  queryScene:function (params) {
    return request({
      url: '/sd/scene',
      method: 'post',
      data: params
    })
  },
  querySd:function (params) {
    return request({
      url: '/sd/sd',
      method: 'post',
      data: params
    })
  }
}
