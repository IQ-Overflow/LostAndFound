import request from '../../utils/request'

// components/cardItem/cardItem.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    cardMsg: Object
  },

  /**
   * 组件的初始数据
   */
  data: {
    state: [{
      name: '进行中',
      color: '#c59f40'
    }, {
      name: '已结束',
      color: '#7cc540'
    }, {
      name: '已删除',
      color: '#c55b40'
    }],
    mode: 'other',    //当前卡片是自己还是他人发布的
    openid: '',
    showApplyInput: false,
    applyReason: ''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    askForContact() {
      let _this = this;
      wx.showModal({
        content: '确定申请对方的联系方式吗？',
        success() {
          request({
            url: '/reason/appliesForContact',
            method: 'POST',
            data: {
              pID: _this.data.cardMsg.stuID,
              tID: _this.data.cardMsg.poster.uID,
              message: _this.data.applyReason
            }
          })
          .then(res => {
            wx.showToast({
              icon: 'none',
              title: res.data.msg
            })
            if(res.data.code == 1) {
              this.setData({
                showApplyInput: false
              })
            }
          })
        }
      })
    },

    deleteCard() {
      let _this = this;
      wx.showModal({
        content: '确定删除此信息吗？',
        success() {
          request({
            url: '/card/delete',
            method: 'DELETE',
            data: {
              stuID: _this.data.cardMsg.stuID,
              flag: _this.data.cardMsg.flag
            }
          })
          .then(res => {
            if(res.data.flag == true) {
              wx.showModal({
                icon: 'none',
                title: '删除成功'
              })
              _this.triggerEvent('refresh');
            } else {
              wx.showModal({
                icon: 'none',
                title: res.data.msg
              })
            }
          })
        }
      })
    },

    showApply() {
      this.setData({
        showApplyInput: true
      })
    },

    hideApply() {
      this.setData({
        showApplyInput: false
      })
    },

    getValue(e) {
      this.setData({
        applyReason: e.detail.value
      })
    }
  },

  attached() {
    console.log('start')
    let openid = wx.getStorageSync('openid')
    this.setData({
      openid
    })
    if(this.data.cardMsg.poster.uID == openid) {
      this.setData({
        mode: 'self'
      })
    } else {
      this.setData({
        mode: 'other'
      })
    }
  }
})
