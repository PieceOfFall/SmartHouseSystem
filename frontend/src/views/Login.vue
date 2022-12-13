<template>
    <div class="login-container">
        <el-card class="form-box" shadow="hover" :style="{opacity:`${formOpacity}%`}">
            <span class="title">5 3 9 <span class="smart-title">智能家居</span></span>
        <el-form 
        ref="ruleFormRef"
        :model="form"
        :rules="rules"
        label-width="60px"
        label-position = "left"
        :hide-required-asterisk = "true">
            <el-form-item label="账号"
            id="account-item"
            prop="account">
                <el-input v-model="form.account"
                placeholder="输入账号" 
                :suffix-icon="User"
                @keydown.native.enter="submitForm(ruleFormRef)"/>
            </el-form-item>

            <el-form-item label="密码"
            id="pwd-item"
            prop="password">
                <el-input v-model="form.password" 
                type = "password"
                placeholder="输入密码"
                :suffix-icon="Unlock"
                @keydown.native.enter="submitForm(ruleFormRef)"/>
            </el-form-item>

            <el-button plain @click="submitForm(ruleFormRef)">
                登 录<el-icon class="el-icon--right"><Upload /></el-icon>
            </el-button>
        </el-form>
    </el-card>
    </div>
</template>

<script setup lang="ts">
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, reactive, ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import {
    Unlock,
    User,
    Upload 
  } from '@element-plus/icons-vue'
import {useRouter} from 'vue-router';
import {userLogin} from '../api/login/index';
import {Token} from '../api/login/types';

/*
   验证表单并提交
*/
const ruleFormRef = ref<FormInstance>()
const router = useRouter()
const form = reactive({
    account:'',
    password:''
})
// 表单验证规则
const rules = reactive<FormRules>({
    account:[
        {required:true,message:'请输入账号',trigger:'blur'},
        { min: 9, max: 9, message: '长度应该为9', trigger: 'blur' }
    ],
    password:[
        {required:true, message:'请输入账号',trigger:'blur'},
        {required:true, min: 6, message: '密码长度至少为6', trigger: 'blur' }
    ]
})

// 验证表单，提交数据
async function submitForm(formEl: FormInstance | undefined) {
    if (!formEl) return
    await formEl.validate( async function (valid, fields) {   
    if (valid) {
      let token:Token = await (await userLogin(form.account,form.password)).data
      window.localStorage.setItem("authorization",token)
      ElMessage({
        message: `欢迎回来！${form.account}`,
        type: 'success',
    })
    router.push('/homepage')
    } else {
        ElMessage({
            message: '请补全信息',
            type: 'error'
          })
    }

  })
    
}

// 页面加载时逐渐显示表单
let formOpacity = ref(0)
onMounted(()=>{
    const Interval:number = setInterval(()=>{
        if(formOpacity.value !== 60){
            formOpacity.value++
        }
    },30)
    setTimeout(()=>{
        clearInterval(Interval)
        formOpacity.value =60
    },3000)
})

</script>

<style lang="less" scoped>
.login-container {
    position: relative;
    background-image: url("../assets/img/login.jpg");
    background-repeat: no-repeat;
    height: 100vh;
    width: 100%;
    background-size: cover;
    background-position: center center;
    .form-box {
        position: absolute;
        text-align: center;
        user-select: none;
        line-height: 6rem;
        padding: 0 5rem;
        left: 50%; 
        top: 50%; 
        transform: translate(-50%,-50%);
        .title{
            font-size: larger;
            font-family: 'STXingkai';
            letter-spacing: 5px;
            .smart-title{
                letter-spacing: 10px;
            }
        }
        #account-item{
            margin-bottom: 2rem;
        }
        
        #pwd-item{
            margin-bottom: 0;
        }

    }
    
}


</style>