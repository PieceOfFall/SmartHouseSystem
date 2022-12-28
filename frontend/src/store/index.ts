import useAsideStore from './aside';
import useUserAccount from './user';

export default function useStore()  {
    return {
        // 侧边栏
        aside:useAsideStore(),
        // 用户
        user:useUserAccount()
    }
}