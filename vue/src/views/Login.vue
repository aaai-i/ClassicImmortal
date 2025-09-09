<template>
    <div class="login-one">
        <div class="login-two">
            <div style="padding: 60px 100px;background-color:white; margin-left: 100px;border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.2)">
<el-form ref="formRef" :rules="data.rules" :model="data.form" style="with:300px">
<div style="margin-bottom: 30px; text-align: center; font-weight: bold; color:darkcyan; font-size: large;" >欢迎登录散修管理系统</div>

<el-form-item prop="username">
<el-input size="large" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
</el-form-item>

<el-form-item prop="password">
<el-input size="large" v-model="data.form.password" placeholder="请输入密码" prefix-icon="Lock"> </el-input>
 </el-form-item>

<el-form-item prop="role">
    <el-select v-model="data.form.role">
        <el-option value="ADMIN" lable="管理员"></el-option>
        <el-option value="EMP" lable="员工"></el-option> 
    </el-select>
</el-form-item>



 <el-form-item>
<el-button @click="login" size="large" style="width: 100%; " type="primary">
登录
</el-button>
 </el-form-item>



</el-form>


            </div>

        </div>
    </div>
</template>


<script setup>
import { reactive,ref } from 'vue';
import {ElMessage} from "element-plus";
import {User,Lock} from "@element-plus/icons-vue"
import request from '@/utils/request';



const data=reactive({
    form:{role:'ADMIN'},
    rules:{
        username:[
            {required:true,message:'请输入账号',trigger:'blur'}
        ],
        password:[
            {required:true,message:'请输入密码',trigger:'blur'}
        ]
    }
})


const formRef=ref()


const login=() =>{
    formRef.value.validate((valid)=>{
        if(valid){
            request.post('/login',data.form).then(res=>{
                if(res.code==='200'){
                    localStorage.setItem('classic_immortal-user',JSON.stringify(res.data))
                    ElMessage.success('登陆成功')
                   setTimeout(() => {
                    location.href = '/manager';  // ✅ 先用这种方式测试，确保能跳
                }, 500)
                }else{
                    ElMessage.error(res.msg)
                }
            })
        }
    })
}


</script>


<style scoped>




.login-one{
    height: 100vh;
    background-image: url("@/assets/loginone.png");
    background-size: cover;
    background-position: 0 -50px;
    overflow: hidden;
}
.login-two{
      width: 40%;
  height: 100%;
  position: absolute;
  align-items: center;
   right: 0;
   display: flex;
   
}


</style>