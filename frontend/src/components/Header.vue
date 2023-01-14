<template>
            <div id="header-container">
                <!-- 标题 -->
                <span @click="backHome">539 <b>智</b> 能家居</span>

                <div id="logout-container">
                    <el-button 
                    plain
                    @click="logout">
                        退 出 登 录
                    </el-button>
                </div>
            </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import useStore from '../store';
import { storeToRefs } from 'pinia';
import { ElMessageBox } from 'element-plus';

// Pinia
const asideStore = useStore().aside
let { selectItem } = storeToRefs(asideStore)

// 点击标题回到主页
const router = useRouter()
async function backHome() {
    router.push('/homepage')
    selectItem.value = '/homepage'
    window.sessionStorage.setItem('selectItem','/homepage')
}

function logout(){
    ElMessageBox.confirm('是否退出当前账户', '提示', {
    confirmButtonText: 'OK',
    type: 'info'
  }).then(() => {
    router.replace('/login')
    sessionStorage.clear()
    localStorage.clear()
  })

}

</script>

<style lang="less" scoped>
    #header-container {
        position: relative;
        box-sizing: border-box;
        letter-spacing: 0.2rem;
        height: 100%;
        padding: 1.5rem 1rem;
        background-color: #171b22;
        
        span {
            cursor: pointer;
            font-family: 'STHeiti';
            margin-left: 1rem;
            color: gainsboro;

            b {
                font-size: large;
                font-family: 'STXingkai';
                box-shadow: 0 0 1.1rem white;
                padding: 5px;
                border-radius: 50%;
            }

        }
        span:hover{
            cursor: pointer;
            font-family: 'STHeiti';
            margin-left: 1rem;
            color: gainsboro;

            b {
                font-size: large;
                font-family: 'STXingkai';
                box-shadow: 0 0 1.2rem var(--el-color-primary);
                padding: 5px;
                border-radius: 50%;
            }
        }

        span:active{
            cursor: pointer;
            font-family: 'STHeiti';
            margin-left: 1rem;
            color: gainsboro;

            b {
                font-size: large;
                font-family: 'STXingkai';
                box-shadow: 0 0 1.2rem cyan;
                padding: 5px;
                border-radius: 50%;
            }
        }
        #logout-container {
            position: absolute;
            right: 1rem;
            top: 1.4rem;
        }
    }
</style>