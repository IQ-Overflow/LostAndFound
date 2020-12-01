// components/inputToast/inputToast.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    isShowConfirm: {
      type: Boolean,
      value: false
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    inputValue:''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    cancel() {
      this.setData({
        isShowConfirm:false
      })
    },
    setValue(e){
      // console.log(e)
      this.data.inputValue = e.detail.value
      // this.setData({
      //   inputValue: this.data.inputValue
      // })
    },
    confirmAcceptance(){
      this.triggerEvent('clickSureBtn', this.data.inputValue)
      this.setData({
        isShowConfirm: false
      })
    }
  },
  detached: function () {
    // 在组件实例被从页面节点树移除时执行
    // console.log('123')
  }
})