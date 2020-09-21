//调用本项目的base axios
import request from '@/utils/request'

export default {
  //获取所有讲师
  getTeacherList() {
    return request({
      url: '/admin/edu/teacher',
      method: 'get'
    })
  },
  //讲师分页方法
  getPageTeacherList(page, limit, searchObj) {
    return request({
      url: '/admin/edu/teacher/' + page + '/' + limit,
      method: 'get',
      params: searchObj
    })
  },
  //根据id删除
  delById(id) {
    return request({
      url: '/admin/edu/teacher/' + id,
      method: 'delete',
    })
  },
  //根据id列表删除
  batchRemove(idList) {
    return request({
      url: '/admin/edu/teacher/batch-remove',
      method: 'delete',
      data: idList
    })
  },
  //保存
  save(teacher) {
    return request({
      url: '/admin/edu/teacher',
      method: 'post',
      data: teacher
    })
  },
  //根据id获取用户
  getTeacherById(id) {
    return request({
      url: '/admin/edu/teacher/' + id,
      method: 'get',
    });
  },
  //修改讲师
  updateTeacher(teacher) {
    return request({
      url: '/admin/edu/teacher',
      method: 'put',
      data: teacher
    });
  },
  selectTeacherNameByKey(key) {
    return request({
      url: '/admin/edu/teacher/list/'+key,
      method: 'get',
    });
  }
}
