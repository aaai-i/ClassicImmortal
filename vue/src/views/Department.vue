 <template>
<div>
<div class="card" style="margin-bottom: 5px;">
    <el-input style="width: 240px; margin-right: 10px" v-model="data.name" placeholder="请输入名称查询" prefix-icon="Search"></el-input>
<el-button type="primary"  @click="load">查询 </el-button>    
<el-button type="warning" @click="reset">重置</el-button>
</div>

<div class="card" style="margin-bottom: 5px">
      <el-button type="primary" @click="handleAdd">新 增</el-button>
      <el-button type="warning" @click="delBatch">批量删除</el-button>
      <el-button type="info">导入</el-button>
      <el-button type="success">导出</el-button>
    </div>

<div class="card" style="margin-bottom: 5px;">
    <el-table :data="data.tableData" stripe  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
    <el-table-column label="名称" prop="name" />
    <el-table-column label="操作">
    <template #default="scope">
    <el-button type="primary" circle @click="handleUpdate(scope.row)"><el-icon><Edit/></el-icon></el-button>
    <el-button type="danger" circle @click="del(scope.row.id)" >删除</el-button>
    </template>   
    </el-table-column>
</el-table>

<div style="margin-top:15px">

    <el-pagination
    v-model:current-page="data.pageNum"
    v-model:page-size="data.pageSize"
    :page-sizes="[5,10,15,20]"
    background
    layout="total,sizes,prev,pager,next,jumper"
    :total="data.total"


    />
</div>
</div>

<div>
      <el-dialog title="部门信息" v-model="data.formVisible" width="500" destroy-on-close>
  <el-form ref="formRef" :rules="data.rules" :model="data.form" label-width="80px" style="padding-right: 40px; padding-top: 20px">
    <el-form-item label="名称" prop="name">
      <el-input  v-model="data.form.name" autocomplete="off" placeholder="请输入账号" />
    </el-form-item>
 <el-form-item label="部门" >
    <el-select v-model="data.form.departmentId">
      <el-option v-for="item in data.departmentList" :key="item.id" :label="item.name" :value=item.id>     </el-option>
    </el-select>
    </el-form-item>
   
     
  </el-form>
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="data.formVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">保 存</el-button>
    </div>
  </template>
</el-dialog>
    </div>


</div>


</template>


<script setup>
import request from "@/utils/request";
import { ElMessage, ElMessageBox } from "element-plus";
import { reactive,ref } from "vue";


const data=reactive({
    name:null,
    tableData:[],
    pageNum:1,
    pageSize:10,
    total:0,
    formVisible:false,
    form:{},
    ids:[],
    departmentList:[],
    rules:{
username:[{
    required:true,message:'请输入名称',trigger:'blur'
}],

    }
    
})

const formRef=ref()

request.get('/department/selectAll').then(res=>{
  data.departmentList=res.data
})





const load=()=>{
    request.get('department/selectPage',{
        params:{
            pageNum:data.pageNum,
            pageSize:data.pageSize,
            name:data.name
        }
    }).then(res=>{
        data.tableData=res.data.list
        data.total=res.data.total
    })
}
load()





const reset=()=>{
    data.name=null
    load()
}

const handleAdd=()=>{
    data.formVisible=true
    data.form={}
}

const add=()=>{
    request.post('department/add',data.form).then(res=>{
        if(res.code==='200'){
            data.formVisible=false
            ElMessage.success('操作成功')
            load()
        }else{
            ElMessage.error(res.msg)
        }

    })
}



//这里面是表单校验
const save = () => { // 在一个保存方法里面做2个操作  一个是新增 一个是编辑
  formRef.value.validate((valid)=>{
    if(valid){
data.form.id ? update() : add()
    }
  })
}


const handleUpdate=(row)=>{
  data.form=JSON.parse(JSON.stringify(row))
  data.formVisible=true
}

const update=()=>{
  request.put('/department/update',data.form).then(res=>{
    if(res.code==='200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()
    }else{
      ElMessage.error(res.msg)
    }
  })
}

const del=(id)=>{
  ElMessageBox.confirm('删除数据后无法恢复，您确定要删除散修吗？？？','删除确认',{type:'warning'}).then(()=>{
    request.delete('/department/deleteById/'+id).then(res=>{
      if(res.code==='200'){
        ElMessage.success('操作成功')
        load()
      }else{
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}


const handleSelectionChange = (rows) => {  // 返回所有选中的行对象数组
  // 从选中的行数组里面取出所有行的id组成一个新的数组
  data.ids = rows.map(row => row.id)
  console.log(data.ids)
}

const delBatch=()=>{
  if(data.ids.length===0){
    ElMessage.warning('请选择数据')
    return
  }
  ElMessageBox.confirm('批量删除散修后数据无法恢复,您确定删除吗','删除确认',{type:'warning'}).then(()=>{
    request.delete('/department/deleteBatch',{data:data.ids}).then(res=>{
      if(res.code==='200'){
        ElMessage.success('操作成功')
        load()
      }else{
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}



</script>






















<style>




</style>