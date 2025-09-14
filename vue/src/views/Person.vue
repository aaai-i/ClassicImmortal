<template>
        <div>
  <el-form ref="formRef" :rules="data.rules" :model="data.form" label-width="80px" style="padding-right: 40px; padding-top: 20px">

  <el-form-item label="头像">
  <el-upload
      class="avatar-uploader"
      action="http://localhost:9091/files/upload"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
  >
    <img v-if="data.form.avatar" :src="data.form.avatar" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
</el-form-item>


    
    <el-form-item label="账号" prop="username">
      <el-input  v-model="data.form.username" autocomplete="off" placeholder="请输入账号" />
    </el-form-item>
    <el-form-item label="名称" prop="name">
      <el-input v-model="data.form.name"  autocomplete="off" placeholder="请输入名称" />
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="data.form.sex">
        <el-radio value="男" label="男"></el-radio>
        <el-radio value="女" label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="工号" prop="no">
      <el-input v-model="data.form.no" autocomplete="off"  placeholder="请输入工号" />
    </el-form-item>
    <el-form-item label="年龄">
      <el-input-number style="width: 180px" :min="18" v-model="data.form.age" autocomplete="off"  placeholder="请输入年龄" />
    </el-form-item>
    <el-form-item label="个人介绍">
      <el-input :rows="3" type="textarea" v-model="data.form.description" autocomplete="off" placeholder="请输入个人介绍" />
    </el-form-item>

<div>
    <div style="text-align: center;"></div>
    <el-button @click="updateUser" type="primary" style="padding: 20px,30px;">更新个人信息</el-button>
</div>
  </el-form>

    </div>
   
</template>
<script setup>
import { reactive, ref } from 'vue';
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";


const formRef=ref()
const data=reactive({
    user:JSON.parse(localStorage.getItem('classic_immortal-user')),
    form:{},
    rules:{
        username:[{required:true,message:'请输入账号',trigger:'blur'}],
        name:[{required:true,message:'请输入名称',trigger:'blur'}],
        no:[{required:true,message:'请输入工号',trigger:'blur'}]
    }
})

const emit=defineEmits(['updateUser'])


if(data.user.role==='EMP'){
    request.get('/employee/selectById/'+data.user.id).then(res=>{
        
        data.form=data.user
    })
}else{
    data.form=data.user
}


const updateUser = () => {
  if (data.user.role === 'EMP') {
    request.put('/employee/update', data.form).then(res => {
      if (res.code === '200') {
        ElMessage.success('更新成功')
        // 更新缓存数据
        localStorage.setItem('classic_immortal-user', JSON.stringify(data.form))
        // 触发父级从缓存里面取到最新的数据
        emit('updateUser')
      } else {
        ElMessage.error(res.msg)
      }
    })
  } else {
    request.put('/admin/update', data.form).then(res => {
      if (res.code === '200') {
        ElMessage.success('更新成功')
        // 更新缓存数据
        localStorage.setItem('classic_immortal-user', JSON.stringify(data.form))
        // 触发父级从缓存里面取到最新的数据
        emit('updateUser')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }
}

// res在里面意味着拿到后端返回的结果
const handleAvatarSuccess=(res)=>{
console.log(res.data)
data.form.avatar=res.data
}
</script>


<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>