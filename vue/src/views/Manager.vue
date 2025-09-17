<template>

<div>
<!-- 顶部 -->
  <div style="height: 70px; background-color: #006080;display: flex; align-items: center;">
    <div style="width:200px;display: flex; align-items: center; padding-left: 15px;" >
      <img style="width: 40px" src="@/assets/logo.svg" alt="">
      <span style="font-size: 22px; color: white; margin-left: 5px;">后台管理系统</span>
  </div>
      <div style="flex: 1"></div>
      <div style="width: fit-content; display: flex; align-items: center; padding-right: 10px">
        <img :src="data.user.avatar||'@/assets/avatarone.jpg' " alt="" style="width: 40px; height: 40px">
        <span style="color: white; margin-left: 5px">{{data.user.name}}</span>
  </div>
</div>
<!-- 左下 -->
<div style="display: flex;">
<div style="width: 200px; border-right: 2px solid #ddd; min-height:calc(100vh - 60px) ">
  <el-menu router :default-active="router.currentRoute.value.path" :default-openeds="['1']"  style="border:0">
<el-menu-item index="/manager/home"><el-icon><House/></el-icon>系统首页</el-menu-item>
<el-menu-item > <el-icon><DataAnalysis /></el-icon> 数据统计</el-menu-item>
<el-menu-item  index="/manager/article">  文章管理</el-menu-item>
<el-menu-item  index="/manager/department">  部门管理</el-menu-item>
<el-sub-menu index="1">
    <template #title> <el-icon><User/></el-icon> <span>用户管理</span></template>
     <el-menu-item  index="/manager/admin">管理员信息</el-menu-item>
    <el-menu-item index="/manager/employee">员工信息</el-menu-item>
</el-sub-menu>

 <el-menu-item  index="/manager/person"> <el-icon><Lock /></el-icon>个人信息</el-menu-item>
 <el-menu-item index="/manager/password"><el-icon><UserFilled /></el-icon>修改密码</el-menu-item>
<el-menu-item @click="logout"> <el-icon><SwitchButton /></el-icon> 退出登录 </el-menu-item>
</el-menu>
</div>
<!-- 右下主体部分 -->
 <div style="flex:1; width: 0; background-color: #f5f7ff; padding: 10px;">
     <RouterView /> 
    </div>
  </div>
</div>
</template>


<script setup>
import {reactive} from "vue";
import router from "@/router/index.js";



const data=reactive({
user:JSON.parse(localStorage.getItem('classic_immortal-user'))
// localStorage.getItem('xm-pro-user' 因为这个是个字符串形式，需要转为json格式
})


const logout = () =>{
  localStorage.removeItem('classic_immortal-user')
  location.href='/login'
}


</script>


<style>
.el-menu .is-active {
  background-color: #e6ecf7 !important;
}
</style>