<template>
  <div class="app-container">
    <!-- 输入表单 -->
    <el-form label-width="120px">
      <!--讲师名称-->
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8889/admin/oss/file/upload?module=avatar"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error="handleAvatarError"
          :before-upload="beforeAvatarUpload">
          <img v-if="teacher.avatar" :src="teacher.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <!--入驻时间-->
      <el-form-item label="入驻时间">
        <el-date-picker v-model="teacher.joinDate" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <!--讲师排序-->
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" :min="0"/>
      </el-form-item>
      <!--讲师头衔-->
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
            -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>
      <!--保存按钮-->
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate()">保存</el-button>
      </el-form-item>
    </el-form>
    >
  </div>
</template>
<script>

import teacher from "@/api/teacher";


export default {

  data() {
    return {
      // 返回讲师对象
      teacher: {
        sort: 0,
        level: 1
      },
      saveBtnDisabled: false//按钮默认可用
    }
  },
  created() {
    //判断路由里面有没有id数学系
    if (this.$route.params.id) {
      this.getTeacherById(this.$route.params.id)
    }
  },
  methods: {
    //文件上传之前的校验
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt524kb = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt524kb) {
        this.$message.error('上传头像图片大小不能超过 512kb!');
      }
      return isJPG && isLt524kb;
    },
    //文件上传失败
    handleAvatarError(message) {
      this.$message.error(message)
    },
    //文件上传成功后
    handleAvatarSuccess(response) {
      if (response.success) {
        this.teacher.avatar = response.data.url;
        this.$forceUpdate();
      } else {
        this.handleAvatarError(response.message);
      }
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true
      if (this.teacher.id) {
        this.update();
      } else {
        this.save()
      }
    },
    //根据id查询用户
    getTeacherById(id) {
      teacher.getTeacherById(id).then(Response => {
        this.teacher = Response.data.item
      });
    },
    //数据更新
    update() {
      teacher.updateTeacher(this.teacher).then(
        response => {
          //弹出提示
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({path: '/teacher'})
        });
    },
    //数据保存
    save() {
      teacher.save(this.teacher).then(
        response => {
          //弹出提示
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({path: '/teacher'})
        }
      )
    }
  }
}
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
