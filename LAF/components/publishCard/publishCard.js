// components/publishCard/publishCard.js
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
    uploadImg: '',
    type: true,
    stuNum: '',
    name: '',
    college: '',
    contact: ''
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
    addPic() {
      let _this = this
      wx.chooseImage({
        count: 1,
        sizeType: 'compressed',
        success(res) {
          _this.setData({
            uploadImg: res.tempFilePaths 
          })
        }
      })
    },
    deletePic() {
      this.setData({
        uploadImg: ''
      })
    },
    publish() {
      console.log(this.data.stuNum, this.data.name, this.data.college)
      if(this.data.stuNum == '' || this.data.college=='' || this.data.name=='' || this.data.contact=='') {
        wx.showToast({
          title: '请将信息填写完整',
          icon: 'none'
        })
        return;
      }
      let _this = this;
      request({
        url: '/card/postCard',
        data: {
          stuID: this.data.stuNum,
          college: this.data.college,
          stuName: this.data.name,
          flag: this.data.type,
          contact: this.data.contact
        },
        method: 'POST'
      })
      .then(res => {
        if(res.data.flag == true) {
          wx.showToast({
            title: '发布成功'
          })
          _this.setData({
            stuNum: '',
            name: '',
            college: '',
            contact: ''
          })
        } else if(res.data.flag == false){
          wx.showToast({
            title: res.data.msg,
            icon: 'none'
          })
        }
      })
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
    getContact(e) {
      this.setData({
        contact: e.detail.value
      })
    }
  }
})
