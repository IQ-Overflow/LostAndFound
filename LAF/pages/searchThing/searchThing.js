// pages/searchThing/searchThing.js
import request from '../../utils/request.js'
import throttle from '../../utils/throttle.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ['捡到', '丢失'],
    index:0,
    title:'',
    content:'',
    contactMethod:'',
    contact:'',
    imgUrl:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  bindPickerChange (e) {
    this.data.index = e.detail.value
    console.log(this.data.index)
    this.setData({
      index: this.data.index
    })
  },
  // bindFormSubmit(e){
  //   // throttle(this.cb,2000)
  //   // const throlledFn = throttle(this.cb, 2000)

  //   // console.log(throttle)
  // },
  cb(e){
    // console.log(e)
    // console.log('点击了')
  },
  sendRequest(e){
    this.data.title = e.detail.value.title
    this.data.content = e.detail.value.content
    this.data.contact = e.detail.value.contact
    // console.log(this.data.contact)
    request({
      url:'/others/publishOthers',
      header: {
        'content-type': 'application/x-www-form-urlencggoded;charset=UTF-8'
      },
      data:{
        title:this.data.title,
        content: this.data.content,
        flag: this.data.index === 0 ? true: false,
        contact: this.data.contact,
        imgFile:this.data.imgUrl
      },
      method:'POST'
    }).then(res=>{
      if (res.data.msg==='发布成功'){
        wx.showToast({
          title: '发布成功',
          duration:1000,
          icon:'success',
          success:function(){
            wx.switchTab({
              url: '/pages/index/index',
            })
            // console.log(1)
          }
        })
      }
    })
  },
  // 选择图片
  chooseImg(){
    wx.chooseImage({
      count:1,
      sizeType:['original','compressed'],
      success:(res)=>{
        let size = res.tempFiles[0].size // 图片的大小
        console.log(res)
        this.setData({
          imgUrl: res.tempFilePaths[0]
        })
      }
    })
  },
  // 使用canvas对图片进行压缩
  compressImg(imgUrl){

  },
  //删除照片
  cancelImg(){
    this.setData({
      imgUrl:''
    })
  },
  // 预览照片
  previewImage(e){
    wx.previewImage({
      current: this.data.imgUrl, // 当前显示图片的http链接  
      urls: [this.data.imgUrl] // 需要预览的图片http链接列表  
    })
    // console.log(e)
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
    this.bindFormSubmit = throttle(this.sendRequest,1000)
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