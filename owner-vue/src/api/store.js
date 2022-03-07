import axios from "axios";

export default {
    getCategoryList(){
        return axios.get(process.env.VUE_APP_OWNER_SERVICE_BASEURL+'/store-service/api/customer/category');
    },
    putCategoryList(data){
        return axios({
            method:'put',
            url:process.env.VUE_APP_OWNER_SERVICE_BASEURL+'/store-service/api/customer/category',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: data,
            responseType:'json'
        })
    },
    getItemById(itemId){
        return axios.get(process.env.VUE_APP_OWNER_SERVICE_BASEURL+'/store-service/api/customer/item/'+itemId)
    },
    saveItem(method, itemData){
        const _url = process.env.VUE_APP_OWNER_SERVICE_BASEURL+'/store-service/api/customer/item'+(method==='put'?+"/"+itemData.itemId:'')
        console.log(_url)
        return axios({
            method:method,
            url: _url,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: itemData,
            responseType:'json'
        })
    },
    getMenu(searchParam){
        return axios.get(process.env.VUE_APP_OWNER_SERVICE_BASEURL+'/store-service/api/customer/item',searchParam);
    },
}