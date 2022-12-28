import {
    defineStore
} from 'pinia';

const useUserStore = defineStore('user', {
    state: () => {
        return {
            userAccount: ''
        }
    }
})

export default useUserStore