package com.levelup.jiemimoshengren.ui;

import org.json.JSONObject;

import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.levelup.jiemimoshengren.base.DefaultActivity;
import com.levelup.jiemimoshengren.config.Constant;
import com.levelup.jiemimoshengren.model.User;
import com.smy.volley.extend.EasyJsonObject;
/**个人信息界面(其他好友的)*/
public class UserInfoActivity extends DefaultActivity {

	private User user;
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}
	
	/**获取某人的信息*/
	private void getUserInfo(final String uid){
		JSONObject queryUserJson = makeQueryUserJson(uid);
		requestQueue.add(new JsonObjectRequest(Constant.URL_USER_INFO, queryUserJson, new Listener<JSONObject>() {
			public void onResponse(JSONObject response) {
				EasyJsonObject userJson = new EasyJsonObject(response);
				if(userJson.getBoolean("success")){
					User user = new User();
					user.setUsername(userJson.getString("uid"));
					user.setNick(userJson.getString("nick"));
					user.setFemale(userJson.getString("sex").equals(User.SEX_FEMALE));
					user.setSign(userJson.getString("sign"));
					user.setImgUrl(userJson.getString("head"));
					refreshUI();
				}else{
					showMsg(userJson.getString("error"));
				}
			}
		}, this));
	}

	/**创建查询用户信息的json*/
	private JSONObject makeQueryUserJson(final String uid) {
		EasyJsonObject queryUserJson = new EasyJsonObject();
		queryUserJson.put("uid", uid);
		return queryUserJson;
	}
	
	/**刷新UI*/
	private void refreshUI(){
		
	}

}
