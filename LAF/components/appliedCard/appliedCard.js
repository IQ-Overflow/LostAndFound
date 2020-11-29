// components/appliedCard/appliedCard.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
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
    agree(e){
      let { fid, pid } = e.currentTarget.dataset
      // console.log(e)
      this.triggerEvent('agree', { fid, pid })
    },
    refuse(e){
      let { fid, pid } = e.currentTarget.dataset
      // console.log(e)
      this.triggerEvent('refuse', { fid, pid })
    }
  }
})
