<template>
<div>
<div class="card" style="margin-bottom: 5px;">
    <el-input style="width: 240px; margin-right: 10px" v-model="data.name" placeholder="请输入名称查询" prefix-icon="Search"></el-input>
<el-button type="primary" >查询 </el-button>    
<el-button type="warning" >重置</el-button>
</div>

<div class="card" style="margin-bottom: 5px">
      <el-button type="primary" >新 增</el-button>
      <el-button type="warning" >批量删除</el-button>
      <el-button type="info">导入</el-button>
      <el-button type="success">导出</el-button>
    </div>

<div class="card" style="margin-bottom: 5px;">
    <el-table :data="data.tableData" strip @selection-change="handleSelectionChange">
    <el-table-column label="账号" prop="username" />
    <el-table-column label="性别" prop="sex"/>
    <el-table-column label="工号" prop="no" />
    <el-table-column label="年龄" prop="age" />
    <el-table-column label="个人介绍" prop="description" />
    <el-table-column label="操作">
    <template #default="scope">
    <el-button type="primary" @click="handleUpdate(scope.row)">编辑</el-button>
    <el-button type="danger" @click="del(scope.row.id)" >删除</el-button>
    </template>   
    </el-table-column>
</el-table>
</div>







</div>


</template>


<script setup>
import request from "@/utils/request";
import { reactive } from "vue";

const data=reactive({
    name:null,
    tableData:[]
})







</script>







<style>




</style>