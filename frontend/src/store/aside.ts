import {
    defineStore
} from 'pinia';

const useAsideStore = defineStore('aside', {
    state: () => {
        return {
            isStoreCollapse: false,
            selectItem: ''
        }
    },
    actions:{
        changeCollapse() {
            this.isStoreCollapse = !this.isStoreCollapse
        },
        setCollapse(status:boolean){
            this.isStoreCollapse = status
        }
    }
})

export default useAsideStore