<template>
    <div id="manager-container">
        <!-- 用户列表 -->
        <el-card id="list-container">
            <template #header>
              <div id="card-header">
                <span>用 户 列 表</span>
              </div>
              <div id="new-user" @click="showInsertUser">
                + 添 加 用 户
              </div>
            </template>

            <el-table
            v-loading="loading"
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
                        
                        <el-popconfirm
                        width="220"
                        confirm-button-text="确认"
                        cancel-button-text="取消"
                        :icon="InfoFilled"
                        icon-color="#626AEF"
                        title="确认要删除该用户吗"
                        @confirm="deleteUser(scope.$index, scope.row)"
                        >
                            <template #reference>
                                <el-button link type="primary" size="small">
                                    删除
                                </el-button>
                            </template>
                        </el-popconfirm>
    
                        
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <!-- 添加用户 -->
        <div
        id="insert-card"
        class="animate__animated animate__fadeIn animate__faster"
        v-if="isOnInsert">
            <el-card>

                <template #header>
                    <span>添加用户</span>
                </template>

                <el-form
                ref="ruleInsertFormRef"
                :model="userInsertForm"
                :rules="userInsertRules">

                    <el-form-item
                    label="账户"
                    prop="account">
                        <el-input 
                        v-model="userInsertForm.account"
                        placeholder="请 设 置 账 户 （ 9 位 数 字 ）" />
                    </el-form-item>

                    <el-form-item
                    label="密码"
                    prop="password">
                        <el-input
                        v-model="userInsertForm.password"
                        placeholder="请 设 置 密 码 （ 至 少 6 位 ）" />
                    </el-form-item>

                    <el-form-item
                    label="邮箱"
                    prop="email">
                        <el-input 
                        v-model="userInsertForm.email"
                        placeholder="请 输 入 您 的 邮 箱" />
                    </el-form-item>
                    
                    <el-form-item
                    label="权限身份"
                    prop="role">
                        <el-radio-group v-model="userInsertForm.role">
                            <el-radio :label="2">root</el-radio>
                            <el-radio :label="1">master</el-radio>
                            <el-radio :label="0">user</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>

                <!-- 确认添加 -->
                <el-link 
                id="confirm-insert"
                type="primary"
                @click="confirmSubmit(ruleInsertFormRef,'add')">
                    确 认
                </el-link>

                <!-- 取消添加 -->
                <el-link
                id="cancel-insert"
                type="primary"
                @click="cancelInsert"
                >
                    取 消
                </el-link>

            </el-card>
            
        </div>

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
                    label="密码"
                    prop="password">
                        <el-input
                        v-model="userEditForm.password"
                        placeholder="在 此 输 入 将 重 置 密 码" />
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

                    <!-- 确认修改 -->
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
import { getAllUsers, editUser, addUser, deleteUserByAccount } from '../../api/security/index';
import { UserRenderData } from '../../api/security/types';
import { FormInstance, FormRules,ElMessage } from 'element-plus';
import { InfoFilled } from '@element-plus/icons-vue';

/*
   用户列表
*/
// 用户信息
const users = ref<UserRenderData[]>()
// 等待加载
const loading = ref(true)

// 请求所有用户信息
async function getAndRenderAllUsers() {
    loading.value = true
    const ret:UserRenderData[] = await (await getAllUsers()).data
    users.value = ret?ret:users.value
    loading.value = false
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

// 添加用户显示
async function showInsertUser() {
    isOnInsert.value = true
}

/*
   操作
*/
// 根据操作类型验证表单并提交
async function confirmSubmit(formEl: FormInstance | undefined,operationType:'add'|'edit') {
    if (!formEl) return
    await formEl.validate( async function (valid, fields) { 
        if (valid) {
            if(operationType === 'edit') {
                // 修改用户
                if(await (await editUser(userEditForm)).status === 200) {
                    ElMessage({
                        message:'操作成功',
                        type:'success'
                    })
                    isOnEdit.value = false
                    userEditForm.account = ''
                    userEditForm.password = ''
                    userEditForm.email = ''
                    userEditForm.role = 0
                } else {
                    ElMessage({
                        message:'操作失败',
                        type:'error'
                    })
                }
                await getAndRenderAllUsers()
                
            } else {
                // 添加用户
                
                if(await (await addUser(userInsertForm)).status === 200) {
                    ElMessage({
                        message:'操作成功',
                        type:'success'
                    })
                    isOnInsert.value = false
                    userInsertForm.account = ''
                    userInsertForm.password = ''
                    userInsertForm.email = ''
                    userInsertForm.role = 0 
                } else {
                    ElMessage({
                        message:'操作失败',
                        type:'error'
                    })
                }
                await getAndRenderAllUsers()
            }
        } else {
            ElMessage({
                message: '请补全信息',
                type: 'error'
            })
        }
    })
}
/*
   删除用户
*/
async function deleteUser(index: number, row:UserRenderData) {
    if(await (await deleteUserByAccount(row.account)).status === 200) {
        ElMessage({
                message: '删除成功',
                type: 'success'
        })
        await getAndRenderAllUsers()
    } else {
        ElMessage({
            message:'操作失败',
            type:'error'
        })
    }
    
}

/*
   添加用户
*/
const isOnInsert = ref(false)
const ruleInsertFormRef = ref<FormInstance>()
const userInsertForm = reactive({
    account:'',
    password:'',
    email:'',
    role:0
})

// 添加用户数据验证规则
const userInsertRules = reactive<FormRules>({
    account:[
        {required:true,message:'请输入账号',trigger:'blur'},
        { min: 9, max: 9, message: '长度应该为9', trigger: 'blur'}
    ],
    email:[
        { required: true, message: '请输入邮箱地址', trigger: 'blur'},
        { type: 'email', message: '请输入正确的邮箱格式', trigger: ['blur', 'change']},
    ],
    password:[
        { required:true,message:'请输入密码',trigger:'blur' },
        {min: 6, message: '密码长度至少为6', trigger: 'blur' }
    ]
})

// 取消添加
function cancelInsert() {
    isOnInsert.value = false
    userInsertForm.account = ''
    userInsertForm.password = ''
    userInsertForm.email = ''
    userInsertForm.role = 0 
}

/*
   修改用户
*/
const isOnEdit = ref(false)
const ruleEditFormRef = ref<FormInstance>()
const userEditForm = reactive({
    account:'',
    password:'',
    email:'',
    role:0
})

// 修改用户数据验证规则
const userEditRules = reactive<FormRules>({
    account:[
        {required:true,message:'请输入账号',trigger:'blur'},
        { min: 9, max: 9, message: '长度应该为9', trigger: 'blur'}
    ],
    email:[
        { required: true, message: '请输入邮箱地址', trigger: 'blur'},
        { type: 'email', message: '请输入正确的邮箱格式', trigger: ['blur', 'change']},
    ],
    password:[
        { required:false },
        {min: 6, message: '密码长度至少为6', trigger: 'blur' }
    ]
})


</script>

<style lang="less" scoped>
#manager-container {
    #list-container {   
        position: relative;
        z-index: 1;
        margin: 0 6rem 0 6rem;
        #card-header {
            font-weight: bold;
            user-select: none;
        }
        #new-user{
            position: absolute;
            font-size: small;
            user-select: none;
            cursor: pointer;
            color: var(--el-color-primary);
            right: 1rem;
            top: 1.5rem;
        }
        #new-user:hover{
            position: absolute;
            font-size: small;
            user-select: none;
            cursor: pointer;
            color: var(--el-color-primary-light-5);
            right: 1rem;
            top: 1.5rem;
        }
        #new-user:active{
            position: absolute;
            font-size: small;
            user-select: none;
            cursor: pointer;
            color: var(--el-color-primary);
            right: 1rem;
            top: 1.5rem;
        }
    }

    #insert-card {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        z-index: 100;
        span {
            color: darkgray;
            margin-bottom: 1rem;
        }
        .el-card{
            position: relative;
            line-height: 1.8rem;
            width: 30rem;
            .el-form {
                font-weight: bold;
                .el-form-item {
                    margin-bottom: 2.5rem;
                }
            }
            #confirm-insert {
                position: absolute;
                padding: 0.2rem;
                font-size: 15px;
                bottom: 0;
                left:30%;
                transform: translate(-50%,0);
            }
            #cancel-insert {
                position: absolute;
                padding: 0.2rem;
                font-size: 15px;
                bottom: 0;
                right:30%;
                transform: translate(50%,0);
            }
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