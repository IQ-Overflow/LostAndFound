// "NW eyJhbGciOiJIUzUxMiJ9.eyJ1aW5mbyI6IntcImlkXCI6NSxcIm9wZW5pZFwiOlwib0MtNy00M1dWQnFQUC02ZlBlemtCY09CWlM3Y1wiLFwidXNlcm5hbWVcIjpcIuOAgu6EmlwiLFwicGFzc3dvcmRcIjpcIlwiLFwiYXV0aG9yaXRpZXNcIjpbe31dfSIsInN1YiI6IuOAgu6EmiIsImlzcyI6ImVjaGlzYW4iLCJleHAiOjE2MDE3ODY4NjAsImlhdCI6MTYwMTE4MjA2MCwicm9sIjoi5pmu6YCaIn0.7ZrjexR5TVwEteNI2zbaKcNnYQfyI0r5zcVLeWgcS7oljSDpcH64RuJloOQIY3kY84hGq9voUw3rYlIJ3fDUWg"
/*
 * 用户注册
 * @param userData Object
 */
export async function register(userData) {
  wx.showLoading({
    title: '正在注册中',
    mask: true,
  });
  return new Promise((resolve, reject) => {
    wx.login({
      success: (res) => {
        let data = {
          code: res.code,
          ...userData,
        };
        wx.request({
          url: 'https://wishwall.zhanghaohui.club/wishwall/identity/register',
          data: data,
          method: 'POST',
          success: (result) => {
            wx.hideLoading();
            if (result.data.msg == '无法获取openid') {
              reject(result);
            } else {
              resolve(result);
            }
          },
          fail: function (err) {
            wx.hideLoading();
            reject(err);
          },
        });
      },
      fail: function (err) {
        reject(err);
        wx.hideLoading();
      },
    });
  });
}

/*
 * 用户登录
 */
export async function login() {
  wx.showLoading({
    title: '正在登录',
    mask: true,
  });
  return new Promise((resolve, reject) => {
    wx.login({
      success: (res) => {
        let code = res.code;
        wx.request({
          // url: 'http://111.230.181.203:9004/identity/login',
          url: `https://wishwall.zhanghaohui.club/wishwall/identity/login?code=${code}`,
          method: 'POST',
          success: (result) => {
            resolve(result);
            wx.hideLoading();
          },
          fail: function (err) {
            reject(err);
            wx.hideLoading();
          },
        });
      },
      fail: function (err) {
        reject(err);
        wx.hideLoading();
      },
    });
  });
}

/*
 *  录音权限
 */
export async function recordAuth(fn) {
  wx.getSetting({
    success(res) {
      if (!res.authSetting['scope.record']) {
        wx.authorize({
          //第一次授权
          scope: 'scope.record',
          success() {
            fn();
          },
          fail() {
            wx.openSetting({
              success (res) {
                if (res.authSetting['scope.record']) {
                  fn();
                } else {
                  wx.showModal({
                    title: '未授权录音',
                    content: '请点击右上角三个点进行设置',
                    showCancel: false,
                  })
                }
              },
              fail () {
                // wx.showToast({
                //   title: '请点右上角授权录音',
                //   icon: 'none',
                // });
                wx.showModal({
                  title: '未授权录音',
                  content: '请点击右上角三个点进行设置',
                  showCancel: false,
                })
              },
            });
            //调用失败
            // wx.showModal({
            //   title: '提示',
            //   content: '您未授权录音，功能将无法使用',
            //   showCancel: true,
            //   confirmText: '授权',
            //   confirmColor: '#52a2d8',
            //   success(res) {
            //     if (res.confirm) {
            //       wx.openSetting({
            //         success(res) {
            //           if (res.authSetting['scope.record']) {
            //             fn();
            //           } else {
            //             wx.showToast({
            //               title: '录音未授权',
            //               icon: 'none',
            //             });
            //           }
            //         },
            //         fail() {
            //           wx.showToast({
            //             title: '微信接口调用失败',
            //             icon: 'none',
            //           });
            //         },
            //       });
            //     } else {
            //       wx.showToast({
            //         title: '录音未授权,请自行设置',
            //         icon: 'none',
            //       });
            //     }
            //   },
            //   fail() {
            //     wx.showToast({
            //       title: '微信接口调用失败',
            //       icon: 'none',
            //     });
            //   },
            // });
          },
        });
      } else {
        fn();
      }
    },
    fail() {
      wx.showModal({
        title: '提示',
        content: '您未授权录音，功能将无法使用',
        showCancel: true,
        confirmText: '授权',
        confirmColor: '#52a2d8',
      });
    },
  });
}

/*
 * 获取access_token
 * 
 */
export function getAccess_token () {
  let token = wx.getStorageSync('token') 
  return new Promise((resolve, reject) => {
    wx.request({
      url: 'https://wishwall.zhanghaohui.club/wishwall/identity/getaccesstoken',
      method: 'GET',
      header: {
        Authorization: token,
      },
      success: (result) => {
        console.log(result);
        let starttime = new Date().getTime()
        let token = result.header.access_token
        let data = {
          starttime,
          token
        }
        wx.setStorageSync('access_token', data)
        resolve(result);
      },
      fail: function (err) {
        reject(err);
      },
    });
  }).catch(err => {
    console.log(err);
  })
}

/*
 * 用户没有授权
 */
// export const getUserAuth = function () {
//   wx.getUserInfo({
//     success: function (res) {
//       let userInfo = res.userInfo
//       wx.setStorageSync ('userName', userInfo.nickName)
//       wx.navigateTo({
//         url: 'pages/slectSex/slectSex'
//       })
//       let userData = {
//          userName: userInfo.nickName,
//          sex: false
//       }
//       // register(userData).then((res)=>{
//       //   if(res.data.code === 1002 && res.data.msg === "该用户已经注册过了") {
//       //     login().then((resLogin)=>{
//       //       console.log(resLogin)
//       //       resLogin.header.token && wx.setStorageSync ('token', resLogin.header.token)
//       //     }).catch((err)=>{
//       //       console.log(err)
//       //     })
//       //   } else {
//       //     res.header.token && wx.setStorageSync ('token', res.header.token)
//       //   }
//       // }).catch((reason)=>{
//       //   console.log(reason)
//       // })
//     },
//     fail: function (err) {
//       console.log(err)
//     }
//   })
// }
