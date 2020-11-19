// components/searchCard/searchCard.js
import request from '../../utils/request'
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
    type: true,
    stuNum: '',
    name: '',
    college: ''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    bindPickerChange: function(e) {
      if(e.detail.value == 0) {
        this.setData({
          type: true,
          index: e.detail.value
        })
      } else {
        this.setData({
          type: false,
          index: e.detail.value
        })
      }
      console.log(this.data.type)
    },
    getStuNum(e) {
      this.setData({
        stuNum: e.detail.value
      })
    },
    getName(e) {
      this.setData({
        name: e.detail.value
      })
    },
    getCollege(e) {
      this.setData({
        college: e.detail.value
      })
    },
    search() {
      console.log(this.data.stuNum, this.data.name, this.data.college)
      let _this = this;
      request({
        url: '/card/searchCard',
        data: {
          stuID: this.data.stuNum,
          college: this.data.college,
          stuName: this.data.name,
          flag: this.data.type
        },
        method: 'POST'
      })
      .then(res => {
        console.log(res.data)
      })
    },
  }
})
