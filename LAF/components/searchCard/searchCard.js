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
    college: '',
    cardMsg: null
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
      if(this.data.stuNum == '' || this.data.college=='' || this.data.name=='') {
        wx.showToast({
          title: '请将信息填写完整',
          icon: 'none'
        })
        return;
      }
      request({
        url: '/card/searchCard',
        data: {
          stuID: this.data.stuNum,
          college: this.data.college,
          stuName: this.data.name
        },
        method: 'POST'
      })
      .then(res => {
        if(res.data.flag == true) {
          this.setData({
            cardMsg : res.data.msg
          })
          this.setData({
            stuNum: '',
            name: '',
            college: ''
          })
        } else {
          wx.showToast({
            icon: 'none',
            title: '查找不到相关信息'
          })
          this.setData({
            cardMsg : null
          })
        }
      })
    },
  }
})
