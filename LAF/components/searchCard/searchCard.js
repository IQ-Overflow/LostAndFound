// components/searchCard/searchCard.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    index: 0,
    array: ['捡到', '丢失'],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    bindPickerChange: function(e) {
      this.setData({
        index: e.detail.value
      })
    },
  }
})
