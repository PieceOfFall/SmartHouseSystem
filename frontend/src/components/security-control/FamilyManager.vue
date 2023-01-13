<template>
    <div id="manager-container">
        <!-- 用户列表 -->
        <el-card id="list-container">
            <template #header>
              <div id="card-header">
                <span>用 户 列 表</span>
              </div>
            </template>

            <el-table
            :data="users"
            :border="true">
                <el-table-column prop="account" label="账户"/>
                <el-table-column prop="createdTime" label="创建时间"/>
                <el-table-column prop="role" label="权限身份"/>
                <el-table-column prop="email" label="邮箱" />
                <el-table-column fixed="right" label="操作">
                    <template #default="scope">
                        <el-button link type="primary" size="small" @click="showEditUser(scope.$index, scope.row)">
                            修改
                        </el-button>
                        <el-button link type="primary" size="small" @click="showDeleteUser(scope.$index, scope.row)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <!-- 编辑用户 -->
        <div 
        id="edit-card"
        class="animate__animated animate__fadeIn animate__faster"
        v-if="isOnEdit">
            <el-card>

                <template #header>
                    <span>修改用户</span>
                </template>

                <el-form
                ref="ruleEditFormRef"
                :model="userEditForm"
                :rules="userEditRules">
                
                   <el-form-item 
                   label="账户"
                   prop="account">
                        <el-input v-model="userEditForm.account" />
                    </el-form-item> 

                    <el-form-item
                    label="邮箱"
                    prop="email">
                        <el-input v-model="userEditForm.email" />
                    </el-form-item>

                    <el-form-item
                    label="权限身份"
                    prop="role">
                        <el-radio-group v-model="userEditForm.role">
                            <el-radio :label="2">root</el-radio>
                            <el-radio :label="1">master</el-radio>
                            <el-radio :label="0">user</el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-link 
                    type="primary"
                    @click="confirmSubmit(ruleEditFormRef,'edit')">
                        确 认
                    </el-link>

                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue';
import { getAllUsers, editUser } from '../../api/security/index';
import { UserRenderData,UserData } from '../../api/security/types';
import { FormInstance, FormRules,ElMessage } from 'element-plus';

/*
   用户列表
*/
// 用户信息
const users = ref<UserRenderData[]>()

// 请求所有用户信息
async function getAndRenderAllUsers() {
    const ret:UserRenderData[] = await (await getAllUsers()).data
    users.value = ret?ret:users.value
}

// 页面初始化请求所有用户信息
onMounted(async()=>{
    await getAndRenderAllUsers()
})

// 修改用户显示
async function showEditUser(index: number, row:UserRenderData) {
    userEditForm.account = row.account
    userEditForm.email = row.email
    userEditForm.role = row.role === 'root'?2:row.role==='master'?1:0
    isOnEdit.value = true
}

// 删除用户显示
async function showDeleteUser(index: number, row:UserRenderData) {
    
}

/*
   修改用户
*/
const isOnEdit = ref(false)
const ruleEditFormRef = ref<FormInstance>()
const userEditForm = reactive({
    account:'',
    email:'',
    role:0
})

// 用户修改表单验证规则
const userEditRules = reactive<FormRules>({
    account:[
        {required:true,message:'请输入账号',trigger:'blur'},
        { min: 9, max: 9, message: '长度应该为9', trigger: 'blur'}
    ],
    email:[
        { required: true, message: '请输入邮箱地址', trigger: 'blur'},
        { type: 'email', message: '请输入正确的邮箱格式', trigger: ['blur', 'change']},
    ]
})

// 根据操作类型验证表单并提交
async function confirmSubmit(formEl: FormInstance | undefined,operationType:'add'|'edit') {
    if (!formEl) return
    await formEl.validate( async function (valid, fields) { 
        if (valid) {
            if(operationType === 'edit') {
                if(await (await editUser(userEditForm)).status === 200) {
                    ElMessage({
                        message:'操作成功',
                        type:'success'
                    })
                } else {
                    ElMessage({
                        message:'操作失败',
                        type:'error'
                    })
                }
                isOnEdit.value = false
                
            } else {
                //TODO: 添加用户
            }
        } else {
            ElMessage({
                message: '请补全信息',
                type: 'error'
            })
        }
    })
}


</script>

<style lang="less" scoped>
#manager-container {
    #list-container {   
        z-index: 1;
        margin: 0 6rem 0 6rem;
        #card-header {
            font-weight: bold;
        }
    }
    #edit-card {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        z-index: 100;
        span {
            color: darkgray;
            margin-bottom: 1rem;
        }
        
        .el-card {
            position: relative;
            line-height: 1.8rem;
            width: 30rem;
            .el-form {
                font-weight: bold;
                .el-form-item {
                    margin-bottom: 2.5rem;
                }
            }
            .el-link {
                position: absolute;
                padding: 0.2rem;
                font-size: 15px;
                bottom: 0;
                left:50%;
                transform: translate(-50%,0);
            }

        }
    }
    
}
</style>