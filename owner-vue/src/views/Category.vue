<template>
  <div>
    <v-subheader class="py-0 d-flex justify-space-between rounded-lg">
      <div class="text-subtitle-1">Category List</div>
      <div style="text-align: right">
        <span class="group pa-2">
          <v-btn
              elevation="2"
              icon
              color="success"
              @click="clickAdd"
          ><v-icon>{{ icons.mdiPlus }}</v-icon></v-btn>
        </span>
        <v-btn
            elevation="2"
            icon
            color="primary"
            @click="clickSave"
        ><v-icon>{{ icons.mdiContentSave }}</v-icon></v-btn>
      </div>

    </v-subheader>

    <v-expansion-panels style="display: block">
      <draggable v-model="categoryList" id="categoryEl" >
        <v-expansion-panel
            v-for="category in categoryList" :key="category.categoryId" class="category-item" :data-id="category.categoryId"
        >
          <v-expansion-panel-header >
            <span contenteditable="true" >{{ category.name }}</span>

            <template v-slot:actions>
              <v-btn
                  elevation="2"
                  icon
                  @click.stop="clickDelete($event)"
              ><v-icon>{{ icons.mdiDelete }}</v-icon></v-btn>
            </template>

          </v-expansion-panel-header>
          <v-expansion-panel-content>

            <v-list-item v-for=" item in category.items" :key="item.id" >
              <v-list-item-content>
                <v-list-item-title> {{item.name}}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>

          </v-expansion-panel-content>
        </v-expansion-panel>
      </draggable>
    </v-expansion-panels>

  </div>
</template>

<script>
import draggable from 'vuedraggable'
import storeApi from "@/api/store";
import {
  mdiContentSave, mdiDelete,
  mdiPlus,
} from '@mdi/js'

export default {
  name: "Category",
  components:{
    draggable
  },
  data() {
    return {
      icons: {
        mdiContentSave,
        mdiPlus,
        mdiDelete,
      },
      categoryList: [

      ],
      deletedList: []
    }
  },
  methods:{
    clickAdd:function (){
      var count = this.categoryList.length+1
      var item = {
        name: 'new Category',
        order:count
      }
      this.categoryList.push(item)
    },
    clickDelete:function (event){
      var category = event.currentTarget.parentNode.parentNode.parentNode;
      let delCategory = {
        categoryId : category.dataset.id,
        name : category.children[0].children[0].innerHTML,
      }
      this.deletedList.push(delCategory)
      category.remove();
    },
    clickSave:function (){
      var vm =this;
      let data = {
        storeId : "1",
        categoryList: [],
        deletedList: this.deletedList
      }

      var categoryEl = document.querySelector("#categoryEl");
      categoryEl.childNodes.forEach(function(item ,index){
        let category = {
          categoryId : item.dataset.id,
          name : item.children[0].children[0].innerHTML,
          order : index+1
        }
        data.categoryList.push(category)
      })

      storeApi.putCategoryList(data)
        .then(function () {
          vm.getCategoryList()
      });

    },
    getCategoryList:function(){
      this.deletedList=[]
      this.categoryList = [];
      var vm =this;
      storeApi.getCategoryList()
      .then(function (response) {
        vm.categoryList = response.data.data;
      });
    }
  },
  mounted() {
    this.getCategoryList();
  }
}
</script>

<style scoped>

</style>