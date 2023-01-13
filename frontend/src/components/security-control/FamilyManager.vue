<template>
    <div id="manager-container">
        <el-card>

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
                        <el-button link type="primary" size="small" @click="editUser(scope.$index, scope.row)">
                            修改
                        </el-button>
                        <el-button link type="primary" size="small" @click="deleteUser(scope.$index, scope.row)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { getAllUsers } from '../../api/security/index';
import { UserRenderData } from '../../api/security/types';

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

// 修改用户信息
async function editUser(index: number, row:UserRenderData) {
    console.log(row);
}

// 删除用户
async function deleteUser(index: number, row:UserRenderData) {
    
}



</script>

<style lang="less" scoped>
#manager-container {
    .el-card {
        margin: 0 6rem 0 6rem;
        #card-header {
            font-weight: bold;
        }
    }
    
}
</style>