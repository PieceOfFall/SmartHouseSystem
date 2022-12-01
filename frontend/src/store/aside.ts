import {
    defineStore
} from 'pinia';

const useAsideStore = defineStore('aside', {
    state: () => {
        return {
            isCollapse: false,
            selectItem: ''
        }
    },
    actions:{
        changeCollapse() {
            this.isCollapse = !this.isCollapse
        },
        setCollapse(status:boolean){
            this.isCollapse = status
        }
    }
})

export default useAsideStore