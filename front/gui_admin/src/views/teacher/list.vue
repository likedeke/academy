<template>
  <div class="app-container">
    <!--条件查询表单-->
    <el-form :inline="true" >
      <el-form-item>
        <!--输入建议-->
        <el-autocomplete
          class="inline-input"
          v-model="searchObj.name"
          :fetch-suggestions="querySearch"
          placeholder="讲师姓名"
          :trigger-on-focus="false"
          value-key="name"
        ></el-autocomplete>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchObj.level" clearable placeholder="头衔">
          <el-option value="1" label="高级讲师"/>
          <el-option value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="入驻时间">
        <el-date-picker
          v-model="searchObj.joinDateBegin"
          placeholder="开始时间"
          value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item label="-">
        <el-date-picker
          v-model="searchObj.joinDateEnd"
          placeholder="结束时间"
          value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getData()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>
    <!--工具条-->
    <!--批量删除按钮-->
    <div style="padding: 10px">
      <el-button type="danger" size="small" @click="batchRemove()" icon="el-icon-delete">批量删除</el-button>
    </div>
    <!--显示讲师列表-->
    <el-table :data="list" border stripe @selection-change="handleSelectionChange">
      <!--多选框-->
      <el-table-column type="selection"/>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">{{ (page - 1) * limit + scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="讲师名字" width="80"/>
      <el-table-column prop="level" label="讲师头衔" width="90">
        <!--根据对应的级别显示讲师-->
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level===1" type="success">高级讲师</el-tag>
          <el-tag v-if="scope.row.level===2">首席讲师</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="讲师资历"/>
      <el-table-column prop="sort" label="排序" width="60"/>
      <el-table-column type="date" prop="joinDate" label="入驻时间" width="120"/>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <!--删除操作-->
          <el-button size="mini" type="danger" @click="removeById(scope.row.id)" icon="el-icon-delete">删除</el-button>
          <!--修改-->
          <router-link :to="'/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <!--分页方法-->
    <el-pagination
      :current-page="this.page"
      :total="this.total"
      :page-size="this.limit"
      :page-sizes="[5,10,20,50]"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      style="padding: 20px 0;text-align: center"
      background
      @current-change="pageChange"
      @size-change="pageSizeChange"
    />
  </div>
</template>
<script>
/* eslint-disable */
import teacher from "@/api/teacher";
import Item from "../layout/components/Sidebar/Item";

export default {
  data() {
    return {
      list: [],//讲师列表
      total: 0,//总记录数
      page: 1,//当前页码
      limit: 10,//每页的记录数
      searchObj: {},//teacher条件查询对象
      selections: [],  //批量选择的
    }
  },
  created() {
    this.getData()
  },
  methods: {
    //获取数据
    getData() {
      //调用分页方法
      teacher.getPageTeacherList(this.page, this.limit, this.searchObj).then(Response => {
        this.list = Response.data.rows
        this.total = Response.data.total
      });
    },
    //页码改变事件
    pageChange(page) {
      this.page = page
      this.getData()
    },
    //每页记录数量改变时间
    pageSizeChange(size) {
      this.limit = size
      this.getData()
    },
    resetData() {
      this.searchObj = {}
      this.getData()
    },
    //删除记录
    removeById(id) {
      //弹出消息
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        //删除
        teacher.delById(id).then(response => {
          //重新请求数据
          this.getData()
          //弹出提示
          this.$message({
            type: 'success',
            message: result.message
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    //批量删除
    batchRemove() {
      //判断idList是否为空
      if (this.selections.length === 0) {
        this.$message({
          type:'warning',
          message:'请选择后在删除',
        })
        return ;
      }
      //弹出消息
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        //获取id
        let idList =[]
        this.selections.forEach(Item => {
          idList.push(Item.id)
        });
        //执行删除
        teacher.batchRemove(idList)
          .then(response => {
          //重新请求数据
          this.getData()
          //弹出提示
          this.$message({
            type: 'success',
            message: result.message
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      });
    },
    //当点击选择框
    handleSelectionChange(selection) {
      this.selections=selection
    },
    querySearch(queryString, callback) {
    teacher.selectTeacherNameByKey(queryString).then(response => {
        callback(response.data.nameList)
      })
    },
  }
}
</script>
