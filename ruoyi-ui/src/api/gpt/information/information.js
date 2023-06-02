import request from '@/utils/request'

// 查询资料信息列表
export function listInformation(query) {
  return request({
    url: '/gpt/information/list',
    method: 'get',
    params: query
  })
}

// 查询资料信息详细
export function getInformation(id) {
  return request({
    url: '/gpt/information/' + id,
    method: 'get'
  })
}

// 新增资料信息
export function addInformation(data) {
  return request({
    url: '/gpt/information',
    method: 'post',
    data: data
  })
}

// 修改资料信息
export function updateInformation(data) {
  return request({
    url: '/gpt/information',
    method: 'put',
    data: data
  })
}

// 删除资料信息
export function delInformation(id) {
  return request({
    url: '/gpt/information/' + id,
    method: 'delete'
  })
}
