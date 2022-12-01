import useAsideStore from './aside';

export default function useStore()  {
    return {
        // 侧边栏
        aside:useAsideStore()
        
    }
}