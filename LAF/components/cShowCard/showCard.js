// components/cShowCard/showCard.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    type:{
      type:String,
      value:'lost'
    },
    dataItem:{
      type:Object
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    applyContact(e){
      // console.log(this.data.dataItem)
      // console.log(e)
      // console.log(e.currentTarget.dataset.dataitem)
      let { oID, uID } = e.currentTarget.dataset.dataitem
      // console.log(oID, uID)
      this.triggerEvent('applyContact', { oID, uID})
    }
  }
})
