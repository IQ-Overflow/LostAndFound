// pages/applied/applied.js
import request from '../../utils/request.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dataList:[],
    noneNews:false
  },
  getDataList(){
    request({
      url:'/reason/receivedApplies'
    }).then(res=>{
      // console.log(res)
      this.setData({
        dataList:res.data,
        noneNews:res.data.length===0?true:false
      })

    })
  },
  refuse(e){
    this.useApi(e, '/reason/refuseApplies','refuse')
  },
  agree(e){
    this.useApi(e, '/reason/agreeApplies','agree')
   
  },
  useApi(e,url,type){
    let { fid, pid } = e.detail
    let index = e.currentTarget.dataset.index
    request({
      url: url,
      data: {
        pID: pid,
        fID: fid
      },
      method: 'POST'
    }).then(res => {
      console.log(res)
      if (res.data.code === 1) {
        let that = this
        wx.showToast({
          title: res.data.msg,
          success: function () {
            let item = `dataList[${index}].state`
            that.setData({
              [item]: type === 'refuse' ? 2 : 1
            }, () => {
              console.log(that.data.dataList[index])
            })
          }
        })
      } else if (res.data.code === 0) {
        wx.showToast({
          title: res.data.msg,
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getDataList()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})